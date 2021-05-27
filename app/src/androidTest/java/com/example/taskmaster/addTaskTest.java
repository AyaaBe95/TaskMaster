package com.example.taskmaster;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class addTaskTest {
    @Rule
    public ActivityScenarioRule<AddTask> activityRule =
            new ActivityScenarioRule<>(AddTask.class);

    @Test
    public void  addTaskTest() {
        onView(withId(R.id.textView2)).check(matches(isDisplayed()));
    }

    //test setting btn

    @Test
    public void  submitButtonTest() {
        onView(withId(R.id.add1)).perform(click());;
    }

    @Test
    public void  submitButtonTest2() {
        onView(withId(R.id.add1)).check(matches(isDisplayed()));
    }

    @Test
    public void  titleTest() {
        onView(withId(R.id.textView4)).check(matches(isDisplayed()));
    }
    @Test
    public void  bodyTest() {
        onView(withId(R.id.textView5)).check(matches(isDisplayed()));
    }
    @Test
    public void  stateTest() {
        onView(withId(R.id.textView6)).check(matches(isDisplayed()));
    }
}
