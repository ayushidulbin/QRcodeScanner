package com.example.ayushi.loginpage;

import android.content.Intent;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import android.widget.Toast;


import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
        private EditText Name;
        private EditText Password;
        private Button Login;
        private TextView userRegistration;
//        private FirebaseAuth firebaseAuth;
        private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


//        Name=(EditText)findViewById(R.id.etname);
//        Password=(EditText)findViewById(R.id.etpassword);
//        Login=(Button)findViewById(R.id.btnLogin);
//        userRegistration=(TextView)findViewById(R.id.tvRegister);

//            Login.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    validate(Name.getText().toString(), Password.getText().toString());
//                }
//
//            });
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        } else{
//            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//        }




        auth =FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!= null){
            startActivity(new Intent(MainActivity.this, SecondActivity.class));
            finish();
        }
       // setContentView(R.layout.activity_login);
        Name=(EditText)findViewById(R.id.etname);
        Password=(EditText)findViewById(R.id.etpassword);
        Login=(Button)findViewById(R.id.btnLogin);
        userRegistration=(TextView)findViewById(R.id.tvRegister);

        auth = FirebaseAuth.getInstance();
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Name.getText().toString();
                final String password = Password.getText().toString();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter email address !",Toast.LENGTH_LONG).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password ! ",Toast.LENGTH_LONG).show();
                    return;
                }


                //auth user
                auth.signInWithEmailAndPassword(email,password).addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(!task.isSuccessful()){
                            //there was an error
                            if(password.length()<6){
                                Toast.makeText(MainActivity.this, "Length of password should be more than 6", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(MainActivity.this,"failed",Toast.LENGTH_LONG).show();
                            }
                        }else {
                            Intent intent = new Intent(MainActivity.this,MainActivity.class);
                            startActivity(intent);
                            finish();
                        }

                    }
                });
            }
        });





        userRegistration.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
                }
            });

    }
//    private void validate(String username,String userPassword) {
//        firebaseAuth = FirebaseAuth.getInstance();
//        FirebaseUser user = firebaseAuth.getCurrentUser();
//        if (user != null) {
//            finish();
//            startActivity(new Intent(MainActivity.this, SecondActivity.class));
//        } else{
//            startActivity(new Intent(MainActivity.this, RegistrationActivity.class));
//        }
//
//            firebaseAuth.signInWithEmailAndPassword(username, userPassword).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//                @Override
//                public void onComplete(@NonNull Task<AuthResult> task) {
//                    if (task.isSuccessful()) {
//                        Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                        startActivity(new Intent(MainActivity.this, SecondActivity.class));
//                    } else {
//                        Toast.makeText(MainActivity.this, "Login Failed...Please Register", Toast.LENGTH_SHORT).show();
//                    }
//                }
//            });
//        }

}
