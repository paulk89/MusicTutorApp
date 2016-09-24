package fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.paulk.MusicTutorApp.R;


/**
 * Created by paulk on 25/06/2016.
 */
public class ScreenSlidePageFragment  extends android.support.v4.app.Fragment{

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_level1_intro, container, false);

        return rootView;
    }
}
