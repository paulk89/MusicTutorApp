package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


/**
 * Created by paulk on 01/06/2016.
 */
public class MainMenuActivity extends Activity {

    Button logout, level1, level2, level3, level4, level5, practicalSupport;
    DatabaseHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        logout = (Button) findViewById(R.id.logout);
        level1 = (Button) findViewById(R.id.level1);
        level2 = (Button) findViewById(R.id.level2);
        level3 = (Button) findViewById(R.id.level3);
        level4 = (Button) findViewById(R.id.level4);
        level5 = (Button) findViewById(R.id.level5);
        practicalSupport = (Button) findViewById(R.id.practical_support);

        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        final String currentUser = userDetails.getString("username", "");

        db = new DatabaseHandler(getApplicationContext());
        int currentLevel = db.getLevelID(currentUser);
        Toast.makeText(getApplicationContext(),
                currentUser + " is at level " + currentLevel, Toast.LENGTH_LONG)
                .show();

        /*Bundle extras = getIntent().getExtras();
        final String currentUser = extras.getString("currentUser");*/

            if (currentLevel < 2) {
                level2.setEnabled(false);
                level3.setEnabled(false);
                level4.setEnabled(false);
                level5.setEnabled(false);
            }

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    Toast.makeText(getApplicationContext(),
                            currentUser + ", you have logged out Successfully", Toast.LENGTH_LONG)
                            .show();
                    Intent i = new Intent(MainMenuActivity.this,
                            HomeActivity.class);
                    startActivity(i);
                    finish();
                }
        });

        level1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "Level 1 button clicked!", Toast.LENGTH_LONG)
                        .show();

                Intent i = new Intent(MainMenuActivity.this,
                        ScreenSlidePagerActivity.class);
                startActivity(i);
                //finish();


            }
        });

        practicalSupport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "Practical Support button clicked!", Toast.LENGTH_LONG)
                        .show();

                Intent i = new Intent(MainMenuActivity.this,
                        PracticalSupportActivity.class);
                startActivity(i);
                //finish();
            }
        });

    }
}

