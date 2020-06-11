package com.ijikod.punkapp

import android.util.Log
import android.view.View
import androidx.fragment.app.testing.launchFragment
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.google.common.truth.Truth.assertThat
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.ui.Activities.Inspector
import com.ijikod.punkapp.ui.Activities.MainActivity
import com.ijikod.punkapp.ui.BeerViewHolder
import com.ijikod.punkapp.ui.Fragments.ListFragment
import com.ijikod.punkapp.ui.Fragments.ListFragmentFactory
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify


@RunWith(AndroidJUnit4::class)
class FragmentTests {


    @Test
    fun testInitialNavigation() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        val inspector = mock(Inspector::class.java)
        val factory = ListFragmentFactory (inspector)
        val scenario = launchFragmentInContainer {
            ListFragment(inspector).also {
                it.viewLifecycleOwnerLiveData.observeForever { viewLifecycleOwner ->
                    if (viewLifecycleOwner != null) {
                        // The fragment’s view has just been created
                        Navigation.setViewNavController(it.requireView(), navController)
                    }
                }
            }
        }

        assertThat(navController.currentDestination?.id).isEqualTo(R.id.listFragment)
    }


}