package com.ijikod.punkapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.paging.PagedList
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.ui.BeersListViewModel
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.*


@RunWith(AndroidJUnit4::class)
class ViewModelTests {


    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Mock
    lateinit var vm : BeersListViewModel


    private  val observer: Observer<PagedList<Beer>> = MokitoUtils().mock()


    @Before
    fun setUp(){
        vm = BeersListViewModel(PunkApplication.providePunkRepository(ApplicationProvider.getApplicationContext()))

    }


    @Test
    fun testModelStateChange(){
        vm.beers.observeForever(observer)
        vm.getNewBeers(true)

        verify(observer).onChanged(vm.beers.value)
    }


    @Test
    fun testForReturnValues(){
        vm.beers.observeForever(observer)
        vm.getNewBeers(true)

        vm.beers.value?.isNotEmpty()?.let { assert(it) }
    }



}

