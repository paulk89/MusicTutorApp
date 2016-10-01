package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * Created by paulk on 01/10/2016.
 */
public class MetronomeActivity extends Activity{

    private MediaPlayer mp;
    Button slow, medium, fast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);

        slow = (Button) findViewById(R.id.slow_button);
        medium = (Button) findViewById(R.id.medium_button);
        fast = (Button) findViewById(R.id.fast_button);

        slow.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(MetronomeActivity.this, R.raw.metronome_slow);
                    mp.start();
                }
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(MetronomeActivity.this, R.raw.metronome_medium);
                    mp.start();
                }
            }
        });

        fast.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                if (mp != null){
                    stopPlaying();
                }else {
                    mp = MediaPlayer.create(MetronomeActivity.this, R.raw.metronome_fast);
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
