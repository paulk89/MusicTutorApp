package tests;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;

import com.example.paulk.MusicTutorApp.R;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;


/**
 * Created by paulk on 28/06/2016.
 */
public class Level1TestActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_layout);
        ImageView imageView = (ImageView)findViewById(R.id.questionImage);

        //convert image to byte array
        Bitmap image = BitmapFactory.decodeResource(getResources(),R.drawable.parts_of_acoustic);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte imageInByte[] = stream.toByteArray();

        //convert image from byte array to bitmap
        ByteArrayInputStream imageStream = new ByteArrayInputStream(imageInByte);
        Bitmap theImage = BitmapFactory.decodeStream(imageStream);
        imageView.setImageBitmap(theImage);

        imageView.setVisibility(View.VISIBLE);
        //imageView.setVisibility(View.GONE);

    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
