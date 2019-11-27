package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PreGameActivity extends AppCompatActivity {

    SharedPreferences pref;
    LinearLayout thingie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        getSupportActionBar().hide();

        thingie = findViewById(R.id.thingie);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);

        if(!pref.contains("username")){
            thingie.removeAllViews();
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(R.drawable.logo);
            thingie.addView(imageView);
        }
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

    public void how(View view){
        Intent intent = new Intent(this, HowToActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
