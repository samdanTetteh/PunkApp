package com.ijikod.punkapp.Data

import android.content.Context
import android.net.ConnectivityManager
import androidx.lifecycle.LiveData
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.ijikod.punkapp.App.PunkApplication.Companion.appContext
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.Model.BeerListResults
import com.ijikod.punkapp.PunkCache
import com.ijikod.punkapp.PunkService

class PunkRepository (private val service: PunkService, private val cache : PunkCache){

    lateinit var data: LiveData<PagedList<Beer>>
    lateinit var networkErrors: LiveData<String>


    fun listBeers(isRefreshing : Boolean) : BeerListResults{

        if (isRefreshing && isNetworkAvailable()){
            cache.deleteBeers()
        }

        val dataSourceFactory= cache.getBeers()

        val beersListBoundryCallBack = BeersListBoundryCallBack(service, cache)

        networkErrors = beersListBoundryCallBack.networkErrors

        data = LivePagedListBuilder(dataSourceFactory, DATABASE_PAGE_SIZE).setBoundaryCallback(beersListBoundryCallBack).build()

        return BeerListResults(data, networkErrors)
    }



    companion object {
        private const val DATABASE_PAGE_SIZE = 10
    }



    @Suppress("DEPRECATION")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }




}