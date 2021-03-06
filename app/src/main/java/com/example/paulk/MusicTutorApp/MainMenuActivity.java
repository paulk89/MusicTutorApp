package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import lessons.LessonsActivity;


/**
 * Created by paulk on 01/06/2016.
 */
public class MainMenuActivity extends Activity {

    Button logout, level1, level2, level3, level4, level5, practicalSupport, myProgress;
    TextView welcomeView;
    DatabaseHandler db;
    Dialog dialog;
    private int currentLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainmenu);

        logout = (Button) findViewById(R.id.logout);
        level1 = (Button) findViewById(R.id.level1_button);
        level2 = (Button) findViewById(R.id.level2_button);
        level3 = (Button) findViewById(R.id.level3_button);
        level4 = (Button) findViewById(R.id.level4_button);
        level5 = (Button) findViewById(R.id.level5_button);
        practicalSupport = (Button) findViewById(R.id.practical_support);
        myProgress = (Button) findViewById(R.id.myProgress);
        welcomeView = (TextView)findViewById(R.id.main_menu_welcome);


        SharedPreferences userDetails = getApplicationContext().getSharedPreferences("userdetails", MODE_PRIVATE);
        final String currentUser = userDetails.getString("username", "");
        int userID = userDetails.getInt("userID", 0);

        welcomeView.setText("Welcome " + currentUser);

        db = new DatabaseHandler(getApplicationContext());
        currentLevel = db.getLevelID(userID);

        setButtonLocks(currentLevel);

        logout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                    showLogoutPrompt();
                }
        });

        level1.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                Intent i = new Intent(MainMenuActivity.this,
                        LessonsActivity.class);
                i.putExtra("buttonclick", 1);
                startActivity(i);

            }
        });

        level2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        LessonsActivity.class);
                i.putExtra("buttonclick", 2);
                startActivity(i);
            }
        });

        level3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        LessonsActivity.class);
                i.putExtra("buttonclick", 3);
                startActivity(i);
            }
        });

        level4.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        LessonsActivity.class);
                i.putExtra("buttonclick", 4);
                startActivity(i);
            }
        });

        level5.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        LessonsActivity.class);
                i.putExtra("buttonclick", 5);
                startActivity(i);
            }
        });

        practicalSupport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        PracticalSupportActivity.class);
                startActivity(i);
            }
        });

        myProgress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this,
                        MyProgressActivity.class);
                startActivity(i);
            }
        });
    }

    // set the required buttons to be disabled based on the users current level
    private void setButtonLocks(int currentLevel){

        switch (currentLevel) {

            case 1:
                level2.setEnabled(false);
                level2.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level3.setEnabled(false);
                level3.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level4.setEnabled(false);
                level4.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level5.setEnabled(false);
                level5.setBackgroundResource(R.drawable.main_button_disabled_layout);
                break;

            case 2:
                level3.setEnabled(false);
                level3.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level4.setEnabled(false);
                level4.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level5.setEnabled(false);
                level5.setBackgroundResource(R.drawable.main_button_disabled_layout);
                break;

            case 3:
                level4.setEnabled(false);
                level4.setBackgroundResource(R.drawable.main_button_disabled_layout);
                level5.setEnabled(false);
                level5.setBackgroundResource(R.drawable.main_button_disabled_layout);
                break;

            case 4:
                level5.setEnabled(false);
                level5.setBackgroundResource(R.drawable.main_button_disabled_layout);
                break;
            default:
                break;


        }
    }

    //prompt to confirm log out
    private void showLogoutPrompt(){

        dialog = new Dialog(MainMenuActivity.this);
        dialog.setContentView(R.layout.activity_logout_prompt);
        dialog.setTitle("Logout?");
        Button cancel = (Button)dialog.findViewById(R.id.cancel_logout);
        Button ok_logout = (Button)dialog.findViewById(R.id.ok_logout);


        cancel.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        ok_logout.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {

                Intent i = new Intent(MainMenuActivity.this, HomeActivity.class);
                startActivity(i);
                finish();
            }
        });

        dialog.show();
    }

    @Override
    public void onBackPressed() {
           showLogoutPrompt();
        }

    @Override
    public void onResume(){
        super.onResume();
        Log.i("Main Menu", "onResume fired!");
        setButtonLocks(currentLevel);

    }
}

