package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PreGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        getSupportActionBar().hide();
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void play(View view){
        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
