package edu.gatech.seclass.sdpvocabquiz;

import com.orm.SugarRecord;

import java.io.Serializable;

public class Word extends SugarRecord implements Serializable {
    private long quizId = -1;
    private String word_;
    private String definition_;
    private boolean used_;

    //default constructor
    public Word(){
        this.word_ = "";
        this.definition_ = "";
        this.used_ = false;
    }

    //additional constructor
    public Word(String word, String definition){
        this.word_ = word;
        this.definition_ = definition;
        this.used_ = false;
    }

    //getter & setter
    public String getWord() { return word_;}

    public String getdefinition() {
        return definition_;
    }

    public Boolean isUsed() { return used_;}


    public void setWord(String word) { this.word_ = word;}

    public void setDefinition(String definition){ this.definition_ = definition; }

    public void setUsed_(boolean used) { this.used_ = used; }

    public void setQuizId(long i){ this.quizId = i; }
}
