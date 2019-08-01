package com.mobile.mavelapp.view.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.espresso.util.TreeIterables
import androidx.test.filters.LargeTest
import com.mobile.mavelapp.R
import com.mobile.mavelapp.view.detail.HeroDetailActivity
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.not
import org.junit.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import java.util.concurrent.CountDownLatch


@RunWith(JUnit4::class)
@LargeTest
class MainActivityTest {

    @get:Rule
    val activity = IntentsTestRule(MainActivity::class.java)

    @Test
    fun whenClickOnSearchIconShouldShowRecycleSearchedAndHideFirstRecycle() {
        onView(withId(R.id.searchView)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(isDisplayed()))
        onView(withId(R.id.recyclerSearched)).check(matches(not(isDisplayed())))
        onView(withId(R.id.searchView)).perform(click())
        onView(withId(R.id.recyclerSearched)).check(matches(isDisplayed()))
        onView(withId(R.id.recycler)).check(matches(not(isDisplayed())))
    }

    @Test
    fun whenClickOnRecycleViewItemShouldGoToHeroDetailActivity(){
        Assert.assertTrue(viewExists(allOf(withId(R.id.ivHeroList),withEffectiveVisibility(Visibility.VISIBLE)),5000)) //wait 5 seconds
        onView(withId(R.id.recycler)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, clickOnViewChild(R.id.ivHeroList)))
        intended(hasComponent(HeroDetailActivity::class.java!!.getName()))
    }

    fun clickOnViewChild(viewId: Int) = object : ViewAction {
        override fun getConstraints() = null

        override fun getDescription() = "Click on a child view with specified id."

        override fun perform(uiController: UiController, view: View) = click().perform(uiController, view.findViewById<View>(viewId))
    }

    @Throws(InterruptedException::class)
    fun viewExists(viewMatcher: Matcher<View>, millis: Long): Boolean {
        val found = arrayOfNulls<Boolean>(1)

        val latch = CountDownLatch(1)
        val action = object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return isRoot()
            }

            override fun getDescription(): String {
                return "wait for a specific view with id <$viewMatcher> during $millis millis."
            }

            override fun perform(uiController: UiController, view: View) {
                uiController.loopMainThreadUntilIdle()
                val startTime = System.currentTimeMillis()
                val endTime = startTime + millis


                do {
                    for (child in TreeIterables.breadthFirstViewTraversal(view)) {

                        if (viewMatcher.matches(child)) {
                            found[0] = true
                            latch.countDown()
                            return
                        }
                    }

                    uiController.loopMainThreadForAtLeast(50)
                } while (System.currentTimeMillis() < endTime)

                found[0] = false
                latch.countDown()
            }
        }
        onView(isRoot()).perform(action)

        latch.await()
        return found[0]!!
    }
}