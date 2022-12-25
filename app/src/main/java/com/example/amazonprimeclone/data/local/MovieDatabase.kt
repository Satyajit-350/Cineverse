package com.example.amazonprimeclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.amazonprimeclone.data.local.dao.MovieAndSeriesDao
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData

@Database(entities = [MovieAndSeriesData::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {

    abstract fun movieAndSeriesDao():MovieAndSeriesDao

}