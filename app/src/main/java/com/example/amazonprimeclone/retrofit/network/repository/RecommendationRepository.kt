package com.example.amazonprimeclone.retrofit.network.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonprimeclone.modal.RecommendationData.RecommendationData
import com.example.amazonprimeclone.retrofit.network.RecommendationApiService
import com.example.amazonprimeclone.retrofit.utils.NetworkResult
import javax.inject.Inject

class RecommendationRepository @Inject constructor(private val recommendationApiService: RecommendationApiService){

    suspend fun getRecommendedMovies(movieName: String): RecommendationData{
        val response = recommendationApiService.getRecommendation(movieName)
        Log.d("Recommendation Response",response.size.toString())
        return response
    }
}