package com.example.amazonprimeclone.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData

@Dao
interface MovieAndSeriesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movieAndSeriesData: MovieAndSeriesData)

    @Delete
    fun delete(movieAndSeriesData: MovieAndSeriesData)

    @Query("SELECT * FROM data_table")
    fun getAllData():LiveData<List<MovieAndSeriesData>>

    @Query("SELECT * FROM data_table WHERE id = :id")
    fun getMovie(id: Int): MovieAndSeriesData?


}