package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PreGameActivity extends AppCompatActivity {

    SharedPreferences pref;
    LinearLayout thingie;
    Spinner spinner;
    private List<Users> users;
    private Users user;
    private DatabaseReference database;
    private int selectedPotion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selectedPotion=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        getSupportActionBar().hide();

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        users = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference("Users");

        thingie = findViewById(R.id.thingie);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        spinner = findViewById(R.id.spinner);

        if(!pref.contains("username")){
            thingie.removeAllViews();
            ImageView imageView = new ImageView(this);
            imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            imageView.setBackgroundResource(R.drawable.logo);
            thingie.addView(imageView);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                users.clear();

                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    user = data.getValue(Users.class);
                    users.add(user);
                }
                for(Users user: users){
                    if(user.getName().compareTo(pref.getString("username", "")) == 0){
                        List<String> list = new ArrayList<String>();
                        if (user.getLowSpeed()>0)
                            list.add("Slow Mo Potion x" + user.getLowSpeed());
                        if (user.getGoldMulti()>0)
                            list.add("Money Multiplier Potion x"+user.getGoldMulti());
                        if (user.getLessObs()>0)
                            list.add("Obstruction Potion x"+user.getLessObs());
                        if (user.getLowSpeed()==0&&user.getGoldMulti()==0&&user.getLessObs()==0)
                            list.add("No Power Ups!");
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dataAdapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if (adapterView.getItemAtPosition(i).toString().contains("Slow Mo")){
                                    selectedPotion=1;
                                    Toast.makeText(getApplicationContext(), "Slow Mo Potion Selected!", Toast.LENGTH_LONG).show();
                                }else if(adapterView.getItemAtPosition(i).toString().contains("Money Multiplier")){
                                    selectedPotion=2;
                                    Toast.makeText(getApplicationContext(), "Money Multiplier Potion Selected!", Toast.LENGTH_LONG).show();
                                }else if(adapterView.getItemAtPosition(i).toString().contains("Obstruction")){
                                    selectedPotion=3;
                                    Toast.makeText(getApplicationContext(), "Obstruction Potion Selected!", Toast.LENGTH_LONG).show();
                                }
                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

                            }
                        });
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void play(View view){
        //add database code which will reduce the number of potions based on selectedPotion int

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
