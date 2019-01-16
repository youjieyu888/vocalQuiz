package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class AddIncorrectActivity extends AppCompatActivity implements View.OnClickListener{

    LinearLayout.LayoutParams layoutParams;
    LinearLayout ll;
    int OFFSET= 200;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_incorrect_def);

        Button createQuiz = (Button)findViewById(R.id.create_quiz);
        createQuiz.setOnClickListener(this);
        ll = (LinearLayout)findViewById(R.id.incorrect_def_ll);
        HashMap<String, String> words = (HashMap<String, String>) getIntent().getExtras().getSerializable("quiz_def");

        for(int i = 1; i <= 3 * words.size(); i++){
            LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.incorrection_def_view, null);
            ((TextView) view.getChildAt(0)).setText("Incorrect Definition #"+i);
            ((TextView)view.getChildAt(1)).setText("");
            view.getChildAt(1).setId(i+OFFSET);
            ll.addView(view);

        }

    }

    public void onClick(View v) {
        List<String> list = new ArrayList<>();
        for(int i = 1; i <= ll.getChildCount(); i++){
            EditText editText = ((EditText)findViewById(i+OFFSET));
            if(editText.getText() == null || editText.getText().toString().trim().equals("")){
                editText.setError("Invalid");
                return;
            }else{
                list.add(editText.getText().toString());
            }
        }
        HashMap<String, String> words = (HashMap<String, String>) getIntent().getExtras().getSerializable("quiz_def");
        String name = getIntent().getExtras().getString("quiz_name");
        String desc = getIntent().getExtras().getString("quiz_desc");
        QuizManager.getInstance().createQuiz(name, desc, words, list);
        Intent intent = new Intent(this, MainMenuActivity.class);
        startActivity(intent);
    }
}
