package edu.gatech.seclass.sdpvocabquiz;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.rule.ActivityTestRule;
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
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class RegisterActivityTest {

    @Rule
    public ActivityTestRule<RegisterActivity> activityTestRule = new ActivityTestRule<>(RegisterActivity.class);


    private static Matcher<View> matchEditTextError (final String expectedError) {
        return new TypeSafeMatcher<View>() {
            @Override
            protected boolean matchesSafely(View view) {
                if (view instanceof EditText) {
                    return ((EditText)view).getError().toString().equalsIgnoreCase(expectedError);
                }
                return false;
            }

            @Override
            public void describeTo(Description description) { }
        };
    }

    @Test
    public void testContainEveryInputs() {
        onView(withId(R.id.username)).check(matches(isDisplayed()));
        onView(withId(R.id.realname)).check(matches(isDisplayed()));
        onView(withId(R.id.major)).check(matches(isDisplayed()));
        onView(withId(R.id.seniority)).check(matches(isDisplayed()));
        onView(withId(R.id.email)).check(matches(isDisplayed()));
    }

    @Test
    public void testUsernameIsEmpty() {
        onView(withId(R.id.username)).perform(clearText());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.username)).check(matches(matchEditTextError("Username required")));
    }

    @Test
    public void testRealNameIsEmpty() {
        onView(withId(R.id.realname)).perform(clearText());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.realname)).check(matches(matchEditTextError("Real name required")));
    }

    @Test
    public void testMajorIsEmpty() {
        onView(withId(R.id.major)).perform(clearText());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.major)).check(matches(matchEditTextError("Major required")));
    }


    @Test
    public void testEmailIsInvalid() {
        onView(withId(R.id.email)).perform(typeText("a.com"));
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.email)).check(matches(matchEditTextError("Invalid email address")));
    }

    @Test
    public void testEmailIsEmpty() {
        onView(withId(R.id.email)).perform(clearText());
        onView(withId(R.id.register)).perform(click());
        onView(withId(R.id.email)).check(matches(matchEditTextError("Email required")));
    }
}
