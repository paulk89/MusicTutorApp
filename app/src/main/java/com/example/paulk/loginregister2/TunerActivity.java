package com.example.paulk.loginregister2;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by paulk on 27/06/2016.
 */
public class TunerActivity extends Activity {

    Button mainMenu, lowE_button, a_button, d_button, g_button, b_button, highE_button;

    MediaPlayer lowE_sound, a_sound, d_sound, g_sound, b_sound, highE_sound;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);

        mainMenu = (Button) findViewById(R.id.main_menu2);
        lowE_button = (Button) findViewById(R.id.low_e);
        a_button = (Button) findViewById(R.id.a);
        d_button = (Button) findViewById(R.id.d);
        g_button = (Button) findViewById(R.id.g);
        b_button = (Button) findViewById(R.id.b);
        highE_button = (Button) findViewById(R.id.high_e);

        lowE_sound = MediaPlayer.create(this,R.raw.lowe);
        a_sound = MediaPlayer.create(this,R.raw.a);
        d_sound = MediaPlayer.create(this,R.raw.d);
        g_sound = MediaPlayer.create(this,R.raw.g);
        b_sound = MediaPlayer.create(this,R.raw.b);
        highE_sound = MediaPlayer.create(this,R.raw.highe);



        mainMenu.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "You have exited the tuner out Successfully", Toast.LENGTH_LONG)
                        .show();
                Intent i = new Intent(TunerActivity.this,
                        MainMenuActivity.class);
                startActivity(i);
                finish();
            }
        });

        lowE_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "Low e button selected", Toast.LENGTH_LONG)
                        .show();
                lowE_sound.start();
            }
        });

        a_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "a button selected", Toast.LENGTH_LONG)
                        .show();
                a_sound.start();
            }
        });

        d_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "d button selected", Toast.LENGTH_LONG)
                        .show();
                d_sound.start();
            }
        });

        g_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "g button selected", Toast.LENGTH_LONG)
                        .show();
                g_sound.start();
            }
        });

        b_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "b button selected", Toast.LENGTH_LONG)
                        .show();
                b_sound.start();
            }
        });

        highE_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Toast.makeText(getApplicationContext(),
                        "d button selected", Toast.LENGTH_LONG)
                        .show();
                highE_sound.start();
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();
        lowE_sound.release();
        a_sound.release();
        d_sound.release();
        g_sound.release();
        b_sound.release();
        highE_sound.release();
    }

}
