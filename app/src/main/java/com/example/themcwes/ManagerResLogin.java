package com.example.themcwes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ManagerResLogin extends AppCompatActivity {
    TextView Textview10, Textview11;
    Button reset;
    Button backBtn, loginbtn;
    EditText manName,manEmail,manPassword;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_manager_res);

        Textview10 = findViewById(R.id.textView10);
        Textview11 =  findViewById(R.id.textView11);
        reset = (Button) findViewById(R.id.resetbtn);
        backBtn = (Button) findViewById(R.id.Btnback);
        manName = findViewById(R.id.managerName);
        manEmail = findViewById(R.id.managerEmail);
        manPassword = findViewById(R.id.managerPassword);
        loginbtn = findViewById(R.id.Btnlogin);
        firebaseAuth = FirebaseAuth.getInstance();


        Textview11.setText(ManagerClass.SELECTED);
        Textview10.setText(ManagerClass.COMPANY_NAME);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                manName.setText("");
                manEmail.setText("");
                manPassword.setText("");

            }
        });
        backBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerResLogin.this, ManagerClass.class);
                startActivity(i);
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userEmail = manEmail.getText().toString().trim();
                String userName = manName.getText().toString().trim();
                String pass = manPassword.getText().toString().trim();
                if( TextUtils.isEmpty(userEmail)){
                    manEmail.setError( "Email is required!" );
                }
                if( TextUtils.isEmpty(userName)){
                    manName.setError( "Name is required!" );
                }

                if( TextUtils.isEmpty(pass)){
                    manPassword.setError("Password is required");
                }
                if(pass.length()<7){
                    manPassword.setError("Password must be greater than 7 characters");
                    return;
                }
                firebaseAuth.signInWithEmailAndPassword(userEmail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {
                        Toast.makeText(ManagerResLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(ManagerResLogin.this, ManagerActivity2.class));

                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ManagerResLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();

                    }
                });



            }
        });
        }

    }
