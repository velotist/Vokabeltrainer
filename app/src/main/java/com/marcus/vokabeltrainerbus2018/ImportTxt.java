package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.view.View;
import android.widget.TextView;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class ImportTxt extends AppCompatActivity {

    private final String baseDir = Environment.getExternalStorageDirectory().getAbsolutePath();
    private TextView answerTxtViewEnglish;
    private TextView answerTxtViewGerman;

    private String txtContentEnglish;
    private String txtContentGerman;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabeln_txt);
        Button btnSave = findViewById(R.id.id_btn_save);
        btnSave.setOnClickListener(clickListener);
        Button btnExit = findViewById(R.id.id_btn_exit);
        btnExit.setOnClickListener(clickListener);
        answerTxtViewEnglish = findViewById(R.id.id_txt_english);
        answerTxtViewGerman = findViewById(R.id.id_txt_german);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            String FILENAME = "vokabeln.txt";
            switch (view.getId()) {
                case R.id.id_btn_save:
                    txtContentEnglish = answerTxtViewEnglish.getText().toString();
                    txtContentGerman = answerTxtViewGerman.getText().toString();
                    save(FILENAME);
                    break;
                case R.id.id_btn_exit:
                    Intent intentExit = new Intent(ImportTxt.this, VokabelExercise.class);
                    startActivity(intentExit);
                    break;
                default:
                    break;
            }
        }
    };

    @SuppressWarnings("ResultOfMethodCallIgnored")
    private void save(String FILENAME) {
        try {
            File file = new File(baseDir + File.separator + FILENAME);

        // If file does not exist, then create it
            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter fw = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.append(txtContentEnglish).append("\n");
            answerTxtViewEnglish.setText("");
            bw.append(txtContentGerman).append("\n");
            answerTxtViewGerman.setText("");
            bw.close();

            Log.d("Success","Gespeichert");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
