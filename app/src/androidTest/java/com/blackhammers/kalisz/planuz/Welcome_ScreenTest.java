package com.blackhammers.kalisz.planuz;

import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;

import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;


@RunWith(AndroidJUnit4.class)
@MediumTest
public class Welcome_ScreenTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<Welcome_Screen> mActivityRule = new ActivityTestRule<>(
            Welcome_Screen.class);


    @Before
    public void initValidString() {
        mStringToBetyped = "Welcome Screen Test Sample";
    }

    @Test
    public void CheckIsWelcomeScreenWorks() {

    }
}