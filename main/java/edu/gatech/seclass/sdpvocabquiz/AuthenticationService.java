package edu.gatech.seclass.sdpvocabquiz;

import android.util.Log;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class AuthenticationService {

    private static final AuthenticationService instance = new AuthenticationService();
    private Map<String, Student> registeredStudents = new HashMap<>();

    private AuthenticationService(){}

    public static AuthenticationService getInstance(){
        return instance;
    }

    /*
     * This method is used for user to login.
     * If user is valid, return QuizeManger of the user
     * If not, return null - this can be modified -
     *
     * @param String username
     * @return QuizeManager
     */
    public Student login(String username){
        if(!registeredStudents.containsKey(username)){
            return null;
        }
        Student s = registeredStudents.get(username);
        //create a quiz manager for student
        //after this we we use QuizManager.getInstance()
        QuizManager.setStudent(s);
        return s;
    }

    /*
     * This method register new user.
     * If user is registered successfully, return true
     * If not, return false
     *
     * @param Student student
     * @return boolean
     */
    public boolean register(Student student){
        if(registeredStudents.containsKey(student.getUsername())){
           return false;
        }

        student.save();
        registeredStudents.put(student.getUsername(), student);
        return true;
    }


    // I just added this method for validation of username.
    // If we will validation input right after input is filled, this method can be used
    // if validation is done after clicking register button, this method is not necessary

    /*
     * This method check if given username exists or not.
     *
     * @param String username
     * @return boolean
     */
    public boolean checkIfUsernameExist(String username){
        if(registeredStudents.containsKey(username)){
            return true;
        }

        return false;
    }



    // I'm not sure 'getName(username): String' from the design
    //is supposed to do this..

    //will help out later on when we need to know who took the quiz

    /*
     * This method returns realname by given username.
     *
     * @param String username
     * @return String realname
     */
    public String getName(String username){
        if(!registeredStudents.containsKey(username)){
            return "";
        }
        return (registeredStudents.get(username).getRealName());
    }


    public void populateRegisteredStudents(){
        List<Student> students = new ArrayList<>();
        try {
            students = Student.listAll(Student.class);
        }
        catch (Exception e){
            Log.e("tag", "populateRegisteredStudents: Error", e);
        }
        for(Student student : students){
            registeredStudents.put(student.getUsername(), student);
        }
    }
}