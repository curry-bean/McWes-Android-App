package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ManagerApaLogin extends AppCompatActivity {
    Button b4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager_apa_login);

        b4 = findViewById(R.id.back6);

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(ManagerApaLogin.this, ManagerClass.class);
                startActivity(i);
            }
        });
    }
}