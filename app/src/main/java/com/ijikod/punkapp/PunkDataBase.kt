package com.ijikod.punkapp

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.ijikod.punkapp.Model.Beer
import com.ijikod.punkapp.Model.RequestConverters

@Database(entities = [Beer::class], version = 1, exportSchema = false)
@TypeConverters(RequestConverters::class)
abstract class PunkDataBase : RoomDatabase(){

    abstract fun dao() : PunkDao

    companion object{
        @Volatile
        private var INSTANCE: PunkDataBase? = null

        fun getInstance(context: Context): PunkDataBase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(context.applicationContext,
                PunkDataBase::class.java, "Punk.db")
                .build()
    }
}