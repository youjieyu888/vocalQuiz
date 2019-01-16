package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class AddQuestionActivity extends AppCompatActivity implements View.OnClickListener {
    LinearLayout.LayoutParams layoutParams;
    LinearLayout ll;
    Quiz quiz;
    String name, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_questions);

        name = getIntent().getExtras().getString("quiz_name");
        desc = getIntent().getExtras().getString("quiz_desc");

        Button addQ = (Button)findViewById(R.id.add_question_btn);
        ll = (LinearLayout)findViewById(R.id.questions);
        layoutParams = new LinearLayout.LayoutParams
                (LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);

        addQ.setOnClickListener(this);
        Button rmQuestion = (Button)findViewById(R.id.remove_question_btn);

        rmQuestion.setOnClickListener(this);
        Button addIncorrectQ = (Button)findViewById(R.id.add_incorrect_question_btn);
        addIncorrectQ.setOnClickListener(this);
        addQuestionToView();
    }

    private void addQuestionToView(){
        LinearLayout view = (LinearLayout) getLayoutInflater().inflate(R.layout.question_title, null);
        TextView textView = (TextView) view.getChildAt(0);
        int qNumb = ll.getChildCount() + 1;
        textView.setText("question #" + qNumb);
        view.getChildAt(1).setId(qNumb);
        view.getChildAt(2).setId(qNumb * 10);
        ll.addView(view);
    }

    @Override
    public void onClick(View v) {

        if(ll.getChildCount()==0){
            String msg = new String("At least one question!");
            Toast.makeText(AddQuestionActivity.this, msg, msg.length()).show();
            return;
        }
        switch (v.getId()){
            case R.id.add_incorrect_question_btn:
                {
                    HashMap<String, String> def = new HashMap<>();
                    for(int i = 1; i <= ll.getChildCount(); i++){
                        String question = ((EditText)findViewById(i)).getText().toString();
                        String answer = ((EditText) findViewById(i*10)).getText().toString();
                        if(question == null || question.equals("")){
                            ((EditText)findViewById(i)).setError("Word must be valid");
                            return;
                        }
                        if(answer == null || answer.equals("")){
                            ((EditText)findViewById(i*10)).setError("Answer must be valid");
                            return;
                        }
                        def.put(question, answer);
                    }
                    Intent intent = new Intent(this, AddIncorrectActivity.class);
                    intent.putExtra("quiz_name", name);
                    intent.putExtra("quiz_desc", desc);
                    intent.putExtra("quiz_def", def);
                    startActivity(intent);
                    break;
                }
            case R.id.remove_question_btn:
                {
                    if(ll.getChildCount() > 1){
                        ll.removeViewAt(ll.getChildCount()-1);
                    }else{
                        int duration = Toast.LENGTH_LONG;
                        Toast toast = Toast.makeText(getApplicationContext(), "One Question Required", duration);
                        toast.show();
                    }
                    break;
                }
            case R.id.add_question_btn: {
                if (ll.getChildCount() < 10) {
                    addQuestionToView();
                } else {
                    int duration = Toast.LENGTH_LONG;
                    Toast toast = Toast.makeText(getApplicationContext(), "up to 10 questions allowed", duration);
                    toast.show();
                }
                break;
            }
        }

    }
}
