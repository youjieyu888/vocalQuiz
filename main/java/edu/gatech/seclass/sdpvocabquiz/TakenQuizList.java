package edu.gatech.seclass.sdpvocabquiz;

import android.support.annotation.NonNull;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class TakenQuizList extends QuizList {

    Student student;
    public TakenQuizList(TreeSet<Quiz> collection, Student student){
        super(collection);
        this.student = student;
    }

    @NonNull
    @Override
    public Iterator<Quiz> iterator() {
        List<Quiz> iterator = new LinkedList<>();
        for(Quiz q : quizSet){
            if(q.isTakenBy(student.getUsername())){
                iterator.add(q);
            }
        }
        Collections.sort(iterator, new OrderByTakenDateByStudent());
        Collections.reverse(iterator);
        return iterator.iterator();
    }

    //a quiz is taken if it exist, not created by the user, and it is taken by the user
    public boolean contains(Quiz q){
        Quiz quiz = getQuiz(q.getName());
        if( quiz != null){
            return quiz.isTakenBy(student.getUsername()) && !quiz.isCreatedByUser(student.getUsername());
        }
        return false;
    }

    public Quiz getQuiz(String string){ return super.getQuiz(string); }

    private class OrderByTakenDateByStudent implements Comparator<Quiz>{

        @Override
        public int compare(Quiz o1, Quiz o2) {
            return o1.getQuizStatistics(student.getUsername()).getLastPlayedDateTime()
                    .compareTo(o2.getQuizStatistics(student.getUsername()).getLastPlayedDateTime());
        }
    }

    //test

}