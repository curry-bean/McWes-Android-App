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

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

public class WorkerActivity2 extends AppCompatActivity {
    public static String WORKSUG ;
    public static String SELECTEDR ;

    Button Submit1, Backbtn;

    FirebaseAuth firebaseAuth;
    RatingBar RatingBar3;
    TextView tvFeed;
    float mine;

    String temp;
    EditText Suggestion;
    FirebaseFirestore fStore;
    TextView tvWorN, tvWorE, tvWorP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_worker2);


        tvWorN = findViewById(R.id.tvWorName);
        tvWorE = findViewById(R.id.tvWorEmail);
        tvWorP = findViewById(R.id.tvWorPhone);
        tvFeed = findViewById(R.id.tvFeedback2);
        Suggestion = findViewById(R.id.worNameS);
        RatingBar3 = findViewById(R.id.ratingBar3);

        Submit1 = findViewById(R.id.submit1);
        Backbtn = findViewById(R.id.back5);





        firebaseAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();


        DocumentReference documentReference = fStore.collection("Workers").document(firebaseAuth.getCurrentUser().getUid());
        documentReference.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                if (documentSnapshot.exists()) {
                    tvWorN.setText(documentSnapshot.getString("Full name"));
                    tvWorE.setText(documentSnapshot.getString("Email"));
                    tvWorP.setText(documentSnapshot.getString("phone number"));

                }

            }
        });

        RatingBar3.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                mine = RatingBar3.getRating();
                switch ((int) mine) {
                    case 0:
                        tvFeed.setText("Very poor work place");
                        break;
                    case 1:
                        tvFeed.setText("Poor work place");
                        break;
                    case 2:
                        tvFeed.setText("Normal work place");
                        break;
                    case 3:
                        tvFeed.setText("Good work place");
                        break;
                    case 4:
                        tvFeed.setText("Very good work place");
                        break;
                    case 5:
                        tvFeed.setText("Excellent work place");
                        break;
                }

            }
        });

        Submit1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                temp = tvFeed.getText().toString().trim();
                Toast.makeText(WorkerActivity2.this, "Your Rating: \n " + temp + "\n" + Suggestion.getText(), Toast.LENGTH_SHORT).show();
                SELECTEDR = tvFeed.getText().toString().trim();
                WORKSUG = Suggestion.getText().toString().trim();

            }
        });


        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(WorkerActivity2.this, MainActivity.class);
                startActivity(i);
            }
        });


    }

}