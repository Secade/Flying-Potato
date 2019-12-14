package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PostScreenActivity extends AppCompatActivity {

    TextView congrats, score, gold;
    Button play;
    LinearLayout thingo;

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

        score.setText(pref2.getInt("level", 0));

        if(!result){
            thingo.removeView(play);
            congrats.setText("Game Over!");
        }

        if(!pref.contains("username")){
            gold.setText("");
        } else {
            if(pref2.getInt("powerup", 0) != 2){
                gold.setText("You Earned 20 Gold!");
                //add gold to user
            } else {
                gold.setText("You Earned 50 Gold!");
                //add gold to user
            }

            //if score > highest score, then replace high score
        }
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
