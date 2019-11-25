package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().hide();
    }

    public void post(View view){
        Intent intent = new Intent(this, PostScreenActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }


}
