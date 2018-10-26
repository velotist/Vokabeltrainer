package com.marcus.vokabeltrainerbus2018;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.view.View;

public class ImportTxt extends AppCompatActivity {
    //ReadWriteTxtFile vokabelnTxt = new ReadWriteTxtFile();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vokabeln_txt);
        Button btnSave = findViewById(R.id.id_btn_save);
        btnSave.setOnClickListener(clickListener);
        Button btnExit = findViewById(R.id.id_btn_exit);
        btnExit.setOnClickListener(clickListener);
    }

    private final View.OnClickListener clickListener = new View.OnClickListener() {

        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.id_btn_save:
                    Intent intentSave= new Intent(ImportTxt.this, ReadWriteTxtFile.class);
                    startActivity(intentSave);
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
}
