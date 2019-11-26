package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, leaderboard, shop, logout;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

    }

    public void play(View view){
        Intent intent = new Intent(this, PreGameActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void profile(View view){
        Intent intent = new Intent(this, ProfileActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void leaderboard(View view){
        Intent intent = new Intent(this, LeaderBoardActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void shop(View view){
        Intent intent = new Intent(this, ShopActivity.class);
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
