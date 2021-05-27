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
public class SettingsTest {

    @Rule
    public ActivityScenarioRule<UserSetting> activityRule =
            new ActivityScenarioRule<>(UserSetting.class);

    @Test
    public void  userNameTest() {
        onView(withId(R.id.username)).check(matches(isDisplayed()));
    }

    @Test
    public void  settingsButtonTest() {
        onView(withId(R.id.save)).perform(click());;
    }

    @Test
    public void  settingsButtonTest2() {
        onView(withId(R.id.save)).check(matches(isDisplayed()));
    }
}
