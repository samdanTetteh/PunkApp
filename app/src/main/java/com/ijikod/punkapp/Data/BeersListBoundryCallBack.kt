package com.ijikod.punkapp.Data

import android.content.Context
import android.net.ConnectivityManager
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PagedList
import com.ijikod.punkapp.*
import com.ijikod.punkapp.App.PunkApplication
import com.ijikod.punkapp.App.PunkApplication.Companion.appContext
import com.ijikod.punkapp.Model.Beer
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class BeersListBoundryCallBack (private val service: PunkService, private val cache: PunkCache) : PagedList.BoundaryCallback<Beer>(){


    private var pageRequested = 1

    private var isRequestInProgress = false

    private val _networkErrors  = MutableLiveData<String>()

    val networkErrors : LiveData<String>
        get() = _networkErrors


    override fun onZeroItemsLoaded() {
            askPunkForBeersAndSave()
    }


    override fun onItemAtEndLoaded(itemAtEnd: Beer) {
            askPunkForBeersAndSave()
    }



     private  fun askPunkForBeersAndSave(){
            if (isNetworkAvailable()){
                if (isRequestInProgress) return
                isRequestInProgress = true
                getBeersFromNetwork(service, pageRequested, SIZE_PER_PAGE,
                    {beers ->
                        cache.insert(beers){
                            pageRequested++
                            isRequestInProgress = false
                        }

                    },
                    {error ->
                        _networkErrors.postValue(error)
                        isRequestInProgress = false
                    })

            }else{
                _networkErrors.postValue(appContext.getString(R.string.no_network_text))
                isRequestInProgress = false

            }
    }



    @Suppress("DEPRECATION")
    private fun isNetworkAvailable() : Boolean{
        val connectivityManager = appContext.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connectivityManager.activeNetworkInfo
        return networkInfo?.isConnectedOrConnecting ?: false
    }



    companion object{
        const val SIZE_PER_PAGE = 10
    }

}