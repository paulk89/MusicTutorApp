package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 24/09/2016.
 */
public class Level1Lesson2Fragment extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_level1_lesson2, container, false);

        return rootView;
    }
}
