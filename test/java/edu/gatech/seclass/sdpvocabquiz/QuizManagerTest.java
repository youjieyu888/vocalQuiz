package edu.gatech.seclass.sdpvocabquiz;

import com.orm.SugarContext;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class QuizManagerTest {
    Student s;
    Quiz q;
    @Before
    public void set_up(){
        s = new Student("msid", "Mustafa Sidiqi", "cs", Student.Seniority.FRESHMAN, "ksid@yahoo.com");
        q = getQuiz(s);


    }


    //able to add
    @Test
    public void addQuiz1() {
        //arrange
        TreeSet<Quiz> treeSet = new TreeSet<>();
        MyQuizList myQuizList = new MyQuizList(treeSet, s);
        //act
        myQuizList.addQuiz(q);
        //assert
        assertTrue(myQuizList.iterator().hasNext());
        assertEquals(myQuizList.iterator().next(), q);
    }


    //unique quiz
    @Test
    public void addQuiz2() {
        //arrange
        TreeSet<Quiz> treeSet = new TreeSet<>();
        MyQuizList myQuizList = new MyQuizList(treeSet, s);
        //act
        myQuizList.addQuiz(q);
        myQuizList.addQuiz(q);
        //assert
        Iterator<Quiz> iterator = myQuizList.iterator();
        assertTrue(iterator.hasNext());
        assertEquals(iterator.next(), q);
        assertFalse(iterator.hasNext());

    }


    //multiple diff quiz
    @Test
    public void addQuiz3() {
        //arrange
        TreeSet<Quiz> treeSet = new TreeSet<>();
        MyQuizList myQuizList = new MyQuizList(treeSet, s);
        //act
        for(int i = 0; i < 10; i++) {
            myQuizList.addQuiz(getQuiz(s));
        }
        //assert
        int i= 0;
        Iterator<Quiz> iterator = myQuizList.iterator();
        while(iterator.hasNext()){
            i++;
            iterator.next();
        }
        assertEquals(i, 10);

    }

    //del quiz
    @Test
    public void delQuiz1() {
        //arrange
        TreeSet<Quiz> treeSet = new TreeSet<>();
        MyQuizList myQuizList = new MyQuizList(treeSet, s);
        myQuizList.addQuiz(q);
        //act
        myQuizList.delQuiz(q.getName());
        //assert
        assertFalse(myQuizList.iterator().hasNext());
    }


    //del quiz u didn't create
    @Test
    public void delQuiz2() {
        //arrange
        Student other = new Student("other", "ms", "cs", Student.Seniority.FRESHMAN, "e@l.com");
        Quiz otherQuiz = getQuiz(other);
        Quiz myQuiz = getQuiz(s);
        TreeSet<Quiz> treeSet = new TreeSet<>();
        treeSet.add(otherQuiz);
        MyQuizList myQuizList = new MyQuizList(treeSet, s);
        myQuizList.addQuiz(myQuiz);
        //act
        myQuizList.delQuiz(otherQuiz.getName());
        //assert
        Iterator<Quiz> iterator = myQuizList.iterator();
        assertTrue(iterator.next().equals(myQuiz));
        assertFalse(iterator.hasNext());
        assertTrue(treeSet.contains(otherQuiz));
    }


    //take quiz
    @Test
    public void takeQuiz() {
        //Arrange
        q.setOwnerUserName("others");
        TreeSet<Quiz> treeSet = new TreeSet<>();
        treeSet.add(q);
        TakenQuizList takenQuizList = new TakenQuizList(treeSet, s);
        //assert
        assertFalse(takenQuizList.iterator().hasNext());
    }


    @Test
    public void takeQuiz2() {
        //Arrange
        q.setOwnerUserName("others");
        TreeSet<Quiz> treeSet = new TreeSet<>();
        treeSet.add(q);
        TakenQuizList takenQuizList = new TakenQuizList(treeSet, s);
        //act
        TakenQuiz takenQuiz = new TakenQuiz("not me", 5, 10, new Date());
        q.addTakenQuiz(takenQuiz);
        //assert
        //test taken not by me
        assertFalse(takenQuizList.iterator().hasNext());
    }



    @Test
    public void takeQuiz3() {
        //Arrange
        q.setOwnerUserName("others");
        TreeSet<Quiz> treeSet = new TreeSet<>();
        treeSet.add(q);
        TakenQuizList takenQuizList = new TakenQuizList(treeSet, s);
        //act
        TakenQuiz takenQuiz = new TakenQuiz(s.getUsername(), 5, 10, new Date());
        q.addTakenQuiz(takenQuiz);
        //assert
        //test taken by me
        assertTrue(takenQuizList.iterator().hasNext());
    }


    @Test
    public void takeQuiz4() {
        //Arrange
        Student other = new Student("other", "ms", "cs", Student.Seniority.FRESHMAN, "e@l.com");
        Student useless = new Student("useless_user", "ms", "cs", Student.Seniority.SENIOR, "e@l.com");
        q.setOwnerUserName("some_user");
        TreeSet<Quiz> treeSet = new TreeSet<>();
        treeSet.add(q);
        TakenQuizList takenQuizList = new TakenQuizList(treeSet, s);
        TakenQuizList takenQuizList2 = new TakenQuizList(treeSet, other);
        TakenQuizList takenQuizList3 = new TakenQuizList(treeSet, useless);
        List<TakenQuiz> list = new ArrayList<>();
        TakenQuiz takenQuizByMe1 = new TakenQuiz(s.getUsername(), 5, 30, new Date());
        TakenQuiz takenQuizbyOther = new TakenQuiz(other.getUsername(), 7, 10, new Date());
        TakenQuiz takenQuizByMe2 = new TakenQuiz(s.getUsername(), 3, 100, new Date());
        list.add(takenQuizByMe1);
        list.add(takenQuizByMe2);
        list.add(takenQuizbyOther);
        //act
        q.addTakenQuiz(takenQuizByMe1);
        q.addTakenQuiz(takenQuizByMe2);
        q.addTakenQuiz(takenQuizbyOther);
        //assert
        //test taken by me
        assertTrue(takenQuizList.iterator().hasNext());
        //taken by other
        assertTrue(takenQuizList2.iterator().hasNext());
        //taken by useless
        assertFalse(takenQuizList3.iterator().hasNext());
    }




    private Quiz getQuiz(Student s){
        List<Word> words = new ArrayList<>();
        words.add(new Word("java", "a coffee"));
        List<IncorrectDefinition> list = new LinkedList<>();
        list.add(new IncorrectDefinition("wrong def 1"));
        list.add(new IncorrectDefinition("wrong def 2"));
        list.add(new IncorrectDefinition("wrong def 3"));
        q = new Quiz((new Random().nextInt())+"", "desc", words, list);
        q.setOwnerUserName(s.getUsername());
        return q;
    }


}