package com.example.flyingpotato;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PreGameActivity extends AppCompatActivity {

    SharedPreferences pref, pref2;
    LinearLayout thingie;
    Spinner spinner;
    private List<Users> users = new ArrayList<>();
    private Users user, currUser;
    private DatabaseReference database;
    private int selectedPotion;
    private String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        selectedPotion=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_game);
        getSupportActionBar().hide();

        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        pref2 = getSharedPreferences("game_details", MODE_PRIVATE);

        database = FirebaseDatabase.getInstance().getReference("Users");

        thingie = findViewById(R.id.thingie);
        pref = getSharedPreferences("user_details", MODE_PRIVATE);
        spinner = findViewById(R.id.spinner);

        String[] names ={"None","Slow Mo!","Money Multiplier!","Less Obstructions!"};
        int icons[] = {R.drawable.blank, R.drawable.garlic, R.drawable.oil, R.drawable.potion};

        CustomAdapter customAdapter=new CustomAdapter(getApplicationContext(),icons,names);
        spinner.setAdapter(customAdapter);

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
        key = database.push().getKey();

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
                        currUser=new Users(user);
                        List<String> list = new ArrayList<String>();
                        list.add("No Power Up");
                        if (user.getLowSpeed()>0)
                            list.add("Slow Mo Potion x" + user.getLowSpeed());
                        if (user.getGoldMulti()>0)
                            list.add("Money Multiplier Potion x"+user.getGoldMulti());
                        if (user.getLessObs()>0)
                            list.add("Obstruction Potion x"+user.getLessObs());
                        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, list);
                        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        spinner.setAdapter(dataAdapter);
                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                if(adapterView.getItemAtPosition(i).toString().contains("No Power")){
                                    selectedPotion=0;
                                    Toast.makeText(getApplicationContext(), "No Potion Selected!", Toast.LENGTH_LONG).show();
                                }else if (adapterView.getItemAtPosition(i).toString().contains("Slow Mo")){
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
        if(selectedPotion == 1){
            System.out.println("SLOW MO USED");
            currUser.setLowSpeed(currUser.getLowSpeed()-1);
            database.child(currUser.getId()).setValue(currUser);
        }else if(selectedPotion ==2){
            System.out.println("GOLD MULTI USED");
            currUser.setGoldMulti(currUser.getGoldMulti()-1);
            database.child(currUser.getId()).setValue(currUser);
        }else if(selectedPotion==3){
            System.out.println("LESS OBS USED");
            currUser.setLessObs(currUser.getLessObs()-1);
            database.child(currUser.getId()).setValue(currUser);
        }

        SharedPreferences.Editor editor = pref2.edit();
        editor.putInt("powerup", selectedPotion);
        editor.putInt("level", pref2.getInt("level", 0) + 1);
        editor.commit();

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

class CustomAdapter extends BaseAdapter {
    Context context;
    int flags[];
    String[] countryNames;
    LayoutInflater inflter;

    public CustomAdapter(Context applicationContext, int[] flags, String[] countryNames) {
        this.context = applicationContext;
        this.flags = flags;
        this.countryNames = countryNames;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return flags.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.custom_spinner_items, null);
        ImageView icon = (ImageView) view.findViewById(R.id.imageView);
        TextView names = (TextView) view.findViewById(R.id.textView);
        icon.setImageResource(flags[i]);
        names.setText(countryNames[i]);
        return view;
    }
}
