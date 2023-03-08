package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class CustomerActivity2 extends AppCompatActivity {
    public static String REVIEWO ;
    public static String SELECTEDc ;
    public static String REVIEW1 ;
    TextView tv17,tv12,tv15,tvFeed,tvFeed1;
    Button btnback,Submitbtn;
    float bar,bar1;
    String type, type1;
    RatingBar ratingBar0,ratingBar1;
    EditText RateWorker;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer2);

        tv17 = findViewById(R.id.textView17);
        tv12 = findViewById(R.id.textView12);
        tv15 = findViewById(R.id.textView15);
        tvFeed = findViewById(R.id.tvFeedback);
        tvFeed1 = findViewById(R.id.tvFeedback1);
        RateWorker = findViewById(R.id.worName);
        btnback = findViewById(R.id.back4);
        Submitbtn = findViewById(R.id.submit);
        ratingBar0 = findViewById(R.id.ratingBar);
        ratingBar1 = findViewById(R.id.ratingBar2);



        tv12.setText(ManagerClass.SELECTED);
        tv17.setText(ManagerClass.COMPANY_NAME);
        tv15.setText( "Welcome " + CustomerActivity.CUSTOMER_NAME);



        ratingBar0.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                bar = ratingBar0.getRating();
                switch ((int) bar){
                    case 0:
                        tvFeed.setText("Very poor customer service");
                        break;
                    case 1:
                        tvFeed.setText("poor customer service");
                        break;
                    case 2:
                        tvFeed.setText("Okay customer service");
                        break;
                    case 3:
                        tvFeed.setText("Good customer service");
                        break;
                    case 4:
                        tvFeed.setText("Better customer service");
                        break;
                    case 5:
                        tvFeed.setText("Best customer service");
                        break;
                }

            }
        });

        ratingBar1.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                bar1 = ratingBar1.getRating();
                switch ((int) bar1){
                    case 0:
                        tvFeed1.setText("Very Dissatisfied");
                        break;
                    case 1:
                        tvFeed1.setText("Dissatisfied");
                        break;
                    case 2:
                        tvFeed1.setText("Okay");
                        break;
                    case 3:
                        tvFeed1.setText("Satisfied");
                        break;
                    case 4:
                        tvFeed1.setText("Very Satisfied");
                        break;
                    case 5:
                        tvFeed1.setText("Very much Satisfied");
                        break;
                }

            }
        });
        Submitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                type = tvFeed.getText().toString().trim();
                type1 = tvFeed1.getText().toString().trim();
                SELECTEDc = tvFeed.getText().toString().trim();
                REVIEW1 = tvFeed1.getText().toString().trim();
                REVIEWO = RateWorker.getText().toString().trim();
                Toast.makeText(CustomerActivity2.this, "Sent", Toast.LENGTH_SHORT).show();
                tvFeed.setText("");
                tvFeed.setText("");



            }
        });




        btnback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CustomerActivity2.this, CustomerActivity.class);
                startActivity(i);
            }
        });
    }
}