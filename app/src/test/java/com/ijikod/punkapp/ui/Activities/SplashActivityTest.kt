package com.ijikod.punkapp.ui.Activities

import com.ijikod.punkapp.BuildConfig
import org.junit.Assert
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.ShadowLooper

@RunWith(RobolectricTestRunner::class)
@Config(sdk= intArrayOf(21))
class SplashActivityTest{



    @Test
    fun splashTimerTest(){
        val mainActivity = SplashActivity()

        assertFalse(mainActivity.isFinishing)

        mainActivity.scheduleSplashScreen(Robolectric.buildActivity(SplashActivity::class.java).create().start().resume().get())
        ShadowLooper.runUiThreadTasksIncludingDelayedTasks()

        assertTrue(mainActivity.isFinishing)
    }





}