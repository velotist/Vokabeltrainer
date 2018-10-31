package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class VokabelScreen extends AppCompatActivity {

    File pathSD = Environment.getExternalStorageDirectory();
    File fileName = new File(pathSD,"vokabeln.txt");

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_screen);

        if(!fileName.exists()) {
            try {
                fileName.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            Toast myToastNew = Toast.makeText(this, "file existiert", Toast.LENGTH_SHORT);
            myToastNew.show();
        }
        Button btnStart = findViewById(R.id.id_btn_start);
        btnStart.setOnClickListener(clickListener);
        Button btnImportTxt = findViewById(R.id.id_btn_vocabularyII);
        btnImportTxt.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_start:
                    Intent intentGerEn = new Intent(VokabelScreen.this, VokabelExercise.class);
                    startActivity(intentGerEn);
                    break;
                case R.id.id_btn_vocabularyII:
                    Intent intentImportTxt = new Intent(VokabelScreen.this, ImportTxt.class);
                    startActivity(intentImportTxt);
                    break;
                default:
                    break;
            }
        }
    };
}
