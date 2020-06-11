package com.ijikod.punkapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.ijikod.punkapp.Data.PunkRepository
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.Model.BeerListResults

class BeersListViewModel(private val repository : PunkRepository) : ViewModel() {

    private val beerLiveData = MutableLiveData<BeerListResults>()

    var beers: LiveData<PagedList<Beer>>  = Transformations.switchMap(beerLiveData){
        repository.data
    }
    var networkErrors: LiveData<String> = Transformations.switchMap(beerLiveData){
        repository.networkErrors
    }


    fun getNewBeers(isRefreshing : Boolean){
        beerLiveData.value = repository.listBeers(isRefreshing)
    }


}