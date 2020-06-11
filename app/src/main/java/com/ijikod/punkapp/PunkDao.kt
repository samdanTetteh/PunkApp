package com.ijikod.punkapp

import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ijikod.punkapp.Model.Beer

@Dao
interface PunkDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(beers : List<Beer>)

    @Query("Select * from Beers ORDER BY name ASC")
    fun  getAllBeers(): DataSource.Factory<Int, Beer>

    @Query("Delete from beers")
    fun deleteBeers()
}