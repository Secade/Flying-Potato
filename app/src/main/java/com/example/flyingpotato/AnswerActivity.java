package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AnswerActivity extends AppCompatActivity {

    EditText number;
    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        getSupportActionBar().hide();

        number = findViewById(R.id.number);
        submit = findViewById(R.id.submit);

    }

    public void submitNumber(View view){
        System.out.println(number.getText().toString());

        Intent intent = new Intent(this, PlayActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}
