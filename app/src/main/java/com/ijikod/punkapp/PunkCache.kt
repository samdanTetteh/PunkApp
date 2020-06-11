package com.ijikod.punkapp

import android.util.Log
import androidx.paging.DataSource
import com.ijikod.punkapp.Model.Beer
import java.util.concurrent.Executor

class PunkCache (private val dao : PunkDao,  private val ioExecutor: Executor) {


    fun insert(beers : List<Beer>, insertFinished: () -> Unit){
        ioExecutor.execute {
            Log.d("PunkCache", "inserting ${beers.size} number of beers")
            dao.insert(beers)
            insertFinished()
        }

    }

    fun getBeers(): DataSource.Factory<Int, Beer> {
        return dao.getAllBeers()
    }


    fun deleteBeers(){
        ioExecutor.execute {
            dao.deleteBeers()
        }

    }



}