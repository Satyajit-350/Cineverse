package com.example.amazonprimeclone.data.local.repository

import com.example.amazonprimeclone.data.local.MovieDatabase
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies
import javax.inject.Inject

class MovieRecommendationRepository @Inject constructor(movieDatabase: MovieDatabase) {

    private val recommendationDao = movieDatabase.recommendationDao()

    suspend fun insert(recommendedMovies: RecommendedMovies){
        deleteDataFromDB()
        recommendationDao.insert(recommendedMovies)
    }

    fun deleteDataFromDB() {
        val count = recommendationDao.getCount()
        if (count > 10) {
            recommendationDao.deleteOldestRow()
            val count = recommendationDao.getCount()
        }
    }

    suspend fun delete(recommendedMovies: RecommendedMovies) = recommendationDao.delete(recommendedMovies)

    fun getAllRecommendedMovies() = recommendationDao.getAllRecommendedMovies()

}