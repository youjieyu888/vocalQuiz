package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainMenuActivity extends AppCompatActivity implements View.OnClickListener {

    TextView textView;
    String msg;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_menu);
        findViewById(R.id.add).setOnClickListener(this);
        findViewById(R.id.remove).setOnClickListener(this);
        findViewById(R.id.view).setOnClickListener(this);
        findViewById(R.id.question).setOnClickListener(this);
        findViewById(R.id.logout).setOnClickListener(this);

        //Intent intent = getIntent();
        //String username = intent.getStringExtra("username");
        username = QuizManager.getInstance().getStudent().getUsername();
        msg = "Welcome "+ username + "!";
        textView = (TextView) findViewById(R.id.welcome);
        textView.setText(msg);
    }

    public void onClick(View v) {
        if(v.getId() == R.id.add) {
            Intent intent = new Intent(this, AddQuizActivity.class);
            startActivity(intent);
        } else if(v.getId() == R.id.remove){
            Intent intent = new Intent(this, RemoveViewQuiz.class);
            startActivity(intent);
        } else if(v.getId() == R.id.view){
            Intent intent = new Intent(this, ViewQuiz.class);
            startActivity(intent);
        } else if(v.getId() == R.id.question){
            Intent intent = new Intent(this, ChooseQuizActivity.class);
            startActivity(intent);
        } else if(v.getId() == R.id.logout){
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }
}
