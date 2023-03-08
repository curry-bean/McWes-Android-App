package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;

public class ClassSelectActivity extends AppCompatActivity {
    Button button2, Backbtn;
    RadioButton Radiobutton4,Radiobutton5,Radiobutton6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_select);

        button2 = findViewById(R.id.resetBtn);
        Radiobutton4 =  findViewById(R.id.radioButton4);
        Radiobutton5=  findViewById(R.id.radioButton5);
        Radiobutton6=  findViewById(R.id.radioButton6);
        Backbtn = findViewById(R.id.back5);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ClassSelectActivity.this, MainActivity.class);
                startActivity(i);
            }
        });





        Radiobutton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (Radiobutton4.isChecked()) {
                    Radiobutton5.setEnabled(false);
                    Radiobutton6.setEnabled(false);
                }
                Intent i = new Intent(ClassSelectActivity.this, WorkerClass.class);
                startActivity(i);
            }


        });
        Radiobutton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Radiobutton5.isChecked()) {
                    Radiobutton4.setEnabled(false);
                    Radiobutton6.setEnabled(false);
                }
                Intent i = new Intent(ClassSelectActivity.this, CustomerActivity.class);
                startActivity(i);
            }

        });

        Radiobutton6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Radiobutton6.isChecked()) {
                    Radiobutton5.setEnabled(false);
                    Radiobutton4.setEnabled(false);
                }
                Intent i = new Intent(ClassSelectActivity.this, ManagerClass.class);
                startActivity(i);
            }
        });

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(Radiobutton4.isChecked()){
                    Radiobutton4.setChecked(false);
                    Radiobutton5.setEnabled(true);
                    Radiobutton6.setEnabled(true);
                }
                if(Radiobutton5.isChecked()){
                    Radiobutton5.setChecked(false);
                    Radiobutton4.setEnabled(true);
                    Radiobutton6.setEnabled(true);
                }
                if(Radiobutton6.isChecked()){
                    Radiobutton6.setChecked(false);
                    Radiobutton4.setEnabled(true);
                    Radiobutton5.setEnabled(true);
                }


            }
        });
    }

}