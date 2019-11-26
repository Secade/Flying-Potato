package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class HowToActivity extends AppCompatActivity {

    LinearLayout background;
    int numPictures =0;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        getSupportActionBar().hide();

        next = findViewById(R.id.next);
        background = findViewById(R.id.background);

        background.setBackgroundResource(R.drawable.back);
    }

    public void next(View view){
        switch (numPictures){
            case 0:
                background.setBackgroundResource(R.drawable.back);
                numPictures++;
                break;
            case 1:
                background.setBackgroundResource(R.drawable.buttonbg);
                numPictures++;
                break;
            case 2:
                background.setBackgroundResource(R.drawable.logo);
                numPictures++;
                break;
            case 3:
                background.setBackgroundResource(R.drawable.logout);
                next.setText("FINISH");
                numPictures++;
                break;
            case 4:
                numPictures=0;
                Intent intent = new Intent(this, PlayActivity.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
                finish();
                break;
        }
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
