package com.ijikod.punkapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.ijikod.punkapp.Data.PunkRepository

class ViewModelFactory(private val repository: PunkRepository) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(BeersListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return BeersListViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}