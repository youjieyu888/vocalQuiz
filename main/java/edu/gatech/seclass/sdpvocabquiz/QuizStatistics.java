package edu.gatech.seclass.sdpvocabquiz;
import com.orm.SugarRecord;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class QuizStatistics extends SugarRecord {
    private Date lastPlayedDateTime;
    private Float firstScore;
    private Date firstScoreDateTime;
    private Float highestScore;
    private Date highestScoreDateTime;
    private Set<String> firstThreeStudentGotPerfectScore = new HashSet<>();


    public QuizStatistics(){}

    public Date getLastPlayedDateTime() {
        return lastPlayedDateTime;
    }

    public void setLastPlayedDateTime(Date lastPlayedDateTime) {
        this.lastPlayedDateTime = lastPlayedDateTime;
    }

    public Float getFirstScore() {
        return firstScore;
    }

    public void setFirstScore(Float firstScore) {
        this.firstScore = firstScore;
    }

    public Date getFirstScoreDateTime() {
        return firstScoreDateTime;
    }

    public void setFirstScoreDateTime(Date firstScoreDateTime) {
        this.firstScoreDateTime = firstScoreDateTime;
    }

    public Float getHighestScore() {
        return highestScore;
    }

    public void setHighestScore(Float highestScore) {
        this.highestScore = highestScore;
    }

    public Date getHighestScoreDateTime() {
        return highestScoreDateTime;
    }

    public void setHighestScoreDateTime(Date highestScoreDateTime) {
        this.highestScoreDateTime = highestScoreDateTime;
    }

    public void addPerfectScoreUser(String user){
        this.firstThreeStudentGotPerfectScore.add(user);
    }

    public List<String> getFirstThreeStudentGotPerfectScore() {
        List<String> firstThree =  new ArrayList<>( firstThreeStudentGotPerfectScore );
        Collections.sort(firstThree);
        return firstThree;
    }

}
