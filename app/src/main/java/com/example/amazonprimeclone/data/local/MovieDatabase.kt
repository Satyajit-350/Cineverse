package com.example.amazonprimeclone.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.amazonprimeclone.data.local.dao.MovieAndSeriesDao
import com.example.amazonprimeclone.data.local.dao.RecommendationDao
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies

@Database(entities = [MovieAndSeriesData::class,RecommendedMovies::class], version = 1, exportSchema = false)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieAndSeriesDao():MovieAndSeriesDao
    abstract fun recommendationDao():RecommendationDao
}