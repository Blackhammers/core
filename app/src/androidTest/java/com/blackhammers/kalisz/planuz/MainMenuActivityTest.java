package com.blackhammers.kalisz.planuz;

import android.support.test.filters.LargeTest;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

@RunWith(AndroidJUnit4.class)
@MediumTest
public class MainMenuActivityTest {

    private String mStringToBetyped;

    @Rule
    public ActivityTestRule<MainMenuActivity> mActivityRule = new ActivityTestRule<>(
            MainMenuActivity.class);


    @Before
    public void initValidString() {
        mStringToBetyped = "Informatyki";
    }

    @Test
    public void CheckIsSearchPoolWorks() {

        onView(withId(R.id.groupsPlanId))
                .perform(click());
        onView(withId(R.id.action_search))
                .perform(click());
        onView(withId(android.support.design.R.id.search_src_text))
                .perform(typeText("Informatyki"));
        closeSoftKeyboard();
        onView(withId(R.id.action_search))
                .perform(click());
        onView(withId(android.support.design.R.id.search_src_text))
                .check(matches(withText(mStringToBetyped)));
        onView(withText("Informatyki"))
                .check(matches(isDisplayed()));
        pressBack();
        pressBack();
    }
}