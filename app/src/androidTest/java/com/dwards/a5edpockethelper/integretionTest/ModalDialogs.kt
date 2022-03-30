package com.dwards.a5edpockethelper.integretionTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
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

        onView(withId(R.id.HPLayout)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.HPLayout)).perform(click())

        onView(withId(R.id.DamageCheck)).check(matches(isDisplayed()))
        onView(withId(R.id.HealCheck)).check(matches(isDisplayed()))
        onView(withId(R.id.TempHPCheck)).check(matches(isDisplayed()))
        onView(withId(R.id.SaveButton)).check(matches(isDisplayed()))

    }

    @Test
    fun checkToolsProfDialog() {

        onView(withId(R.id.pager)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.pager)).perform(swipeLeft())

        for (i in 0..5) {
            onView(withId(R.id.fragment_place_skills)).perform(swipeUp())
        }

        onView(withId(R.id.ToolsProficiencyLayout)).check(matches(isDisplayed()))

        onView(withId(R.id.ToolsProficiencyLayout)).perform(click())
        onView(withId(R.id.AddToolsButton)).check(matches(isCompletelyDisplayed()))
    }

}