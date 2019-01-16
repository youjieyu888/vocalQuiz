package edu.gatech.seclass.sdpvocabquiz;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.TreeSet;

public class OthersQuizList extends QuizList {

    Student student;
    public OthersQuizList(TreeSet<Quiz> collection, Student student){
        super(collection);
        this.student = student;
    }

    @NonNull
    @Override
    public Iterator<Quiz> iterator() {
        List<Quiz> iterator = new LinkedList<>();
        for(Quiz q : quizSet){
            if(! q.isCreatedByUser(student.getUsername())){
                iterator.add(q);
            }
        }
        return iterator.iterator();
    }

    //test

}