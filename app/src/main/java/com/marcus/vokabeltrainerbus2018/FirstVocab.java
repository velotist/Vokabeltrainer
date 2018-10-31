package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class FirstVocab extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_first);
        Button btnOkay = findViewById(R.id.id_btn_okay);
        btnOkay.setOnClickListener(clickListener);
    }
    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_okay:
                    Intent intentGoTo = new Intent(FirstVocab.this, ImportTxt.class);
                    startActivity(intentGoTo);
                    break;
                default:
                    break;
            }
        }
    };
}
