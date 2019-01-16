package edu.gatech.seclass.sdpvocabquiz;

import com.orm.SugarRecord;
import com.orm.dsl.NotNull;
import com.orm.dsl.Unique;

import java.io.Serializable;

public class Student extends SugarRecord implements Serializable {

    @NotNull
    @Unique
    private String username;
    private String realName;
    private String major;
    private Seniority seniorityLevel;
    private String email;

    public Student(String username, String realName, String major, Seniority seniorityLevel, String email){
        this.username=username;
        this.realName=realName;
        this.major=major;
        this.seniorityLevel=seniorityLevel;
        this.email=email;
    }

    public Student(){}

    enum Seniority { FRESHMAN, SOPHOMORE, JUNIOR, SENIOR }

    public String getUsername() {
        return username;
    }

    public String getRealName() {
        return realName;
    }

    public String getMajor() {
        return major;
    }

    public Seniority getSeniorityLevel() {
        return seniorityLevel;
    }

    public String getEmail() {
        return email;
    }


    public void setRealName(String realName) {
        this.realName = realName;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public void setSeniorityLevel(Seniority seniorityLevel) {
        this.seniorityLevel = seniorityLevel;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
