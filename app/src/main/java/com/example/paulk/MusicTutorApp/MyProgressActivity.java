package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by paulk on 01/10/2016.
 */
public class MyProgressActivity extends Activity {

    DatabaseHandler db;
    TextView level1Score,level2Score, level3Score, level4Score, level5Score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_progress);

        level1Score = (TextView)findViewById(R.id.highScoreTVScore1);
        level2Score = (TextView)findViewById(R.id.highScoreTVScore2);
        level3Score = (TextView)findViewById(R.id.highScoreTVScore3);
        level4Score = (TextView)findViewById(R.id.highScoreTVScore4);
        level5Score = (TextView)findViewById(R.id.highScoreTVScore5);

    }
}
