package com.dwards.a5edpockethelper.integretionTest

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.matcher.IntentMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import com.dwards.a5edpockethelper.MainActivity
import com.dwards.a5edpockethelper.R
import com.google.android.gms.auth.api.signin.internal.SignInHubActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
@LargeTest
class SwipeTabsAuth {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun testCorrectTabSwipesForAuth() {

        Intents.init()

        onView(withId(R.id.pager)).check(matches(isCompletelyDisplayed()))

        for (i in 0..2) {
            onView(withId(R.id.pager)).perform(ViewActions.swipeLeft())
        }

        Intents.intended(IntentMatchers.hasComponent(SignInHubActivity::class.java.name))

        Intents.release()
    }

}
