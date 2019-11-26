package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class LeaderBoardActivity extends AppCompatActivity {

    private DatabaseReference databaseUsers;

    private List<Users> users;

    private LinearLayout linlay;

//    private boolean daily;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leader_board);
        getSupportActionBar().hide();

        databaseUsers = FirebaseDatabase.getInstance().getReference("Users");
        users = new ArrayList<>();

        linlay = findViewById(R.id.thing);
//        daily = true;
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseUsers.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();
                linlay.removeAllViews();

                for(DataSnapshot postSnapshot: dataSnapshot.getChildren()){
                    Users user = postSnapshot.getValue(Users.class);
                    users.add(user);
                }

                leaderboard();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void leaderboard(){
        if(users.size() != 0)
            for(int i = users.size() - 1; i >= 0; i--){
                if(users.get(i).getHighscore() == 0){
                    users.remove(i);
                }
            }
        if(users.size() == 0){
            linlay.setBackgroundResource(R.drawable.buttonbg);
            TextView textView = new TextView(this);
            textView.setText("No Data Available");
            Typeface typeface = ResourcesCompat.getFont(this, R.font.lemon);
            textView.setTypeface(typeface);
            textView.setTextSize(20);
            textView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            textView.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            textView.setGravity(Gravity.CENTER);
            linlay.addView(textView);
        } else {
            Collections.sort(users, new Comparator<Users>() {

                public int compare(Users o1, Users o2) {
                    // compare two instance of `Score` and return `int` as result.
                    return o2.getHighscore() - o1.getHighscore();
                }
            });
//            Collections.reverse(users);
            for(Users user : users){
                Typeface typeface = ResourcesCompat.getFont(this, R.font.lemon);
                LinearLayout innerLayout = new LinearLayout(this);
                innerLayout.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,200
//                        ViewGroup.LayoutParams.WRAP_CONTENT
                ));
                innerLayout.setOrientation(LinearLayout.HORIZONTAL);
                innerLayout.setWeightSum(2);
                innerLayout.setBackgroundResource(R.drawable.buttonbg);
                innerLayout.setPadding(50, 0, 50, 0);
                TextView name = new TextView(this);
                LinearLayout.LayoutParams params_name = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
                params_name.weight = 1;
                name.setLayoutParams(params_name);
                name.setText(user.getName());
                name.setGravity(Gravity.START | Gravity.CENTER_VERTICAL);
                name.setTextSize(25);
                name.setTypeface(typeface);
                innerLayout.addView(name);
                TextView score = new TextView(this);
                LinearLayout.LayoutParams params_phone = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        ViewGroup.LayoutParams.MATCH_PARENT
                );
                params_phone.weight = 1;
                score.setLayoutParams(params_phone);
                score.setText(user.getHighscore() + "");
                score.setGravity(Gravity.END | Gravity.CENTER_VERTICAL);
                score.setTextSize(25);
                innerLayout.addView(score);

                linlay.addView(innerLayout);

                View view = new View(this);
                view.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, 3
                ));
                view.setBackgroundColor(Color.TRANSPARENT);
                linlay.addView(view);
            }
        }
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
