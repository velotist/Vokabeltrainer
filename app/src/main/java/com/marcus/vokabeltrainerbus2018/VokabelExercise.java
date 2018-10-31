package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VokabelExercise extends AppCompatActivity {

    private final ArrayList<String> zeile1 = new ArrayList<>();
    private final ArrayList<String> zeile2 = new ArrayList<>();
    private final ArrayList<String> alleZeilen = new ArrayList<>();
    private TextView questionTxtView;
    private TextView answerTxtView;
    private TextView pointsTxtView;
    private TextView solutionTxtView;
    private int pointsInt = 0;
    private int indexRandom = 0;
    private static double methodRandom = Math.random() * 2;
    private final File pathSD = Environment.getExternalStorageDirectory();
    private final File fileName = new File(pathSD,"vokabeln.txt");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_exercise);
        Button btnSolution = findViewById(R.id.id_btn_solution);
        btnSolution.setOnClickListener(clickListener);
        Button btnNew = findViewById(R.id.id_btn_new);
        btnNew.setOnClickListener(clickListener);
        Button btnVocab = findViewById(R.id.id_btn_vocabularyII);
        btnVocab.setOnClickListener(clickListener);
        Button btnAmIRight = findViewById(R.id.id_btn_habIchRecht);
        btnAmIRight.setOnClickListener(clickListener);
        solutionTxtView = findViewById(R.id.id_txt_solution);
        pointsTxtView = findViewById(R.id.id_txt_points);
        pointsTxtView.setText(String.valueOf(pointsInt));
        ladeDatei();
        ladeInArray();
        questionTxtView = findViewById(R.id.id_txt_question);
        questionTxtView.setText(vokabelGiveBack());
        answerTxtView = findViewById(R.id.id_txt_answer);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_solution:
                    if((int)methodRandom == 0) {
                        solutionTxtView.setText(zeile2.get(indexRandom));
                    }
                    else {
                        solutionTxtView.setText(zeile1.get(indexRandom));
                    }
                    break;
                case R.id.id_btn_new:
                    questionTxtView.setText(vokabelGiveBack());
                    answerTxtView.setText(R.string.dsc_blank);
                    solutionTxtView.setText(R.string.dsc_blank);
                    break;
                case R.id.id_btn_vocabularyII:
                    Intent intentVocabulary = new Intent(VokabelExercise.this, ImportTxt.class);
                    startActivity(intentVocabulary);
                    break;
                case R.id.id_btn_habIchRecht:
                    vokabelCompare();
                    pointsTxtView.setText(String.valueOf(pointsInt));
                default:
                    break;
            }
        }
    };

    private void ladeDatei() {
        String line;
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            questionTxtView = findViewById(R.id.id_txt_question);
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
    }

// Methode zum Zuweisen der Zeile 1 in einen Array zeile1
// und der Zeile 2 in einen Array zeile2
    private void ladeInArray() {
        for (int i = 0; i < alleZeilen.size(); i = i + 2) {
            zeile1.add(alleZeilen.get(i));
        }
        for (int i = 1; i < alleZeilen.size(); i = i + 2) {
            zeile2.add(alleZeilen.get(i));
        }
    }

    private int vokabelRandomBack() {
        int lengthOfArray = zeile1.size();
        indexRandom = (int) (Math.random() * lengthOfArray);
        return indexRandom;
    }

    private String vokabelGiveBack() {
        methodRandom = Math.random() * 2;
        String vokabel;
        if ((int)methodRandom == 0) {
            vokabel = zeile1.get(vokabelRandomBack());
        } else {
            vokabel = zeile2.get(vokabelRandomBack());
        } return vokabel;
    }

    private void vokabelCompare() {
        String readTextView = answerTxtView.getText().toString();
        if(readTextView.equals(zeile1.get(indexRandom)) || readTextView.equals(zeile2.get(indexRandom))) {
            pointsInt = pointsInt + 10;
            questionTxtView.setText(R.string.txt_true);
            solutionTxtView.setText("");
        } else {
            solutionTxtView.setText(R.string.txt_wrong);
        }
    }
}
