package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class PlayActivity extends AppCompatActivity {

    GameView gameView;
    SharedPreferences pref2;
    int powerup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();
        this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        pref2 = getSharedPreferences("game_details", MODE_PRIVATE);
        powerup = pref2.getInt("powerup", 0);
        AppConstants.gameActivityContext = this;
        gameView = new GameView(this, powerup);
        setContentView(gameView);
    }

    public void post(View view){
        Intent intent = new Intent(this, PostScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void guessNumber(View view){
        Intent intent = new Intent(this, AnswerActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
