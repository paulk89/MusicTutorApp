package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 24/09/2016.
 */
public class Level1Lesson4Fragment extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_level1_lesson4, container, false);

        return rootView;
    }
}