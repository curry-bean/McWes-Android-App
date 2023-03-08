package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerCarLogin extends AppCompatActivity {
    Button butbac;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.manager_car_login);

        butbac.findViewById(R.id.buttonback);

        butbac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerCarLogin.this, ManagerClass.class);
                startActivity(i);
            }
        });
    }
}