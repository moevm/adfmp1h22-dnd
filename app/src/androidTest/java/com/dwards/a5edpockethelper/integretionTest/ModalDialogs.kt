package com.dwards.a5edpockethelper.integretionTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dwards.a5edpockethelper.MainActivity
import com.dwards.a5edpockethelper.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class ModalDialogs {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun checkHPChangeDialog() {

        onView(ViewMatchers.withId(R.id.HPLayout)).check(matches(isCompletelyDisplayed()));
        onView(ViewMatchers.withId(R.id.HPLayout)).perform(ViewActions.click())

        onView(ViewMatchers.withId(R.id.DamageCheck)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.HealCheck)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.TempHPCheck)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.SaveButton)).check(matches(isDisplayed()))

    }

}