package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
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

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        int userID = userDetails.getInt("userID", 0);

        //get users current level
        db = new DatabaseHandler(this);
        int level = db.getLevelID(userID);

        int [] highScores = {0,0,0,0,0};

        //get highest score for each level
        for (int i = 1; i <= level; i++){
            highScores[i-1] = db.getHighScores(userID, i);
        }

        //display data in the text views
        level1Score.setText(String.valueOf(highScores[0]));
        level2Score.setText(String.valueOf(highScores[1]));
        level3Score.setText(String.valueOf(highScores[2]));
        level4Score.setText(String.valueOf(highScores[3]));
        level5Score.setText(String.valueOf(highScores[4]));

    }
}
