package com.ijikod.punkapp

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.NoMatchingViewException
import androidx.test.espresso.ViewAssertion
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.ijikod.punkapp.ui.Activities.MainActivity
import com.ijikod.punkapp.ui.BeerViewHolder
import org.hamcrest.CoreMatchers
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {

    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)


    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.ijikod.punkapp", appContext.packageName)
    }


    @Test
    fun recyclerViewDisplayedCheck(){
        //this test will  fail until the exact item count of the list is known and can be asserted
        Espresso.onView(withId(R.id.list)).check(CustomAssertions.hasItemCount(10))
    }


    @Test
    fun clickListItem(){
        Espresso.onView(withId(R.id.list))
            .perform(RecyclerViewActions.actionOnItemAtPosition<BeerViewHolder>(5, click()))

        Espresso.onView(withId(R.id.details_bear_image)).check(ViewAssertions.matches(
            isDisplayed()))

        Espresso.onView(withId(R.id.description_txt)).check(ViewAssertions.matches(
            isDisplayed()))


        Espresso.onView(withId(R.id.info_txt)).check(ViewAssertions.matches(
            isDisplayed()))
    }






    class CustomAssertions {
        companion object {
            fun hasItemCount(count: Int): ViewAssertion {
                return RecyclerViewItemCountAssertion(count)
            }
        }

        private class RecyclerViewItemCountAssertion(private val count: Int) : ViewAssertion {

            override fun check(view: View, noViewFoundException: NoMatchingViewException?) {
                if (noViewFoundException != null) {
                    throw noViewFoundException
                }

                if (view !is RecyclerView) {
                    throw IllegalStateException("The asserted view is not RecyclerView")
                }

                if (view.adapter == null) {
                    throw IllegalStateException("No adapter is assigned to RecyclerView")
                }

                ViewMatchers.assertThat("RecyclerView item count", view.adapter?.itemCount, CoreMatchers.equalTo(count))
            }
        }
    }
}
