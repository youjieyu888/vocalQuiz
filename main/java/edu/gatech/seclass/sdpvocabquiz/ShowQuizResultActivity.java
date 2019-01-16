package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShowQuizResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //get quiz info first
        QuizList othersQuizList = QuizManager.getInstance().getOthersQuizList();

        setContentView(R.layout.activity_show_quiz_result);
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();
        String quizName = extras.getString("QUIZ_NAME");
        String quizCorrectNum = extras.getString("QUIZ_SCORE");
        Quiz currentQuiz = othersQuizList.getQuiz(quizName);


        TextView question = (TextView) findViewById(R.id.quizName);
        question.setText(quizName);

        int numQuestion = currentQuiz.getWords().size();
        int numCorrect = Integer.parseInt(quizCorrectNum);
        float score = (float) numCorrect/numQuestion * 100;
        String percentageString = Float.toString(score);

        TextView percentage = (TextView) findViewById(R.id.quizScore);
        percentage.setText(percentageString + "%");

        Date date = new Date();
        QuizManager.getInstance().takeQuiz(numCorrect,date,quizName);
        /*
        String string = "January 2, 2010";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy");
        Date date = format.parse(string);
        System.out.println(date);
        QuizManager.getInstance().takeQuiz(numCorrect,date.,quizName);
        */
        Button main = (Button) findViewById(R.id.main_menu);
        main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), MainMenuActivity.class);
                startActivity(intent);
            }
        });


        Button view_stat = (Button) findViewById(R.id.view_stat);
        view_stat.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), ViewQuiz.class);
                startActivity(intent);
            }
        });



    }
}
