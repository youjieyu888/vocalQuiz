package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import java.util.Iterator;
import java.util.List;

public class ChooseQuizActivity extends AppCompatActivity {

    LinearLayout ll;
    LinearLayout.LayoutParams lp;

    String username;
    List<String> quizlist;
    int btn_id=1;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_list);
//        //should use quiz
//        ArrayList<Integer> al = new ArrayList<>();
//        al.add(1);
//        al.add(2);
//        Iterator itr = al.iterator();
//        TakenQuizList tq = QuizManager.getInstance().getTakenQuizList();
//        Iterator<Quiz> itr_mq = tq.iterator();

        //other quiz (taken or not taken)
        OthersQuizList oql = QuizManager.getInstance().getOthersQuizList();
        //quiz created by me
//        MyQuizList mql = QuizManager.getInstance().getMyQuizList();

//        Collection<Quiz> notTakenQuizList = new ArrayList<>();
//        for(Quiz q : oql){
//            notTakenQuizList.add(q);
//        }
        //all of quizzes created by me
//        for(Quiz q : mql){
//            notTakenQuizList.add(q);
//        }

        Iterator<Quiz> itr_oql = oql.iterator();

        ll=(LinearLayout) findViewById(R.id.LinearLayout1);
        lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);
//        ll2=(LinearLayout) findViewById(R.id.ll2);
//        lp2 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,LinearLayout.LayoutParams.WRAP_CONTENT);


        while(itr_oql.hasNext()){
            Button mybutton = new Button(this);
            mybutton.setId(btn_id);
            final Quiz q = itr_oql.next();
            String temp = "Quiz Name: " + q.getName() + "\n" + "Quiz Description: " + q.getDescription();
            mybutton.setText(temp);

            ll.addView(mybutton, lp);
            Button btn_num = (Button) findViewById(btn_id);
            btn_num.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), PracticeActivity.class);
                    intent.putExtra("QUIZ_NAME", q.getName());
                    startActivity(intent);
                }
            });
            btn_id++;
        }
	/*
        while(itr_oq.hasNext()){
            Button mybutton = new Button(this);
            mybutton.setId(btn_id);
            final Quiz q = itr_oq.next();
            String temp = "Quiz Name: " + q.getName() + "\n" + "Quiz Description: " + q.getDescription();
            mybutton.setText(temp);

            ll2.addView(mybutton, lp2);
            Button btn_num = (Button) findViewById(btn_id);
            btn_num.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(v.getContext(), QuizStatsActivity.class);
                    intent.putExtra("quiz", q.getName());
                    startActivity(intent);
                }
            });
            btn_id++;
        }
	*/
    }
}
