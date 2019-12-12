package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ShopActivity extends AppCompatActivity {

    TextView gold;
    SharedPreferences pref;
    private List<Users> users;
    private Users user;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        getSupportActionBar().hide();

        gold=findViewById(R.id.gold);

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        users = new ArrayList<>();

        database = FirebaseDatabase.getInstance().getReference("Users");

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
                        System.out.println("GOTTEM GOLD");
                        gold.setText(String.format("%.02f Gold", user.getCash()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    public void buyObstruction(View view){
        for(Users user: users){
            if(user.getName().compareTo(pref.getString("username", "")) == 0){
                if (user.getCash()>=10) {
                    user.setCash(user.getCash() - 10);
                    user.setLessObs(user.getLessObs() + 1);
                    database.child(user.getId()).setValue(user);
                    gold.setText(String.format("%.02f Gold", user.getCash()));
                    Toast.makeText(this, "Bought 1 Obstruction Potion for 10 Gold!", Toast.LENGTH_LONG).show();
                }else{
                        Toast.makeText(this, "You don't have enough money...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void buyMultiplier(View view){
        for(Users user: users){
            if(user.getName().compareTo(pref.getString("username", "")) == 0){
                if (user.getCash()>=15) {
                    user.setCash(user.getCash() - 15);
                    user.setGoldMulti(user.getGoldMulti() + 1);
                    database.child(user.getId()).setValue(user);
                    gold.setText(String.format("%.02f Gold", user.getCash()));
                    Toast.makeText(this, "Bought 1 Money Multiplier Potion for 15 Gold!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "You don't have enough money...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void buySlow(View view){
        for(Users user: users){
            if(user.getName().compareTo(pref.getString("username", "")) == 0){
                if (user.getCash()>=20) {
                    user.setCash(user.getCash() - 20);
                    user.setLowSpeed(user.getLowSpeed() + 1);
                    database.child(user.getId()).setValue(user);
                    gold.setText(String.format("%.02f Gold", user.getCash()));
                    Toast.makeText(this, "Bought 1 Slow Mo Potion for 20 Gold!", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(this, "You don't have enough money...", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    public void back(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }

    public void finish(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(0, 0);
        finish();
    }
}