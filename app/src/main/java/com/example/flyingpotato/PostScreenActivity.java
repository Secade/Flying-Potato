package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PostScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_screen);
        getSupportActionBar().hide();
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
