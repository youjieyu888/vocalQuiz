package edu.gatech.seclass.sdpvocabquiz;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

public abstract class QuizList implements Iterable<Quiz> {

    TreeSet<Quiz> quizSet;

    public QuizList(TreeSet<Quiz> collection){
        quizSet = collection;
    }

    protected boolean delQuiz(String quizName){
        return quizSet.remove(new Quiz(quizName));
    }

    protected Quiz getQuiz(String quizName) {
        for (Quiz quiz : quizSet){
            if(quiz.getName().equals(quizName)){
                return quiz;
            }
        }
        return null;
    }

    @NonNull
    @Override
    public Iterator<Quiz> iterator() {
        return quizSet.iterator();
    }
}
