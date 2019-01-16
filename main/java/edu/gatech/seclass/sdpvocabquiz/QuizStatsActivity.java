package edu.gatech.seclass.sdpvocabquiz;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

public class QuizStatsActivity extends Activity {

    String firstScore;
    String firstDate;
    String bestScore;
    String bestDate;
    String first100;
    String second100;
    String third100;
    List<String> first3;
    TextView first_score;
    TextView first_date;
    TextView best_score;
    TextView best_date;
    TextView title;
    LinearLayout ll;
    LinearLayout.LayoutParams lp;
//    TextView first_100;
//    TextView second_100;
//    TextView third_100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_score_result);

        String qname = "";
        try {
            qname = (String) getIntent().getExtras().getString("quiz");
        }catch (Exception e){
            qname = "testQuiz3";
        }
        OthersQuizList oq = QuizManager.getInstance().getOthersQuizList();
        MyQuizList mq = QuizManager.getInstance().getMyQuizList();
        Quiz q;
        if (oq.getQuiz(qname)!=null){
            q = oq.getQuiz(qname);
        }else{
            q = mq.getQuiz(qname);
        }

        QuizStatistics qs = q.getQuizStatistics(QuizManager.getInstance().getStudent().getUsername());

        title = (TextView) findViewById(R.id.textView);
        first_score = (TextView) findViewById(R.id.first_score);
        first_date = (TextView) findViewById(R.id.first_date);
        best_score = (TextView) findViewById(R.id.best_score);
        best_date = (TextView) findViewById(R.id.best_date);

        title.setText("Quiz \"" + qname + "\" Statistics");

        if (qs.getFirstScore() != null){
            firstScore = Float.toString(qs.getFirstScore());
            first_score.setText(firstScore);
        }else{
            first_score.setText("N/A");
        }

        if (qs.getFirstScoreDateTime()!=null){
            firstDate = qs.getFirstScoreDateTime().toString();
            first_date.setText(firstDate);
        }else{
            first_date.setText("N/A");
        }

        if(qs.getHighestScore()!=null){
            bestScore = Float.toString(qs.getHighestScore());
            best_score.setText(bestScore);
        }else{
            best_score.setText("N/A");
        }

        if(qs.getHighestScoreDateTime()!=null){
            bestDate = qs.getHighestScoreDateTime().toString();
            best_date.setText(bestDate);
        }else{
            best_date.setText("N/A");
        }

        int index=0;
        ll = (LinearLayout) findViewById(R.id.ll);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
       // li = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);


        if(qs.getFirstThreeStudentGotPerfectScore()!=null){
            first3 = qs.getFirstThreeStudentGotPerfectScore();
            while(index<first3.size()){
                TextView tv = new TextView(this);
                tv.setLayoutParams(lp);
                tv.setText(first3.get(index));
                ll.addView(tv,lp);
                index++;
            }
        }else{
            TextView tv = new TextView(this);
            tv.setLayoutParams(lp);
            tv.setText("N/A");
            ll.addView(tv);
        }

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
