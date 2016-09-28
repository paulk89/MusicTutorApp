package tests;

import android.app.Activity;
import android.os.Bundle;

import com.example.paulk.MusicTutorApp.R;

/**
 * Created by paulk on 28/09/2016.
 */
public class Level3TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
