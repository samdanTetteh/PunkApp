package com.ijikod.punkapp

import androidx.room.Room
import androidx.room.paging.LimitOffsetDataSource
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.google.gson.Gson
import com.ijikod.punkapp.Model.Beer
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class DataBaseTests {

    private lateinit var beerDatabase: PunkDataBase


    @Before
    fun initDb() {
        beerDatabase = Room.inMemoryDatabaseBuilder(
            InstrumentationRegistry.getInstrumentation().context,
            PunkDataBase::class.java).build()
    }



    @Test
    fun insertBeersSavesData() {
        val cachedBeers = Gson().fromJson(BeerTestFactory.bearItem, Array<Beer>::class.java).asList()
        beerDatabase.dao().insert(cachedBeers)

        val beers = (beerDatabase.dao().getAllBeers().create() as LimitOffsetDataSource<Beer>).loadRange(0,1)
        assert(beers.isNotEmpty())
    }



    @Test
    fun deleteBeersClearData() {
        val cachedBeers = Gson().fromJson(BeerTestFactory.bearItem, Array<Beer>::class.java).asList()
        beerDatabase.dao().insert(cachedBeers)

        beerDatabase.dao().deleteBeers()
        val beers = (beerDatabase.dao().getAllBeers().create() as LimitOffsetDataSource<Beer>).loadRange(0,1)
        assert(beers.isEmpty())
    }




}