package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PostScreenActivity extends AppCompatActivity {

    TextView congrats, score, gold;
    Button play;
    LinearLayout thingo;
    Users user, currUser;
    ArrayList<Users> users = new ArrayList<>();
    DatabaseReference database;

    SharedPreferences pref, pref2;

    boolean result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_screen);
        getSupportActionBar().hide();

        congrats = findViewById(R.id.textView9);
        score = findViewById(R.id.score);
        gold = findViewById(R.id.gold);

        play = findViewById(R.id.playagain);
        thingo = findViewById(R.id.thingo);

        Intent intent = getIntent();

        result = intent.getBooleanExtra("result", true);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        pref2 = getSharedPreferences("game_details", MODE_PRIVATE);

        score.setText(pref2.getInt("level", 0)+"");

        database = FirebaseDatabase.getInstance().getReference("Users");

        database.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                System.out.println("BOI");
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    user = data.getValue(Users.class);
                    users.add(user);
                }
                for(Users user: users){

                    if(user.getName().compareTo(pref.getString("username", "")) == 0){
                        System.out.println("FOUND USER");
                        currUser = new Users(user);
                    }
                }

                if(!result){
                    thingo.removeView(play);
                    congrats.setText("Game Over!");
                }

                if(!pref.contains("username")){
                    gold.setText("");
                } else {
                    if(pref2.getInt("powerup", 0) != 2){
                        gold.setText("You Earned 20 Gold!");
                        currUser.setCash(currUser.getCash()+20);
                        database.child(currUser.getId()).setValue(currUser);
                    } else {
                        gold.setText("You Earned 50 Gold!");
                        currUser.setCash(currUser.getCash()+50);
                        database.child(currUser.getId()).setValue(currUser);
                    }

                    if(Integer.parseInt(score.getText().toString().trim()) > currUser.getHighscore()){
                        currUser.setHighscore(Integer.parseInt(score.getText().toString().trim()));
                        database.child(currUser.getId()).setValue(currUser);
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();


    }

    public void playagain(View view){
        Intent intent = new Intent(this, PreGameActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void menu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
