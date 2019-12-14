package com.example.flyingpotato;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AnswerActivity extends AppCompatActivity {

    EditText number;
    Button submit;

    int potato;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        potato = intent.getIntExtra("potato", 0);

        number = findViewById(R.id.number);
        submit = findViewById(R.id.submit);

    }

    public void submitNumber(View view){
        if(!number.getText().toString().isEmpty()) {
            System.out.println(number.getText().toString());

            Intent intent = new Intent(this, PostScreenActivity.class);
            intent.putExtra("result", Integer.parseInt(number.getText().toString()) == potato);
            startActivity(intent);
            overridePendingTransition(0, 0);
            finish();
        } else
        {
            Toast.makeText(this, "No answer provided!", Toast.LENGTH_SHORT).show();
        }
    }
}
