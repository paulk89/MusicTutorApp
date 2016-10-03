package com.example.paulk.MusicTutorApp;

import android.app.Application;
import android.content.Context;

/**
 * class to retrieve the context anywhere in the app
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
