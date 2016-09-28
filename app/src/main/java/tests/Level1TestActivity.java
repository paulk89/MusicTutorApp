package tests;

import android.app.Activity;
import android.os.Bundle;

import com.example.paulk.MusicTutorApp.R;


/**
 * Created by paulk on 28/06/2016.
 */
public class Level1TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.level1_quiz);
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
