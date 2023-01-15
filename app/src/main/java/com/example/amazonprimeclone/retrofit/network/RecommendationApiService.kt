package com.example.amazonprimeclone.retrofit.network

import com.example.amazonprimeclone.modal.RecommendationData.RecommendationData
import retrofit2.http.Body
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface RecommendationApiService {

    @Multipart
    @POST("predict")
    suspend fun getRecommendation(@Part("movieName") movieName: String): RecommendationData

}