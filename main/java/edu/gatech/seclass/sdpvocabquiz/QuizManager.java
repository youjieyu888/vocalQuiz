package edu.gatech.seclass.sdpvocabquiz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class QuizManager {

    private static final QuizManager instance = new QuizManager();

    private Student student;

    //quiz created by the @student
    private MyQuizList myQuizList;

    //quiz created by other students
    private OthersQuizList othersQuizList;

    //quiz taken by the student
    // othersQuizList - takenQuizList =  notTakenQuizList
    private TakenQuizList takenQuizList;

    public static QuizManager getInstance(){
        if(instance.student == null){
            throw new IllegalStateException("You can not call getInstance without calling set student first");
        }
        return instance;
    }

    private QuizManager(){

    }

    public static void setStudent(Student student){
        instance.student = student;
        //load all quizzes
        List<Quiz> quizIterable = Quiz.listAll(Quiz.class);
        for(Quiz q : quizIterable){
            q.setWords(
                    Word.find(Word.class,
                            "quiz_id = ?",
                            ""+q.getId())
            );
            q.setIncorrectDefinition(
                    IncorrectDefinition.find(IncorrectDefinition.class,
                            "quiz_id = ?",
                            ""+q.getId())
            );
            q.setTakenQuizList(TakenQuiz.find(
                    TakenQuiz.class,
                    "quiz_id = ?",
                    ""+q.getId())
            );
        }

        TreeSet<Quiz> quizTreeSet = new TreeSet<>(quizIterable);
        instance.myQuizList = new MyQuizList(quizTreeSet, student);
        instance.othersQuizList = new OthersQuizList(quizTreeSet, student);
        instance.takenQuizList = new TakenQuizList(quizTreeSet, student);
    }


    public Student getStudent() {
        return student;
    }

    public MyQuizList getMyQuizList() {
        return myQuizList;
    }

    public OthersQuizList getOthersQuizList() {
        return othersQuizList;
    }

    public void createQuiz(String name, String desc, Map<String, String> words,
                        Collection<String> invalidWord){
        Quiz q = new Quiz(name);
        q.setDescription(desc);
        q.setIncorrectDefinition(invalidWord);
        q.setWords(words);
        q.setOwnerUserName(this.student.getUsername());
        createQuiz(q);

    }

    public void createQuiz(Quiz quiz){
        //myquizlist and otherquizlist uses the same set so getQuiz is for all the lists
        if( myQuizList.getQuiz(quiz.getName()) == null ) {
            long quizId = quiz.save();

            for(Word word : quiz.getWords()){
                word.setQuizId(quizId);
                word.save();
            }

            for(IncorrectDefinition incorrectDefinition : quiz.getIncorrectDefinition()){
                incorrectDefinition.setQuizId(quizId);
                incorrectDefinition.save();
            }

            myQuizList.addQuiz(quiz);
        }else {
            throw new IllegalArgumentException("Quiz already exists");
        }

    }

    public void removeQuiz(String name){
        Quiz quiz = myQuizList.getQuiz(name);
        if(myQuizList.delQuiz(name)){
            for(IncorrectDefinition incorrectDefinition : quiz.getIncorrectDefinition()){
                incorrectDefinition.delete();
            }
            for(Word word : quiz.getWords()){
                word.delete();
            }
            for(TakenQuiz takenQuiz : quiz.getTakenQuizList()){
                takenQuiz.delete();
            }
            quiz.delete();
        }
    }

    public QuizStatistics viewQuizStatistics(String quizName) {
        return myQuizList.getQuiz(quizName).getQuizStatistics(student.getUsername());
    }

    public TakenQuizList getTakenQuizList() {
        return takenQuizList;
    }

    public MyQuizList getMyQuizzess(){
        return myQuizList;
    }

    public void takeQuiz(int numberOfCorrect, Date playedDate, String quizName){
        Quiz q = othersQuizList.getQuiz(quizName);
        TakenQuiz takenQuiz = new TakenQuiz(student.getUsername(), numberOfCorrect,q.getWords().size(),playedDate);
        q.addTakenQuiz(takenQuiz);
        takenQuiz.setQuizId(q.getId());
        takenQuiz.save();

    }

}
