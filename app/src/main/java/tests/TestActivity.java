package tests;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.paulk.MusicTutorApp.DatabaseHandler;
import com.example.paulk.MusicTutorApp.Question;
import com.example.paulk.MusicTutorApp.R;

import java.io.ByteArrayInputStream;
import java.util.Collections;
import java.util.List;


/**
 * Created by paulk on 28/06/2016.
 */
public class TestActivity extends Activity {

    List<Question> questionList;
    int score = 0;
    int qid = 0;
    Question currentQ;
    TextView txtQuestionNumber;
    TextView question;
    ImageView imageView;
    RadioButton radio0, radio1, radio2, radio3;
    RadioGroup radioGroup;
    Button butNext;
    int testLevel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        testLevel = getIntent().getExtras().getInt("level");
        imageView = (ImageView)findViewById(R.id.questionImage);

        DatabaseHandler db = new DatabaseHandler(this);
        db.getReadableDatabase();

        //get the questions for this level
        questionList = db.getAllQuestions(testLevel);

        //shuffle questions randomly
        Collections.shuffle(questionList);

        txtQuestionNumber = (TextView)findViewById(R.id.QuestionNumber);
        question = (TextView)findViewById(R.id.question);
        radioGroup = (RadioGroup)findViewById(R.id.radioGroup1);
        radio0 =(RadioButton)findViewById(R.id.radio0);
        radio1 =(RadioButton)findViewById(R.id.radio1);
        radio2 =(RadioButton)findViewById(R.id.radio2);
        radio3 =(RadioButton)findViewById(R.id.radio3);
        butNext=(Button)findViewById(R.id.next);

        //get the question in the first position
        currentQ = questionList.get(qid);
        //set its view
        setQuestionView();

        butNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (radioGroup.getCheckedRadioButtonId()== -1){
                    Toast.makeText(getApplicationContext(),"Please select an answer!", Toast.LENGTH_LONG).show();
                }else {
                    RadioButton answer = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                    if (currentQ.getCorrect().equals(answer.getText())) {
                        score++;
                    }
                    if (qid == 9) {
                        butNext.setText("Finish Test");
                    }
                    if (qid < 10) {
                        currentQ = questionList.get(qid);
                        setQuestionView();
                    } else {
                        Intent intent = new Intent(TestActivity.this, ResultActivity.class);
                        Bundle b = new Bundle();
                        b.putInt("score", score); //Your score
                        b.putInt("testLevel", testLevel);
                        intent.putExtras(b); //Put your score to your next Intent
                        startActivity(intent);
                        finish();
                    }
                }
            }
        });

    }

    //set the question view depending on whether its an image question or a text question
    private void setQuestionView() {

        if (currentQ.getIsImageQuestion()) {

            radioGroup.clearCheck();

            byte [] imageInByte = questionList.get(qid).getImageResource();

            ByteArrayInputStream imageStream = new ByteArrayInputStream(imageInByte);
            Bitmap theImage = BitmapFactory.decodeStream(imageStream);
            imageView.setImageBitmap(theImage);
            imageView.setVisibility(View.VISIBLE);

            txtQuestionNumber.setText("Question " + (qid + 1));
            question.setText(currentQ.getQuestion());
            radio0.setText(currentQ.getA1());
            radio1.setText(currentQ.getA2());
            radio2.setText(currentQ.getA3());
            radio3.setText(currentQ.getA4());

            qid++;

        }else {

            radioGroup.clearCheck();

            imageView.setVisibility(View.GONE);

            txtQuestionNumber.setText("Question " + (qid + 1));
            question.setText(currentQ.getQuestion());
            radio0.setText(currentQ.getA1());
            radio1.setText(currentQ.getA2());
            radio2.setText(currentQ.getA3());
            radio3.setText(currentQ.getA4());

            qid++;


        }
    }

    public void onBackPressed() {
        super.onBackPressed();
    }
}
