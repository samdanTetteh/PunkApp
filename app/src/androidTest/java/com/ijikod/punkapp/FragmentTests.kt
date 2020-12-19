package com.ijikod.punkapp

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.common.truth.Truth.assertThat
import com.ijikod.punkapp.ui.Activities.Inspector
import com.ijikod.punkapp.ui.Fragments.ListFragment
import com.ijikod.punkapp.ui.Fragments.FragmentFactory
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.mock


@RunWith(AndroidJUnit4::class)
class FragmentTests {


    @Test
    fun testInitialNavigation() {
        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext())
        navController.setGraph(R.navigation.nav_graph)
        val inspector = mock(Inspector::class.java)
        val factory = FragmentFactory (inspector)
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