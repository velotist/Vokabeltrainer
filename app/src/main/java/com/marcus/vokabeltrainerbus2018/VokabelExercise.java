package com.marcus.vokabeltrainerbus2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class VokabelExercise extends AppCompatActivity {

    private final ArrayList<String> zeile1 = new ArrayList<>();
    private final ArrayList<String> zeile2 = new ArrayList<>();
    private final ArrayList<String> alleZeilen = new ArrayList<>();
    private TextView pointsTxtView;
    private TextView questionTxtView;
    private TextView answerTxtView;
    private int pointsInt = 0;
    private int indexRandom = 0;
    private static double methodRandom = Math.random() * 2;
    private String readTextView = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabel_exercise);
        Button btnOkay = findViewById(R.id.id_btn_okay);
        btnOkay.setOnClickListener(clickListener);
        Button btnSolution = findViewById(R.id.id_btn_solution);
        btnSolution.setOnClickListener(clickListener);
        Button btnNew = findViewById(R.id.id_btn_new);
        btnNew.setOnClickListener(clickListener);
        loadPoints();
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
                case R.id.id_btn_okay: {
                    readTextView = answerTxtView.getText().toString();
                    vokabelCompare();
                    pointsTxtView.setText(String.valueOf(pointsInt));
                }
                case R.id.id_btn_solution:
                    if((int)methodRandom == 0) {
                        answerTxtView.setText(zeile2.get(indexRandom));
                    }
                    else {
                        answerTxtView.setText(zeile1.get(indexRandom));
                    }
                    break;
                case R.id.id_btn_new:
                    questionTxtView.setText(vokabelGiveBack());
                    answerTxtView.setText(R.string.dsc_blank);
                    break;
                default:
                    break;
            }
        }
    };

    private void ladeDatei() {
        try {
            InputStream vocFileIn = getApplicationContext().getAssets().open("vokabeln.txt");
            BufferedReader vocIn = new BufferedReader(new InputStreamReader(vocFileIn));
            String zeile;
        // Punktestand in Integer-Variable einlesen
            while ((zeile = vocIn.readLine()) != null) {
                alleZeilen.add(zeile);
            }
    // Ausnahmebehandlung
        } catch (IOException e) {
            Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
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
        if(readTextView.equals(zeile1.get(indexRandom)) || readTextView.equals(zeile2.get(indexRandom))) {
            pointsInt = pointsInt + 10;
            questionTxtView.setText(R.string.txt_true);
        }
    }

    private void loadPoints() {
         try {
             InputStream fIn = getApplicationContext().getAssets().open("points.txt");
             BufferedReader in = new BufferedReader(new InputStreamReader(fIn));
             String zeile;
         // Punktestand in Integer-Variable einlesen
             while ((zeile = in.readLine()) != null) {
                 pointsInt = Integer.parseInt(zeile);
             }
    // Ausnahmebehandlung
         } catch (IOException e) {
             Toast.makeText(getBaseContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
         }
    }
}
