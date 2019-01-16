package edu.gatech.seclass.sdpvocabquiz;

import android.app.Instrumentation;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class MainMenuActivityTest {

    Student student = AuthenticationService.getInstance().login("testuser");

    @Rule
    public ActivityTestRule<MainMenuActivity> activityTestRule = new ActivityTestRule<>(MainMenuActivity.class);

    @Before
    public void setup(){
        QuizManager.setStudent(student);
    }

    @Test
    public void checkContainEveryInputs() {
        onView(withId(R.id.add)).check(matches(isDisplayed()));
        onView(withId(R.id.view)).check(matches(isDisplayed()));
        onView(withId(R.id.remove)).check(matches(isDisplayed()));
        onView(withId(R.id.question)).check(matches(isDisplayed()));
    }

    @Test
    public void checkAddButtonLoadAddActivity() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(AddQuizActivity.class.getName(), null, false);

        onView(withId(R.id.add)).perform(click());
        AddQuizActivity activity = (AddQuizActivity) monitor.waitForActivityWithTimeout(100);

        assertNotNull(activity);
        assertEquals(1, monitor.getHits());

        getInstrumentation().removeMonitor(monitor);
    }

    @Test
    public void checkRemoveButtonLoadRemoveViewQuiz() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(RemoveViewQuiz.class.getName(), null, false);

        onView(withId(R.id.remove)).perform(click());
        RemoveViewQuiz activity = (RemoveViewQuiz) monitor.waitForActivityWithTimeout(100);

        assertNotNull(activity);
        assertEquals(1, monitor.getHits());

        getInstrumentation().removeMonitor(monitor);
    }

    @Test
    public void checkViewButtonLoadViewQuiz() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(ViewQuiz.class.getName(), null, false);

        onView(withId(R.id.view)).perform(click());
        ViewQuiz activity = (ViewQuiz) monitor.waitForActivityWithTimeout(100);

        assertNotNull(activity);
        assertEquals(1, monitor.getHits());

        getInstrumentation().removeMonitor(monitor);
    }

    @Test
    public void checkQuestionButtonLoadChooseQuizActivity() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(ChooseQuizActivity.class.getName(), null, false);

        onView(withId(R.id.question)).perform(click());
        ChooseQuizActivity activity = (ChooseQuizActivity) monitor.waitForActivityWithTimeout(100);

        assertNotNull(activity);
        assertEquals(1, monitor.getHits());

        getInstrumentation().removeMonitor(monitor);
    }

 }
