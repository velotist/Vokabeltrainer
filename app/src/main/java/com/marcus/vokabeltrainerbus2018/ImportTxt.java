package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ImportTxt extends AppCompatActivity {

    private TextView answerTxtViewEnglish;
    private TextView answerTxtViewGerman;
    Button btnSave;
    Button btnExit;
    File pathSD = Environment.getExternalStorageDirectory();
    File fileName = new File(pathSD,"vokabeln.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabeln_txt);
        answerTxtViewEnglish = findViewById(R.id.id_txt_english);
        answerTxtViewGerman = findViewById(R.id.id_txt_german);
        btnSave = findViewById(R.id.id_btn_save);
        btnExit = findViewById(R.id.id_btn_exit);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if((!answerTxtViewEnglish.getText().toString().isEmpty()) && (!answerTxtViewGerman.getText().toString().isEmpty())) {
                    saveToFile(answerTxtViewEnglish.getText().toString());
                    saveToFile(answerTxtViewGerman.getText().toString());
                    Toast.makeText(ImportTxt.this, "Saved", Toast.LENGTH_SHORT).show();
                    answerTxtViewEnglish.setText("");
                    answerTxtViewGerman.setText("");
                } else {
                    Toast.makeText(ImportTxt.this, "Bitte Englisch und Deutsch eintragen!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnExit.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_exit:
                    Intent intentExit = new Intent(ImportTxt.this, VokabelExercise.class);
                    startActivity(intentExit);
                    break;
                default:
                    break;
            }
        }
    };

    public boolean saveToFile(String data){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(fileName,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());
            return true;
        }  catch(FileNotFoundException ex) {
            Toast.makeText(ImportTxt.this, "File not found exception", Toast.LENGTH_SHORT).show();
        }  catch(IOException ex) {
            Toast.makeText(ImportTxt.this, "IO Exception", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}
