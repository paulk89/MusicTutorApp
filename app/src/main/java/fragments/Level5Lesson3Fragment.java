package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 27/09/2016.
 */
public class Level5Lesson3Fragment extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_level5_lesson3, container, false);

        return rootView;
    }
}