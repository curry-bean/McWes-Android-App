package com.example.themcwes;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;


import android.content.DialogInterface;
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

public class WorkerResLogin extends AppCompatActivity {
    TextView TextView8,TextView9,TextView13, forgotBtn;
    Button Reset, BackBtn, BtnLogin;
    EditText worName,worEmail,worPassword;
    FirebaseAuth firebaseAuth;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_worker_res);



        TextView8 = findViewById(R.id.textView8);
        TextView9 =  findViewById(R.id.textView9);
        TextView13 = findViewById(R.id.textView13);
        forgotBtn = findViewById(R.id.Forgot);
        Reset = findViewById(R.id.ResetB);
        BackBtn = findViewById(R.id.backB);
        worName = findViewById(R.id.WorkerName);
        worEmail = findViewById(R.id.workerEmail);
        worPassword = findViewById(R.id.workerPassword);
        BtnLogin = findViewById(R.id.btnLogin);


        TextView9.setText(ManagerClass.SELECTED);
        TextView8.setText(ManagerClass.COMPANY_NAME);

        firebaseAuth = FirebaseAuth.getInstance();

        Reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worName.setText("");
                worEmail.setText("");
                worPassword.setText("");

            }
        });
        BackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkerResLogin.this, WorkerClass.class);
                startActivity(i);
            }
        });

        forgotBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText resetMail = new EditText(v.getContext());
                AlertDialog.Builder passwordresetDialog = new AlertDialog.Builder(v.getContext());
                passwordresetDialog.setTitle("Reset Password");
                passwordresetDialog.setMessage("Enter your email to receive rest link");
                passwordresetDialog.setView(resetMail);

                passwordresetDialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String mail  = resetMail.getText().toString();
                        firebaseAuth.sendPasswordResetEmail(mail).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void unused) {
                                Toast.makeText(WorkerResLogin.this, "Reset link has been sent", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(WorkerResLogin.this, "Error reset link is not sent" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

                    }
                });

                passwordresetDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                passwordresetDialog.create().show();

            }
        });

        TextView13.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkerResLogin.this, WorkerRegistration.class);
                startActivity(i);
            }
        });

        BtnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = worEmail.getText().toString().trim();
                String userName = worName.getText().toString().trim();
                String pass = worPassword.getText().toString().trim();
                if( TextUtils.isEmpty(userEmail)){
                    worEmail.setError( "Email is required!" );
                    return;
                }
                if( TextUtils.isEmpty(userName)){
                    worName.setError( "Name is required!" );
                    return;
                }

                if( TextUtils.isEmpty(pass)){
                    worPassword.setError("Password is required");
                    return;
                }
                if(pass.length()<7){
                    worPassword.setError("Password must be greater than 7 characters");
                    return;
                }

                firebaseAuth.signInWithEmailAndPassword(userEmail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            if(firebaseAuth.getCurrentUser().isEmailVerified()){
                            Toast.makeText(WorkerResLogin.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(WorkerResLogin.this, WorkerActivity2.class));
                            }else{
                                Toast.makeText(WorkerResLogin.this, "Please verify your email", Toast.LENGTH_SHORT).show();
                            }

                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(WorkerResLogin.this, "Login Failed", Toast.LENGTH_SHORT).show();

                        }
                    });



            }
        });

    }
}