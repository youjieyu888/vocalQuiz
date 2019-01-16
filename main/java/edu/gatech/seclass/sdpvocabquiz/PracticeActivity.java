package edu.gatech.seclass.sdpvocabquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class PracticeActivity extends AppCompatActivity {

    private int correctAnswers = 0;
    private ArrayList<Integer> usedWord = new ArrayList<Integer>();
    private int correctWord = 0;
    private int pattern = 0;
    private Button buttonA;
    private Button buttonB;
    private Button buttonC;
    private Button buttonD;
    private List<IncorrectDefinition> allIncorrect;
    private ArrayList<Integer> threeWrongDefinition = new ArrayList<Integer>();
    private List<Word> allWord;
    private String quizName = "";
    private ArrayList<Integer> randomPool = new ArrayList<Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quiz_list2);
        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
            quizName = extras.getString("QUIZ_NAME");
        }
        //load up the quiz based on user selection
        QuizList othersQuizList = QuizManager.getInstance().getOthersQuizList();
        Quiz currentQuiz = othersQuizList.getQuiz(quizName);
        allWord = currentQuiz.getWords();
        allIncorrect = currentQuiz.getIncorrectDefinition();

        //adding all the indices for random pool
        for(int i = 0; i < allWord.size(); i++){
            randomPool.add(i);
        }

        buttonA = (Button) findViewById(R.id.choice1);
        buttonB = (Button) findViewById(R.id.choice2);
        buttonC = (Button) findViewById(R.id.choice3);
        buttonD = (Button) findViewById(R.id.choice4);



        //generate all the random patterns and store them in the global private variables
        generateAllRandomNumbers();
        //updateView once to populate the activity for the first question, the later updateView should be called by each button click
        updateViews(pattern,allWord ,allIncorrect, correctWord, threeWrongDefinition);
        threeWrongDefinition.clear();

        //Set onClick listener on each button
        buttonA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonA.getText() == allWord.get(correctWord).getdefinition()) {
                    correctAnswers++;
                    Toast.makeText(PracticeActivity.this, "Good Job!", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
                else {
                    Toast.makeText(PracticeActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
            }
        });

        buttonB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonB.getText() == allWord.get(correctWord).getdefinition()) {
                    correctAnswers++;
                    Toast.makeText(PracticeActivity.this, "Good Job!", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
                else {
                    Toast.makeText(PracticeActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
            }
        });

        buttonC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonC.getText() == allWord.get(correctWord).getdefinition()) {
                    Toast.makeText(PracticeActivity.this, "Good Job!", Toast.LENGTH_SHORT).show();
                    correctAnswers++;
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
                else {
                    Toast.makeText(PracticeActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
            }
        });

        buttonD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (buttonD.getText() == allWord.get(correctWord).getdefinition()) {
                    correctAnswers++;
                    Toast.makeText(PracticeActivity.this, "Good Job!", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
                else {
                    Toast.makeText(PracticeActivity.this, "Wrong Answer", Toast.LENGTH_SHORT).show();
                    if(usedWord.size()>=allWord.size()){
                        showQuizResult(view);
                    }
                    else {
                        generateAllRandomNumbers();
                        updateViews(pattern, allWord, allIncorrect, correctWord, threeWrongDefinition);
                        threeWrongDefinition.clear();
                    }
                }
            }
        });
    }


    //helper functions
    public int getRandomWithExclusion(Random rnd, ArrayList<Integer> randomPool){
        int range = allWord.size()- usedWord.size();
        int temp = rnd.nextInt(range);
        int index = randomPool.get(temp);
        randomPool.remove(temp);
        usedWord.add(index);
        return index;
    }

    public void updateViews(int pattern,
                            List<Word> allWord,
                            List<IncorrectDefinition> allIncorrect,
                            int correctWord,
                            ArrayList<Integer> threeWrongDefinition){

        //first update the question
        updateQuestion(allWord, correctWord);

        //then depending on the pattern(random number determine which choice is the correct one), populate the buttons
        if (pattern == 0){
            Button correctButton = (Button) findViewById(R.id.choice1);
            correctButton.setText(allWord.get(correctWord).getdefinition());
            Button wrongButton1 = (Button) findViewById(R.id.choice2);
            wrongButton1.setText(allIncorrect.get(threeWrongDefinition.get(0)).getDefinition());
            Button wrongButton2 = (Button) findViewById(R.id.choice3);
            wrongButton2.setText(allIncorrect.get(threeWrongDefinition.get(1)).getDefinition());
            Button wrongButton3 = (Button) findViewById(R.id.choice4);
            wrongButton3.setText(allIncorrect.get(threeWrongDefinition.get(2)).getDefinition());
        }

        else if (pattern == 1){
            Button correctButton = (Button) findViewById(R.id.choice2);
            correctButton.setText(allWord.get(correctWord).getdefinition());
            Button wrongButton1 = (Button) findViewById(R.id.choice1);
            wrongButton1.setText(allIncorrect.get(threeWrongDefinition.get(0)).getDefinition());
            Button wrongButton2 = (Button) findViewById(R.id.choice3);
            wrongButton2.setText(allIncorrect.get(threeWrongDefinition.get(1)).getDefinition());
            Button wrongButton3 = (Button) findViewById(R.id.choice4);
            wrongButton3.setText(allIncorrect.get(threeWrongDefinition.get(2)).getDefinition());
        }

        else if (pattern == 2){
            Button correctButton = (Button) findViewById(R.id.choice3);
            correctButton.setText(allWord.get(correctWord).getdefinition());
            Button wrongButton1 = (Button) findViewById(R.id.choice1);
            wrongButton1.setText(allIncorrect.get(threeWrongDefinition.get(0)).getDefinition());
            Button wrongButton2 = (Button) findViewById(R.id.choice2);
            wrongButton2.setText(allIncorrect.get(threeWrongDefinition.get(1)).getDefinition());
            Button wrongButton3 = (Button) findViewById(R.id.choice4);
            wrongButton3.setText(allIncorrect.get(threeWrongDefinition.get(2)).getDefinition());
        }

        else{
            Button correctButton = (Button) findViewById(R.id.choice4);
            correctButton.setText(allWord.get(correctWord).getdefinition());
            Button wrongButton1 = (Button) findViewById(R.id.choice1);
            wrongButton1.setText(allIncorrect.get(threeWrongDefinition.get(0)).getDefinition());
            Button wrongButton2 = (Button) findViewById(R.id.choice2);
            wrongButton2.setText(allIncorrect.get(threeWrongDefinition.get(1)).getDefinition());
            Button wrongButton3 = (Button) findViewById(R.id.choice3);
            wrongButton3.setText(allIncorrect.get(threeWrongDefinition.get(2)).getDefinition());
        }
    }


    private void updateQuestion(List<Word> allWord, int correctWord){
        TextView question = (TextView) findViewById(R.id.question);
        question.setText(allWord.get(correctWord).getWord());
    }

    //this method is used to generate all required random numbers for updateView to use
    private void generateAllRandomNumbers(){
        Random ran = new Random();
        correctWord = getRandomWithExclusion(ran, randomPool);
        pattern = ran.nextInt(4);
        for (int i = 0; i < 3; i++){
            int wrongDefinition = ran.nextInt(allIncorrect.size());
            threeWrongDefinition.add(wrongDefinition);
        }
    }

    private void showQuizResult(View view){
        Intent intent = new Intent(view.getContext(), ShowQuizResultActivity.class);
        Bundle extras = new Bundle();
        extras.putString("QUIZ_NAME",quizName);
        extras.putString("QUIZ_SCORE", Integer.toString(correctAnswers) );
        intent.putExtras(extras);
        startActivity(intent);
    }
}
