package edu.gatech.seclass.sdpvocabquiz;

import com.orm.SugarRecord;

import java.io.Serializable;
import java.util.Date;

public class TakenQuiz extends SugarRecord implements Serializable {
    long quizId = -1;
    private String studentUserName;
    private int numberOfCorrect;
    private int totalNumberOfQuestions;
    private Date date;

    public TakenQuiz(){
    }

    public TakenQuiz(String studentUserName, int numberOfCorrect, int totalNumberOfQuestions, Date date) {
        this.studentUserName = studentUserName;
        this.numberOfCorrect = numberOfCorrect;
        this.totalNumberOfQuestions = totalNumberOfQuestions;
        this.date = date;
    }

    public int getPercentage(){
        return (100*numberOfCorrect)/totalNumberOfQuestions;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public int getNumberOfCorrect() {
        return numberOfCorrect;
    }

    public int getTotalNumberOfQuestions() {
        return totalNumberOfQuestions;
    }

    public Date getDate() {
        return date;
    }

    public void setNumberOfCorrect(int numberOfCorrect) {
        this.numberOfCorrect = numberOfCorrect;
    }

    public void setTotalNumberOfQuestions(int totalNumberOfQuestions) {
        this.totalNumberOfQuestions = totalNumberOfQuestions;
    }

    public void setQuizId(long i){
        this.quizId = i;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

