package com.dwards.a5edpockethelper.integretionTest

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dwards.a5edpockethelper.MainActivity
import com.dwards.a5edpockethelper.R
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
@LargeTest
class WeaponElement {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCorrectAddBtn() {

        onView(withId(R.id.pager)).check(matches(isCompletelyDisplayed()))

        onView(withId(R.id.pager)).perform(swipeLeft())
        onView(withId(R.id.pager)).perform(swipeLeft())

        onView(withId(R.id.StatLayout)).check(matches(isCompletelyDisplayed()))
        onView(withId(R.id.StatLayout)).perform(click())
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(R.id.StatLayout)).perform(click())
    }

}

fun waitFor(delay: Long): ViewAction {
    return object : ViewAction {
        override fun getConstraints(): Matcher<View> = isRoot()
        override fun getDescription(): String = "wait for $delay milliseconds"
        override fun perform(uiController: UiController, v: View?) {
            uiController.loopMainThreadForAtLeast(delay)
        }
    }
}