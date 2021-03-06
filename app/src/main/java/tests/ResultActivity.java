package tests;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.paulk.MusicTutorApp.DatabaseHandler;
import com.example.paulk.MusicTutorApp.MainMenuActivity;
import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 30/09/2016.
 */
public class ResultActivity extends Activity {

    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        RatingBar ratingBar = (RatingBar)findViewById(R.id.ratingBar);
        TextView resultMessage = (TextView)findViewById(R.id.resultMessge);
        TextView message = (TextView)findViewById(R.id.message);
        TextView scoreTextView = (TextView)findViewById(R.id.score);

        Bundle bundle = getIntent().getExtras();
        int score= bundle.getInt("score");
        int testLevel = bundle.getInt("testLevel");

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        int userID = userDetails.getInt("userID", 0);

        db = new DatabaseHandler(this);
        db.addScore(score, testLevel, userID);
        int currentLevel;
        currentLevel = db.getLevelID(userID);

        String test = "The test level is " + testLevel + " and the current users level is " + currentLevel;
        Log.i("TAG", test);

        if (score >= 8){

            if(currentLevel < 5) {
                if (!(db.getLevelID(userID) > testLevel)) {
                    db.incrementLevel(userID);
                }
            }
        }

        float floatScore = (float)((score)/2.0f);
        String text = "Score: " + score + " Float score: " + floatScore + " Test Level: " + testLevel;
        Log.i("SCORE", text);

        ratingBar.setRating(floatScore);

        switch (score)
        {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
            case 6:
                    scoreTextView.setText(score + " correct");
                    resultMessage.setText("Whoops! Better Luck Next Time!");
                    message.setText("Have a look over the lessons again, study up and have another try");

                break;
            case 7:
                resultMessage.setText("Oooooo! Hard luck! So close!");
                scoreTextView.setText(score + " correct");
                break;
            case 8:
            case 9:
                if((db.getLevelID(userID) == 5) && (testLevel == 5)){

                    scoreTextView.setText(score + " correct");
                    resultMessage.setText("Well done! You've completed all the tests!");
                    message.setText("Now see if you can get a perfect score in every test! If you've already done that, then keep using the tools in this app, you can continue practicing with the tools in this app!");
                }else {
                    scoreTextView.setText(score + " correct");
                    resultMessage.setText("Well done! You've passed!");
                    message.setText("You have unlocked the next level!");
                }
                break;
            case 10:

                if((db.getLevelID(userID) == 5) && (testLevel == 5)){

                    scoreTextView.setText(score + " correct");
                    resultMessage.setText("Well done! You've completed all the tests!");
                    message.setText("Now see if you can get a perfect score in every test! If you've already done that, then keep using the tools in this app, you can continue practicing with the tools in this app!");
                }else {
                    scoreTextView.setText(score + " correct");
                    resultMessage.setText("Wow! You aced that test! Well done!");
                    message.setText("You have unlocked the next level!");
                }
        }
    }

    public void mainMenu(View v){
        Intent intent = new Intent(ResultActivity.this, MainMenuActivity.class);
        startActivity(intent);
        finish();
    }

}
