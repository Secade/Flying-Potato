package com.example.flyingpotato;

import android.content.Intent;
import android.os.Build;
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

public class SignUpFragment extends Fragment {

    private View view;

    private Button signup, back;
    private EditText username, password;

    private DatabaseReference register;
    private String name;
    private String passw;
    private boolean state;
    public SignUpFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        register = FirebaseDatabase.getInstance().getReference("Users");


        view = inflater.inflate(R.layout.fragment_signup, container, false);

        signup = view.findViewById(R.id.signup);
        back = view.findViewById(R.id.back);

        username = view.findViewById(R.id.ETusername);
        password = view.findViewById(R.id.ETpassword);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                if(addUser()==false){
                   //yes
                }else{
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    startActivity(intent);
                    getActivity().overridePendingTransition(0, 0);
                    getActivity().finish();
                }



        }
        });

        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addFragment(new InitialFragment(),false,"two");
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
//    public Boolean addUser() {
//        name = username.getText().toString().trim();
//        passw = password.getText().toString().trim();
//        if(!TextUtils.isEmpty(name) && checker()== true) {
//            String id = register.push().getKey();
//            Users user = new Users(id, name, passw);
//            register.child(id).setValue(user);
//            Toast.makeText(getActivity(), "User signup success!", Toast.LENGTH_LONG).show();
//            return true;
//        }
//        if(checker()== false){
//            name = "";
//            passw = "";
//            Toast.makeText(getActivity(),"Username already exists!", Toast.LENGTH_LONG).show();
//            return false;
//        }else
//            Toast.makeText(getActivity(),"Please fill all the blanks!", Toast.LENGTH_LONG).show();
//            return false;
//    }
    public boolean addUser(){ //still broken but working on it
        name = username.getText().toString().trim();
        passw = password.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(passw)) {
            Toast.makeText(getActivity(), "Please fill all the blanks!", Toast.LENGTH_LONG).show();
            state = false;
        }
        else {
            register.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    if (dataSnapshot.child("Users").child(name).exists()) {
                        Toast.makeText(getActivity(), "Username already exists!", Toast.LENGTH_LONG).show();
                        state = false;

                    } else {

                        String id = register.push().getKey();
                        Users user = new Users(id, name, passw);
                        register.child(id).setValue(user);
                        Toast.makeText(getActivity(), "User signup success!", Toast.LENGTH_LONG).show();
                        state = true;
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        System.out.println(state);

        return state;
    }

}
