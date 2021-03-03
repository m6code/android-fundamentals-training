package com.m6code.twoactivities;

import android.content.Context;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ActivityInputOutputTest {

    @Rule
    public ActivityScenarioRule myActivity = new ActivityScenarioRule<>(MainActivity.class);



    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        assertEquals("com.m6code.twoactivities", appContext.getPackageName());
    }

    @Test
    public void activityLaunch(){
        onView(withId(R.id.button_main)).perform(click());  // Clicks a button
        onView(withId(R.id.text_header)).check(matches(isDisplayed())); // Check if the header is displayed
        onView(withId(R.id.button_second)).perform(click());
        onView(withId(R.id.text_header_reply)).check(matches(isDisplayed()));
    }

    @Test
    public void textInputOutput(){
        onView(withId(R.id.editText_main)).perform(typeText("This is a test.")); // Types in an editText Field
        onView(withId(R.id.button_main)).perform(click());
        onView(withId(R.id.text_message)).check(matches(withText("This is a test."))); // Check if a text matches what was entered
    }
}