package com.ijikod.punkapp.App

import android.app.Application
import android.content.Context
import androidx.lifecycle.ViewModelProvider
import com.ijikod.punkapp.Data.PunkRepository
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.PunkCache
import com.ijikod.punkapp.PunkDataBase
import com.ijikod.punkapp.PunkService
import com.ijikod.punkapp.ui.ViewModelFactory
import java.util.concurrent.Executors

class PunkApplication : Application() {


    override fun onCreate() {
        super.onCreate()
        PunkApplication.appContext = applicationContext
    }


    companion object{
        lateinit var appContext : Context


          fun providePunkRepository(context: Context): PunkRepository {
            return PunkRepository(PunkService.create(), provideCache(context))

        }


        private fun provideCache(context: Context): PunkCache {
            val database = PunkDataBase.getInstance(context)
            return PunkCache(database.dao(), Executors.newSingleThreadExecutor())
        }


        fun provideViewModelFactory(context: Context): ViewModelProvider.Factory {
            return ViewModelFactory(
                providePunkRepository(
                    context
                )
            )
        }

    }
}