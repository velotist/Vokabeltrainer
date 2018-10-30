package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;

public class VokabelScreen extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_screen);
        File file = Environment.getExternalStorageDirectory();
        File txtFile = new File(file.getAbsolutePath() + File.separator + "vokabeln.txt");
        if(!txtFile.exists()) {
            txtFile.mkdirs();
            try {
                txtFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else {
            Toast myToast = Toast.makeText(this, "File exists", Toast.LENGTH_SHORT);
            myToast.show();
        }
        Button btnStart = findViewById(R.id.id_btn_start);
        btnStart.setOnClickListener(clickListener);
        Button btnimportTxt = findViewById(R.id.id_btn_vocabularyII);
        btnimportTxt.setOnClickListener(clickListener);
        TextView txtVar = findViewById(R.id.id_txt_variable);
        txtVar.setText(txtFile.toString());
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
