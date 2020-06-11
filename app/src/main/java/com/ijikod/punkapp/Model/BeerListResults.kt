package com.ijikod.punkapp.Model

import androidx.lifecycle.LiveData
import androidx.paging.PagedList

data class BeerListResults(val data: LiveData<PagedList<Beer>>, val networkErrors : LiveData<String>)