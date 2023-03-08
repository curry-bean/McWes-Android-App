package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;

public class ManagerClass extends AppCompatActivity {
    public static String COMPANY_NAME ;
    public static String SELECTED ;
    Button button1 , Backbtn;
    RadioButton Radiobutton1,Radiobutton2,Radiobutton3;
    EditText compName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.class_manager);




        button1 = findViewById(R.id.button1);
        Radiobutton1 =  findViewById(R.id.radioButton1);
        Radiobutton2=  findViewById(R.id.radioButton2);
        Radiobutton3=  findViewById(R.id.radioButton3);
        compName = findViewById(R.id.editTextName);
        Backbtn =  findViewById(R.id.backBtn);

        Backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerClass.this, ClassSelectActivity.class);
                startActivity(i);
            }
        });




        Radiobutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(compName.getText())){
                    compName.setError( "Name of company is required!" );
                    Radiobutton1.setChecked(false);
                    Radiobutton2.setEnabled(true);
                    Radiobutton3.setEnabled(true);
                }else if(Radiobutton1.isChecked()){
                    Radiobutton2.setEnabled(false);
                    Radiobutton3.setEnabled(false);
                    SELECTED = Radiobutton1.getText().toString().trim();
                    COMPANY_NAME = compName.getText().toString().trim();
                    Intent i = new Intent(ManagerClass.this, ManagerResLogin.class);
                    startActivity(i);
                }

            }



        });
        Radiobutton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(compName.getText())){
                    compName.setError( "Name of company is required!" );
                    Radiobutton2.setChecked(false);
                    Radiobutton1.setEnabled(true);
                    Radiobutton3.setEnabled(true);
                }else if (Radiobutton2.isChecked()){
                    Radiobutton1.setEnabled(false);
                    Radiobutton3.setEnabled(false);
                    SELECTED = Radiobutton2.getText().toString().trim();
                    COMPANY_NAME = compName.getText().toString().trim();
                    startActivity(new Intent(getApplicationContext(), ManagerApaLogin.class));
                }
            }


        });

        Radiobutton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if( TextUtils.isEmpty(compName.getText())){
                    compName.setError( "Name of company is required!" );
                    Radiobutton3.setChecked(false);
                    Radiobutton2.setEnabled(true);
                    Radiobutton1.setEnabled(true);
                }else if (Radiobutton3.isChecked()) {
                Radiobutton2.setEnabled(false);
                Radiobutton1.setEnabled(false);
                    SELECTED = Radiobutton3.getText().toString().trim();
                    COMPANY_NAME = compName.getText().toString().trim();
                    Intent i = new Intent(ManagerClass.this, ManagerApaLogin.class);
                    startActivity(i);
                }
            }


        });

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                compName.setText("");
                if(Radiobutton1.isChecked()){
                    Radiobutton1.setChecked(false);
                    Radiobutton2.setEnabled(true);
                    Radiobutton3.setEnabled(true);
                }
                if(Radiobutton2.isChecked()){
                    Radiobutton2.setChecked(false);
                    Radiobutton1.setEnabled(true);
                    Radiobutton3.setEnabled(true);
                }
                if(Radiobutton3.isChecked()){
                    Radiobutton3.setChecked(false);
                    Radiobutton2.setEnabled(true);
                    Radiobutton1.setEnabled(true);
                }


            }
        });
    }



    public static String getValue() {
        return SELECTED;
    }
    public static String getName() {
        return COMPANY_NAME;
    }

}