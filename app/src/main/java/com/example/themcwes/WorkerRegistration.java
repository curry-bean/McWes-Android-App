package com.example.themcwes;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.hbb20.CountryCodePicker;

import java.util.HashMap;
import java.util.Map;

public class WorkerRegistration extends AppCompatActivity {


    Button Reset1, BackBtn1, BtnRegister;
    EditText worName1,worEmail1,worPassword1, worConfirm,worNumber;
    FirebaseAuth firebaseAuth;
    Spinner spinner;
    CountryCodePicker ccp;
    FirebaseFirestore fstore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_worker);



        spinner = findViewById(R.id.spinner_languages);
        Reset1 = findViewById(R.id.Reset1);
        BackBtn1 = findViewById(R.id.back2);
        worName1 = findViewById(R.id.WorkerName1);
        worNumber = findViewById(R.id.WorkerPhone);
        ccp = findViewById(R.id.Ccp1);
        worEmail1 = findViewById(R.id.workerEmail1);
        worPassword1 = findViewById(R.id.workerPassword1);
        worConfirm = findViewById(R.id.workerConPassword);
        BtnRegister = findViewById(R.id.btnRegister);

        firebaseAuth = FirebaseAuth.getInstance();
        fstore = FirebaseFirestore.getInstance();


        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.Work_available, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        spinner.setAdapter(adapter);

        Reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                worName1.setText("");
                worEmail1.setText("");
                worPassword1.setText("");

            }
        });
        BackBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkerRegistration.this, WorkerResLogin.class);
                startActivity(i);
            }
        });




        BtnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = worEmail1.getText().toString().trim();
                String userName = worName1.getText().toString().trim();
                String item = spinner.getSelectedItem().toString();
                String phoneNo = "+" + ccp.getSelectedCountryCode() + worNumber.getText().toString().trim();
                String pass = worPassword1.getText().toString().trim();
                String Conpass = worConfirm.getText().toString().trim();
                if (TextUtils.isEmpty(userEmail)) {
                    worEmail1.setError("Email is required!");
                    return;
                }
                if (TextUtils.isEmpty(userName)) {
                    worName1.setError("Name is required!");
                    return;
                }
                if (TextUtils.isEmpty(phoneNo)) {
                    worNumber.setError("Phone number required");
                    return;
                }
                if (worNumber.length() != 9) {
                    worNumber.setError("Format is 7******** and Phone number should be 9 digits");
                    return;
                }

                if (TextUtils.isEmpty(pass)) {
                    worPassword1.setError("Password is required");
                    return;
                }
                if (!pass.equals(Conpass)){
                    worConfirm.setError("Password do not match");
                    return;
                }
                if(pass.length()<7){
                    worPassword1.setError("Password must be greater than 7 characters");
                    return;
                }
                firebaseAuth.createUserWithEmailAndPassword(userEmail, pass).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                            @Override
                            public void onSuccess(AuthResult authResult) {
                                FirebaseUser fuser = firebaseAuth.getCurrentUser();
                                fuser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Toast.makeText(WorkerRegistration.this, "Verification has been sent", Toast.LENGTH_SHORT).show();


                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: Email not sent" + e.getMessage());
                                    }
                                });
                                Toast.makeText(WorkerRegistration.this, "Worker created", Toast.LENGTH_SHORT).show();
                                userID = firebaseAuth.getCurrentUser().getUid();
                                DocumentReference documentReference = fstore.collection("Workers").document(userID);
                                Map<String,Object> Worker = new HashMap<>();
                                Worker.put("Full name",userName);
                                Worker.put("Area of work", item);
                                Worker.put("Email",userEmail);
                                Worker.put("phone number",phoneNo);
                                documentReference.set(Worker).addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void unused) {
                                        Log.d(TAG, "onSuccess: Worker profile is created for" + userID);

                                    }
                                }).addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.d(TAG, "onFailure: " + e.getMessage());

                                    }
                                });
                                startActivity(new Intent(WorkerRegistration.this, WorkerResLogin.class));


                            }



                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(WorkerRegistration.this, "Error" + e.getMessage(), Toast.LENGTH_SHORT).show();
                            }
                        });

            }

            });

        }
    }

