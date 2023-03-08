package com.example.themcwes;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class ManagerActivity2 extends AppCompatActivity {
    TextView tvwor1,tv2,tv3,tvcus1, tvrate1,tvrate2,tvRate3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manager2);

        tvwor1 = findViewById(R.id.tvSugg);
        tvrate1 = findViewById(R.id.tvRate);
        tv2 = findViewById(R.id.textView5);
        tv3 = findViewById(R.id.textView19);
        tvcus1 = findViewById(R.id.tvSug);
        tvrate2 = findViewById(R.id.tvRate1);
        tvRate3 = findViewById(R.id.tvSugR);

        tv2.setText(ManagerClass.SELECTED);
        tv3.setText(ManagerClass.COMPANY_NAME);

        tvwor1.setText(WorkerActivity2.WORKSUG);
        tvrate1.setText(WorkerActivity2.SELECTEDR);
        tvcus1.setText(CustomerActivity2.SELECTEDc);
        tvrate2.setText(CustomerActivity2.REVIEWO);
        tvRate3.setText(CustomerActivity2.REVIEW1);

    }


}