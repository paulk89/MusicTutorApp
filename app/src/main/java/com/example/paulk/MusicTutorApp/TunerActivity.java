package com.example.paulk.MusicTutorApp;

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

    Button lowE_button, a_button, d_button, g_button, b_button, highE_button;

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tuner);

        lowE_button = (Button) findViewById(R.id.low_e);
        a_button = (Button) findViewById(R.id.a);
        d_button = (Button) findViewById(R.id.d);
        g_button = (Button) findViewById(R.id.g);
        b_button = (Button) findViewById(R.id.b);
        highE_button = (Button) findViewById(R.id.high_e);

        lowE_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.lowe);
                    mp.start();
                }
            }
        });

        a_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.a);
                    mp.start();
                }
            }
        });

        d_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.d);
                    mp.start();
                }
            }
        });

        g_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.g);
                    mp.start();
                }
            }
        });

        b_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.b);
                    mp.start();
                }
            }
        });

        highE_button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(TunerActivity.this, R.raw.highe);
                    mp.start();
                }
            }
        });
    }

    private void stopPlaying() {
        if (mp != null) {
            mp.stop();
            mp.release();
            mp = null;
        }
    }

    @Override
    protected void onPause(){
        super.onPause();
        if (mp != null) {
            mp.release();
        }
    }

}
