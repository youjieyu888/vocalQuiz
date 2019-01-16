package edu.gatech.seclass.sdpvocabquiz;

import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.orm.SugarRecord;
import com.orm.dsl.Unique;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;

public class Quiz extends SugarRecord implements Comparable<Quiz>, Serializable{
    @NonNull
    @Unique
    private String name;
    private String description;
    private List<Word> words;
    private List<IncorrectDefinition> incorrectDefinition;
    private String ownerUserName;
    private List<TakenQuiz> takenQuizList;

    public Quiz(@NonNull String name, String description, List<Word> words, List<IncorrectDefinition> incorrectDefinition) {
        this.name = name;
        this.description = description;
        this.words = words;
        this.incorrectDefinition = incorrectDefinition;
        this.takenQuizList = new ArrayList<>();
    }

    public Quiz(){
        incorrectDefinition = new ArrayList<>();
        words = new ArrayList<>();
        this.takenQuizList = new ArrayList<>();
    }

    public Quiz(String name){
        this.name = name;
        incorrectDefinition = new ArrayList<>();
        words = new ArrayList<>();
        this.takenQuizList = new ArrayList<>();
    }

    public String getOwnerUserName() {
        return ownerUserName;
    }

    public void setOwnerUserName(String ownerUserName) {
        this.ownerUserName = ownerUserName;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Word> getWords() {
        return words;
    }

    public void setWords(Map<String, String> words) {
        for(Map.Entry<String, String> entry: words.entrySet()){
            this.words.add(new Word(entry.getKey(), entry.getValue()));
        }
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public List<IncorrectDefinition> getIncorrectDefinition() {
        return incorrectDefinition;
    }

    public void setIncorrectDefinition(Collection<String> incorrectDefinition) {
        for(String str : incorrectDefinition){
            this.incorrectDefinition.add(new IncorrectDefinition(str));
        }
    }

    public void setIncorrectDefinition(List<IncorrectDefinition> incorrectDefinition) {
        this.incorrectDefinition = incorrectDefinition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public QuizStatistics getQuizStatistics(String myUserName) {
        //sort by date
        Collections.sort(this.takenQuizList, new SortByDate());
        QuizStatistics quizStatistics = new QuizStatistics();
        for(TakenQuiz takenQuiz : this.takenQuizList){
            //my first score
            if(takenQuiz.getStudentUserName().equals(myUserName) && quizStatistics.getFirstScore() == null){
                quizStatistics.setFirstScore(new Float(takenQuiz.getPercentage()));
                quizStatistics.setFirstScoreDateTime(takenQuiz.getDate());
            }

            //my last played
            if(takenQuiz.getStudentUserName().equals(myUserName) ){
                quizStatistics.setLastPlayedDateTime(takenQuiz.getDate());
            }

            //first three person to get perfect
            if(takenQuiz.getNumberOfCorrect() == takenQuiz.getTotalNumberOfQuestions()
                    && quizStatistics.getFirstThreeStudentGotPerfectScore().size() < 3){
                quizStatistics.addPerfectScoreUser(takenQuiz.getStudentUserName());
            }

            //my highest score
            if(takenQuiz.getStudentUserName().equals(myUserName) &&
                    (
                            quizStatistics.getHighestScore() == null ||
                                    takenQuiz.getPercentage() > quizStatistics.getHighestScore()
                    )
                    )
            {
                quizStatistics.setHighestScore(new Float(takenQuiz.getPercentage()));
                quizStatistics.setHighestScoreDateTime(takenQuiz.getDate());
            }

        }
        return quizStatistics;
    }

    private class SortByDate implements Comparator<TakenQuiz> {
        @Override
        public int compare(TakenQuiz o1, TakenQuiz o2) {
            return o1.getDate().compareTo(o2.getDate());
        }
    }

    public boolean isCreatedByUser(String user) {
        return ownerUserName != null && this.ownerUserName.equals(user);
    }

    @Override
    public int compareTo(@NonNull Quiz o) {
        if(o != null && o.getName() != null && this.name != null) {
            return name.compareTo(o.getName());
        } else {
            throw new NullPointerException("null pointer");
        }
    }

    public boolean isTakenBy(String userName){
        for(TakenQuiz q : takenQuizList){
            if(q.getStudentUserName().equals(userName)){
                return true;
            }
        }
        return false;
    }

    public void addTakenQuiz(TakenQuiz quiz){
        this.takenQuizList.add(quiz);
    }

    public List<TakenQuiz> getTakenQuizList(){
        return takenQuizList;
    }

    public void setTakenQuizList(List<TakenQuiz> list){
        this.takenQuizList = list;
    }

}
