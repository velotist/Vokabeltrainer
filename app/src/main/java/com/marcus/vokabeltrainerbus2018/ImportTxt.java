package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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
    final static String fileName = "vokabeln.txt";
    final static String path = Environment.getExternalStorageDirectory().getAbsolutePath() + "/" ;
    final static String TAG = FileHelper.class.getName();

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
                if ((saveToFile(answerTxtViewEnglish.getText().toString())) && (saveToFile(answerTxtViewGerman.getText().toString()))) {
                    Toast.makeText(ImportTxt.this, "Saved", Toast.LENGTH_SHORT).show();
                    answerTxtViewEnglish.setText("");
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

    public static boolean saveToFile(String data){
        try {
            new File(path  ).mkdir();
            File file = new File(path + fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
            FileOutputStream fileOutputStream = new FileOutputStream(file,true);
            fileOutputStream.write((data + System.getProperty("line.separator")).getBytes());

            return true;
        }  catch(FileNotFoundException ex) {
            Log.d(TAG, ex.getMessage());
        }  catch(IOException ex) {
            Log.d(TAG, ex.getMessage());
        }
        return  false;
    }
}
