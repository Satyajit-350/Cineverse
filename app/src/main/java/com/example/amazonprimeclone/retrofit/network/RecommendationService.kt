package com.example.amazonprimeclone.retrofit.network

import com.example.amazonprimeclone.modal.RecommendationData.RecommendationData
import javax.inject.Inject

class RecommendationService @Inject constructor(private val recommendationApiService: RecommendationApiService){

    suspend fun getRecommendation(movieName: String):RecommendationData = recommendationApiService.getRecommendation(movieName)

}