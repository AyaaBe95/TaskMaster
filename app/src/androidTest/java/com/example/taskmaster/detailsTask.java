package com.example.taskmaster;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class detailsTask {

    @Rule
    public ActivityScenarioRule<Details> activityRule =
            new ActivityScenarioRule<>(Details.class);

    @Test
    public void  titleTest() {
        onView(withId(R.id.textView)).check(matches(isDisplayed()));
    }
    @Test
    public void  bodyTest() {
        onView(withId(R.id.textView7)).check(matches(isDisplayed()));
    }
    @Test
    public void  stateTest() {
        onView(withId(R.id.textView8)).check(matches(isDisplayed()));
    }
}

