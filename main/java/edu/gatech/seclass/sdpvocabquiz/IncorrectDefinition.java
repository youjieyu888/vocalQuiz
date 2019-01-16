package edu.gatech.seclass.sdpvocabquiz;

import com.orm.SugarRecord;

import java.io.Serializable;

public class IncorrectDefinition extends SugarRecord implements Serializable{
    private long quizId = -1;
    private String definition_;

    //default constructor
    public IncorrectDefinition(){
        this.definition_ = "";
    }

    public IncorrectDefinition(String definition){
        this.definition_ = definition;
    }

    //getter & setter
    public String getDefinition(){ return definition_;}

    public void setDefinition(String definition){ this.definition_ = definition;}

    public void setQuizId(long i){ this.quizId = i; }

}