package com.example.themcwes;






import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;

import com.google.firebase.auth.PhoneAuthProvider;
import com.hbb20.CountryCodePicker;

import java.util.concurrent.TimeUnit;
public class CustomerActivity extends AppCompatActivity {
    public static final String TAG = "TAG";
    public static String CUSTOMER_NAME;
    public static String CUSTOMER_NUMBER;
    CountryCodePicker ccp;
    EditText CusName, CusNumber, otpCode;
    Button sendOtp, reset1, back1;
    FirebaseAuth firebaseAuth;
    ProgressBar progressBar;
    String verificationId;
    TextView resend;
    PhoneAuthProvider.ForceResendingToken token;
    Boolean verificationInProgress = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer);


        CusName = findViewById(R.id.managerName);
        CusNumber = findViewById(R.id.CustomerPhone);
        otpCode = findViewById(R.id.Otpcode);
        ccp = findViewById(R.id.Ccp);
        progressBar = findViewById(R.id.progressBar);
        sendOtp = findViewById(R.id.SendO);
        reset1 = findViewById(R.id.ResetB);
        back1 = findViewById(R.id.backB);

        firebaseAuth = FirebaseAuth.getInstance();


        reset1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CusName.setText("");
                CusNumber.setText("");
                otpCode.setText("");


            }
        });
        back1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerActivity.this, ClassSelectActivity.class);
                startActivity(i);
            }
        });

        sendOtp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!verificationInProgress) {
                    CUSTOMER_NAME = CusName.getText().toString().trim();


                    if (TextUtils.isEmpty(CUSTOMER_NAME)) {
                        CusName.setError("Customer name is required!");
                        return;
                    }

                    CUSTOMER_NUMBER = "+" + ccp.getSelectedCountryCode() + CusNumber.getText().toString();
                    if (TextUtils.isEmpty(CUSTOMER_NUMBER)) {
                        CusNumber.setError("Phone number required");
                        return;
                    }
                    if (CusNumber.length() != 9) {
                        CusNumber.setError("Format is 7******** and Phone number should be 9 digits");
                        return;
                    }


                    Log.d(TAG, "onClick: Phone no->" + CUSTOMER_NUMBER);
                    progressBar.setVisibility(View.VISIBLE);
                    Toast.makeText(CustomerActivity.this, "Sending OTP", Toast.LENGTH_SHORT).show();
                    requestOtp(CUSTOMER_NUMBER);

                } else {
                    String userOtp = otpCode.getText().toString();

                    if (TextUtils.isEmpty(userOtp)) {
                        otpCode.setError("Otp code is required!");
                        return;
                    }


                    if (userOtp.length() != 6) {
                        otpCode.setError("Write a 6 digit otp code");
                    }


                    PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, userOtp);
                    verifyAuthentication(credential);


                }
            }
        });
    }

    private void verifyAuthentication(PhoneAuthCredential credential) {
        firebaseAuth.signInWithCredential(credential).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(CustomerActivity.this, "Authentication successful", Toast.LENGTH_SHORT).show();

                    Intent i = new Intent(CustomerActivity.this, CustomerActivity2.class);
                    startActivity(i);

                } else {
                    Toast.makeText(CustomerActivity.this, "Authentication failed", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void requestOtp(String phoneNo) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(phoneNo, 60L, TimeUnit.SECONDS, CustomerActivity.this, new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                verificationId = s;
                token = forceResendingToken;
                progressBar.setVisibility(View.GONE);
                otpCode.setVisibility(View.VISIBLE);
                sendOtp.setText("Verify Otp");
                verificationInProgress = true;


            }


            @Override
            public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {
                verifyAuthentication(phoneAuthCredential);


            }

            @Override
            public void onVerificationFailed(FirebaseException e) {
                Toast.makeText(CustomerActivity.this, "Cannot create account" + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }
}







