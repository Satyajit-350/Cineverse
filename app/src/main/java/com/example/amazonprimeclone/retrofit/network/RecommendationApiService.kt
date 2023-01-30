package com.example.amazonprimeclone.retrofit.network

import com.example.amazonprimeclone.modal.RecommendationData.RecommendationData
import retrofit2.http.*

interface RecommendationApiService {

    @FormUrlEncoded
    @POST("predict")
    suspend fun getRecommendation(@Field("movieName") movieName: String): RecommendationData

}