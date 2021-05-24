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

public class HomeTest {
    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Test
    public void  myTasksTest() {
        onView(withText("My Tasks")).check(matches(isDisplayed()));
    }

    @Test
    public void testImage() {

        onView(withId(R.id.imageView)).check(matches(isDisplayed()));
    }

    // test userName
    @Test
    public void  testUserName() {
        onView(withId(R.id.welcome)).check(matches(isDisplayed()));
    }
    //test setting btn

    @Test
    public void  settingsButtonTest() {
        onView(withId(R.id.uSettings)).check(matches(isClickable()));
    }

    @Test
    public void  settingsButtonTest2() {
        onView(withId(R.id.uSettings)).check(matches(isDisplayed()));
    }
    //test allTasks btn
    @Test
    public void  allTaskButtonTest1() {
        onView(withId(R.id.alltask)).check(matches(isClickable()));
    }
    @Test
    public void  allTaskButtonTest2() {
        onView(withId(R.id.alltask)).check(matches(isDisplayed()));
    }
    ////test addTask btn

    @Test
    public void  addTaskButtonTest1() {
        onView(withId(R.id.addtask)).check(matches(isClickable()));
    }
    @Test
    public void  addTaskButtonTest2() {
        onView(withId(R.id.addtask)).check(matches(isDisplayed()));
    }

    // Test RecyclerView
    @Test
    public void testRecyclerView() {
        onView(withId(R.id.recyclerView)).perform(click());
    }

}
