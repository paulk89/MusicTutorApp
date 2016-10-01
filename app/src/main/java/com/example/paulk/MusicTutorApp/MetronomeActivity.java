package com.example.paulk.MusicTutorApp;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;

/**
 * Created by paulk on 01/10/2016.
 */
public class MetronomeActivity extends Activity{

    private MediaPlayer mp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_metronome);
    }
}
