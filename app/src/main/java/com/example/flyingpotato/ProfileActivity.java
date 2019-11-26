package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView username,gold,level;
    SharedPreferences pref;
    private List<Users> users;

    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        getSupportActionBar().hide();

        username=findViewById(R.id.username);
        gold=findViewById(R.id.gold);
        level=findViewById(R.id.level);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        database = FirebaseDatabase.getInstance().getReference("Users");
        users = new ArrayList<>();

        username.setText(pref.getString("username", ""));

    }

    @Override
    protected void onStart() {
        super.onStart();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    Users user = data.getValue(Users.class);
                    //System.out.println(user.getName());
                    users.add(user);
                }
                for(Users user: users){
                    if(user.getName().compareTo(pref.getString("username", "")) == 0){
                        System.out.println("kjhfskrghkje");
                        gold.setText(String.format("%.02f", user.getCash()));
                        level.setText(user.getHighscore() +"");
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void logout(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
