package com.mobile.mavelapp.view.main

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import com.mobile.mavelapp.R
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4


@RunWith(JUnit4::class)
@LargeTest
class MainActivityTest {

    @Rule
    @JvmField
    val activity = ActivityTestRule(MainActivity::class.java)

    @Test
    fun whenClickOnSearchIconShouldShowRecycleSearchedAndHideFirstRecycle() {
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerSearched)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchView)).perform(click())
        onView(withId(R.id.recyclerSearched)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(not(isDisplayed())))
    }

}