package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

@SuppressWarnings("ResultOfMethodCallIgnored")
public class VokabelScreen extends AppCompatActivity {

    private final File pathSD = Environment.getExternalStorageDirectory();
    private final File fileName = new File(pathSD,"vokabeln.txt");

    private final ArrayList<String> alleZeilen = new ArrayList<>();

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
            Toast myToastNew = Toast.makeText(this, "File exists", Toast.LENGTH_SHORT);
            myToastNew.show();
        }
        if(areVocabsSaved()) {
            Toast.makeText(this, "Hello", Toast.LENGTH_SHORT).show();
        } else  {
            Intent intentFirstVocab = new Intent(VokabelScreen.this, FirstVocab.class);
            startActivity(intentFirstVocab);
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

    private boolean areVocabsSaved() {
        String line;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            while ((line = bufferedReader.readLine()) != null) {
                alleZeilen.add(line);
            }
            fileInputStream.close();
            bufferedReader.close();
        } catch(FileNotFoundException ex) {
            Toast.makeText(this, "File not found exception", Toast.LENGTH_SHORT).show();
            // Ausnahmebehandlung
        } catch (IOException e) {
            Toast.makeText(this, "All loaded", Toast.LENGTH_SHORT).show();
        }
        return alleZeilen.size() != 0;
    }
}
