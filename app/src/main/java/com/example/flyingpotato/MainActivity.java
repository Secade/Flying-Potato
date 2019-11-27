package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button play, leaderboard, shop, logout, profile;

    private SharedPreferences shareMain;

    public MainActivity(){

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        shareMain = getSharedPreferences("user_details", MODE_PRIVATE);
        shop = findViewById(R.id.shop);
        profile = findViewById(R.id.profile);

        if(!shareMain.contains("username")){
            shop.setEnabled(false);
//            shop.setVisibility(View.INVISIBLE);
            shop.setAlpha(0.5f);
            profile.setEnabled(false);
//            profile.setVisibility(View.INVISIBLE);
            profile.setAlpha(0.5f);
        }

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

        SharedPreferences.Editor editor = shareMain.edit();

        editor.clear();
        editor.commit();

        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }



}
