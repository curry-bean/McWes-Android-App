package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity {

    Button Next, Exitbtn;
    ImageView imageView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Next= findViewById(R.id.nextBtn);
        Exitbtn = (Button) findViewById(R.id.back5);
        imageView = findViewById(R.id.imageView8);

        imageView.getLayoutParams().width = 3000;

        Exitbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showMyAlertDialog(MainActivity.this );
            }
        });

        Next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, ClassSelectActivity.class);
                startActivity(i);
            }
        });

        MediaPlayer tone  = MediaPlayer.create(MainActivity.this, R.raw.tone);
        tone.start();
    }
    private void showMyAlertDialog(MainActivity mainActivity) {
        new AlertDialog.Builder(mainActivity)

                .setTitle("Message from MCWES")
                .setMessage("Do you want to close this application ?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        finishAffinity();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                })
                .create()
                .show();
    }

}