package tests;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.paulk.MusicTutorApp.R;


/**
 * Created by paulk on 28/06/2016.
 */
public class Level1TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        ImageView imageView = (ImageView)findViewById(R.id.questionImage);
        imageView.setImageResource(R.drawable.a_minor_chord);
        imageView.setVisibility(View.VISIBLE);
        //imageView.setVisibility(View.GONE);

    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
