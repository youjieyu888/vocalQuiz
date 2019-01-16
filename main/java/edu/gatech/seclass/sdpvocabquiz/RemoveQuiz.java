package edu.gatech.seclass.sdpvocabquiz;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class RemoveQuiz extends AppCompatActivity  implements View.OnClickListener{

    EditText editText;
    TextView quizzes;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_remove_quiz);

        quizzes = findViewById(R.id.quizzes);

        String str = "";
        for(Quiz s : QuizManager.getInstance().getMyQuizzess()){
            str += s.getName()+"\n";
        }
        quizzes.setText(str);

        editText = findViewById(R.id.quiz_del_text);
        Button delButton = findViewById(R.id.quz_del_btn);

        delButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        QuizManager.getInstance().removeQuiz(editText.getText().toString());
        String str = "";
        for(Quiz s : QuizManager.getInstance().getMyQuizzess()){
            str += s.getName()+"\n";
        }
        quizzes.setText(str);

    }
}
