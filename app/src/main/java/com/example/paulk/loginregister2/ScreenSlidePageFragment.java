package com.example.paulk.loginregister2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
/**
 * Created by paulk on 25/06/2016.
 */
public class ScreenSlidePageFragment  extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.level1_fragment_screen_slide_page, container, false);

        return rootView;
    }
}
