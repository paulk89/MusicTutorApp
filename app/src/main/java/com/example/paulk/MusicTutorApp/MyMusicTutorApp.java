package com.example.paulk.MusicTutorApp;

import android.app.Application;
import android.content.Context;

/**
 * Created by paulk on 29/09/2016.
 */
public class MyMusicTutorApp extends Application {

    private static Context context;

    public void onCreate() {
        super.onCreate();
        MyMusicTutorApp.context = getApplicationContext();
    }

    public static Context getAppContext() {
        return MyMusicTutorApp.context;
    }
}
