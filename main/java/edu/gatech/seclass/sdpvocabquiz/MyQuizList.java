package edu.gatech.seclass.sdpvocabquiz;

import android.support.annotation.NonNull;
import android.util.Log;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.TreeSet;

public class MyQuizList extends QuizList {

    private  Student student;

    public MyQuizList(TreeSet<Quiz> quizCollection, Student s){
        super(quizCollection);
        this.student = s;
    }

    public boolean delQuiz(String quizName){
        Quiz q = super.getQuiz(quizName);
        if(q.getOwnerUserName().equals(student.getUsername())){
            return super.delQuiz(q.getName());
        }
        return false;
    }

    public void addQuiz(Quiz quiz){
        quizSet.add(quiz);
    }

    @NonNull
    @Override
    public Iterator<Quiz> iterator() {
            Collection<Quiz> col = new ArrayList<>();
            for( Quiz q : quizSet ){
                if(q.isCreatedByUser(student.getUsername())) {
                    col.add(q);
                }
            }
            return col.iterator();
    }
}
