package com.example.amazonprimeclone.retrofit.network.repository

import com.example.amazonprimeclone.modal.RecommendationData.RecommendationData
import com.example.amazonprimeclone.retrofit.network.RecommendationService
import javax.inject.Inject

class RecommendationRepository @Inject constructor(private val recommendationService: RecommendationService){

    suspend fun getRecommendedMovies(movieName: String): RecommendationData {
       return recommendationService.getRecommendation(movieName)
    }
}