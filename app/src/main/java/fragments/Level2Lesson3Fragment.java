package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 25/09/2016.
 */
public class Level2Lesson3Fragment extends android.support.v4.app.Fragment {

    Button level1Test;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_level2_lesson3, container, false);

        return rootView;
    }
}