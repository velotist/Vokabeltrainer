package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class VokabelScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_screen);
        Button btnStart = findViewById(R.id.id_btn_start);
        btnStart.setOnClickListener(clickListener);
        Button btn_importTxt = findViewById(R.id.id_btn_importTxt);
        btn_importTxt.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_start:
                    Intent intentGerEn = new Intent(VokabelScreen.this, VokabelExercise.class);
                    startActivity(intentGerEn);
                    break;
                case R.id.id_btn_importTxt:
                    Intent intentImportTxt = new Intent(VokabelScreen.this, importTxt.class);
                    startActivity(intentImportTxt);
                    break;
                default:
                    break;
            }
        }
    };
}
