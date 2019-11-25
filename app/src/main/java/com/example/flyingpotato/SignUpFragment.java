package com.example.flyingpotato;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpFragment extends Fragment {

    private View view;

    private Button signup, back;
    private EditText username, password;

    private DatabaseReference register;

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
                addUser();
                Intent intent = new Intent(getActivity(), MainActivity.class);
                startActivity(intent);
                getActivity().overridePendingTransition(0, 0);
                getActivity().finish();


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
    public void addUser() {
        String name = username.getText().toString().trim();
        String passw = password.getText().toString().trim();
        if(!TextUtils.isEmpty(name)) {
            String id = register.push().getKey();
            Users user = new Users(id, name, passw);
            register.child(id).setValue(user);
            Toast.makeText(getActivity(), "User signup success!", Toast.LENGTH_LONG).show();
        }else
            Toast.makeText(getActivity(),"Please fill all the blanks!", Toast.LENGTH_LONG).show();
    }

}
