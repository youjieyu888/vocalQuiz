package edu.gatech.seclass.sdpvocabquiz;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public ActivityTestRule<LoginActivity> activityTestRule = new ActivityTestRule<>(LoginActivity.class);

    @Test
    public void checkContainEveryInputs() {
        onView(withId(R.id.register)).check(matches(isDisplayed()));
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        onView(withId(R.id.userName)).check(matches(isDisplayed()));
    }

    @Test
    public void checkRegisterButtonLoadRegisterActivity() {
        Instrumentation.ActivityMonitor monitor = getInstrumentation()
                .addMonitor(RegisterActivity.class.getName(), null, false);

        onView(withId(R.id.register)).perform(click());
        RegisterActivity registerActivity = (RegisterActivity) monitor.waitForActivityWithTimeout(100);

        assertNotNull(registerActivity);
        assertEquals(1, monitor.getHits());

        getInstrumentation().removeMonitor(monitor);
    }


 }
