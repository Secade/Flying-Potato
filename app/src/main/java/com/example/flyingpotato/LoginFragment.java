package com.example.flyingpotato;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class LoginFragment extends Fragment {

    private View view;

    private EditText username, password;

    private Button login, back;

    private DatabaseReference log;

    private Users user;

    private ArrayList<Users> users = new ArrayList<>();

    SharedPreferences pref;

    public LoginFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //sharedPref

        //if may naka log in na


    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        view = inflater.inflate(R.layout.fragment_login, container, false);

        login = view.findViewById(R.id.login);
        back = view.findViewById(R.id.back);
        username = view.findViewById(R.id.ETusername);
        password = view.findViewById(R.id.ETpassword);

        log = FirebaseDatabase.getInstance().getReference("Users");

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
               checkLogin();
            }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new InitialFragment(),false,"one");
            }
        });

        return view;
    }

    public void addFragment(Fragment fragment, boolean addToBackStack, String tag) {
        FragmentManager manager = getFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();

        if (addToBackStack) {
            ft.addToBackStack(tag);
        }
        ft.replace(R.id.frame_container, fragment, tag);
        ft.commitAllowingStateLoss();
    }

    public void checkLogin(){
        final String name = username.getText().toString().trim();
        final String passw = password.getText().toString().trim();
        if(!TextUtils.isEmpty(name)|| !TextUtils.isEmpty(passw)){
        log.addListenerForSingleValueEvent(new ValueEventListener() {
            int count =0;
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot data: dataSnapshot.getChildren()) {
                    user = data.getValue(Users.class);
                    //System.out.println(user.getName());
                    users.add(user);
                }
                for(int i =0; i < users.size();i++){
                    if(users.get(i).getName().equals(name)){
                        if(users.get(i).getPassword().equals(passw)){
                            //user = users.get(i);
                            System.out.println("HELLO");
                            Toast.makeText(getActivity(), "Login Successfully", Toast.LENGTH_LONG).show();
                            i = users.size()-1;


                            //SharedPref
                            pref = getActivity().getSharedPreferences("user_details", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = pref.edit();
                            editor.putString("username", name);
                            editor.putString("password", passw);
                            editor.commit();

                            Intent intent = new Intent(getActivity(), MainActivity.class);
                            startActivity(intent);
                            getActivity().overridePendingTransition(0, 0);
                            getActivity().finish();

                        }else{
                          //  System.out.println("bbbbbbbbbbb");
                            Toast.makeText(getActivity(), "Password is incorrect", Toast.LENGTH_LONG).show();
                            i = users.size()-1;
                        }
                    }else{
                        Toast.makeText(getActivity(), "User does not exist", Toast.LENGTH_LONG).show();
                    }
                }

            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        }else{
            Toast.makeText(getActivity(), "Please fill all the blanks!", Toast.LENGTH_LONG).show();
        }

    }

}
