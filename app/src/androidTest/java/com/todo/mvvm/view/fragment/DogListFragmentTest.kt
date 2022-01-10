package com.todo.mvvm.view.fragment

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.todo.mvvm.R
import com.todo.mvvm.view.activity.DogListActivity
import com.todo.mvvm.view.adapter.DogListAdapter
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class DogListFragmentTest {

    @get:Rule
    var hiltRule = HiltAndroidRule(this)

    @Before
    fun setUp() {
        hiltRule.inject()
    }

    @Test
    fun test_list_fragment_when_activity_launched_displayed_fragment() {
        mockActivityScenario()

        onView(withId(R.id.listFragment)).check(matches(isDisplayed()))
    }

    @Test
    fun test_list_fragment_when_launched_displayed_recycler_view() {
        mockActivityScenario()

        onView(withId(R.id.rvListItems)).check(matches(isDisplayed()))
    }

    @Test
    fun test_list_fragment_when_dog_list_popup_displayed_progressBar() {
        ActivityScenario.launch(DogListActivity::class.java)

        onView(withId(R.id.progressBar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_list_fragment_when_item_clicked_displayed_detail_fragment() {
        mockActivityScenario()

        onView(withId(R.id.rvListItems)).perform(
            RecyclerViewActions.actionOnItemAtPosition<DogListAdapter.DogListViewHolder>(
                2,
                ViewActions.click()
            )
        )
        mockThreadSleep(1000)

        onView(withId(R.id.detailFragment)).check(matches(isDisplayed()))
    }

    private fun mockActivityScenario() {
        ActivityScenario.launch(DogListActivity::class.java)
        mockThreadSleep(100)
    }

    private fun mockThreadSleep(milliSeconds: Long) {
        try {
            Thread.sleep(milliSeconds)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}