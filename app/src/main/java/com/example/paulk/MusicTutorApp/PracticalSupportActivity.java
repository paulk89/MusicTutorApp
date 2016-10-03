package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by paulk on 27/06/2016.
 */
public class PracticalSupportActivity extends Activity {

    Button tuner, metronome, chordCharts, strumPatterns, mainMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practical_support);

        tuner = (Button) findViewById(R.id.tuner);
        metronome = (Button) findViewById(R.id.metronome);
        chordCharts = (Button) findViewById(R.id.chord_charts);
        strumPatterns = (Button) findViewById(R.id.strum_patterns);
        mainMenu = (Button) findViewById(R.id.main_menu);

        tuner.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(PracticalSupportActivity.this,
                        TunerActivity.class);
                startActivity(i);
            }
        });

        chordCharts.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(PracticalSupportActivity.this,
                        ChordChartActivity.class);
                startActivity(i);
            }
        });

        strumPatterns.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(PracticalSupportActivity.this,
                        StrummingPatternsActivity.class);
                startActivity(i);
            }
        });

        metronome.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(PracticalSupportActivity.this,
                        MetronomeActivity.class);
                startActivity(i);
            }
        });

        mainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent openMainActivity= new Intent(PracticalSupportActivity.this, MainMenuActivity.class);
                openMainActivity.setFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                startActivity(openMainActivity);

                finish();
            }
        });

    }
}