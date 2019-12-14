package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class HowToActivity extends AppCompatActivity {

    LinearLayout background;
    TextView step;
    int numPictures =0;
    Button next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);
        getSupportActionBar().hide();

        next = findViewById(R.id.next);
        background = findViewById(R.id.background);
        step = findViewById(R.id.step);

        numPictures=0;

        background.setBackgroundResource(R.drawable.step1);
    }

    public void next(View view){
        switch (numPictures){
            case 0:
                background.setBackgroundResource(R.drawable.step1);
                step.setText("STEP 1:");
                numPictures++;
                break;
            case 1:
                background.setBackgroundResource(R.drawable.step2);
                step.setText("STEP 2:");
                numPictures++;
                break;
            case 2:
                background.setBackgroundResource(R.drawable.step3);
                step.setText("STEP 3:");
                numPictures++;
                break;
            case 3:
                background.setBackgroundResource(R.drawable.step4);
                step.setText("STEP 4:");
                numPictures++;
                break;
            case 4:
                background.setBackgroundResource(R.drawable.step5);
                step.setText("STEP 5:");
                numPictures++;
                break;
            case 5:
                background.setBackgroundResource(R.drawable.step6);
                step.setText("STEP 6:");
                next.setText("FINISH");
                numPictures++;
                break;
            case 6:
                numPictures=0;
                Intent intent = new Intent(this, PreGameActivity.class);
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
