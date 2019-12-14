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

    private Users users;

    private Button signup, back;
    private EditText username, password;

    private DatabaseReference register;
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

    public boolean addUser(){

        final String name = username.getText().toString().trim();
        final String passw = password.getText().toString().trim();
        if(TextUtils.isEmpty(name) || TextUtils.isEmpty(passw)) {
            Toast.makeText(getActivity(), "Please fill all the blanks!", Toast.LENGTH_LONG).show();
            state = false;
        }
        else {
            register.addListenerForSingleValueEvent(new ValueEventListener() {
                int i =0;
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    System.out.println("IM HERE");

                    for (DataSnapshot data: dataSnapshot.getChildren()) {
                        users = data.getValue(Users.class);
                        System.out.println(users.getName());
                        if (users.getName().compareToIgnoreCase(name)==0){
                            i++;
                        }
                    }
                    if (i!=0) {
                        System.out.println("WORKED");
                        Toast.makeText(getActivity(), "Username already exists!", Toast.LENGTH_LONG).show();
                        state = false;
                        i=0;
                    } else{
                        System.out.println("NOT WORKED");
                        String id = register.push().getKey();
                        Users user = new Users(id, name, passw);
                        register.child(id).setValue(user);
                        Toast.makeText(getActivity(), "User signup success!", Toast.LENGTH_LONG).show();
                        state = true;
                        i=0;
                        addFragment(new InitialFragment(),false,"two");
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }

        return state;
    }

}
