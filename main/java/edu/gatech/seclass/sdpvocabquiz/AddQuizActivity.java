package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class AddQuizActivity extends AppCompatActivity implements  View.OnClickListener {


    private TextView quizName;
    private  TextView quizDesc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_a_quiz);
        findViewById(R.id.addQuestion).setOnClickListener(this);

        quizName = findViewById(R.id.quiz_name);
        quizDesc = findViewById(R.id.description);


    }

    @Override
    public void onClick(View v) {
        String name = quizName.getText().toString();
        String desc = quizDesc.getText().toString();

        if(name == null || name.trim().equals("")){
            quizName.setError("Quiz Name is required");
            return;
        }
        if(desc == null || desc.trim().equals("")){
            quizDesc.setError("Quiz description is required");
            return;
        }
        if (QuizManager.getInstance().getMyQuizList().getQuiz(name) != null ||
                QuizManager.getInstance().getOthersQuizList().getQuiz(name) != null) {
            quizName.setError("Quiz name already existed!");
            return;
        }

        Intent intent = new Intent(this, AddQuestionActivity.class);
        intent.putExtra("quiz_name", name);
        intent.putExtra("quiz_desc", desc);

        startActivity(intent);

    }
}