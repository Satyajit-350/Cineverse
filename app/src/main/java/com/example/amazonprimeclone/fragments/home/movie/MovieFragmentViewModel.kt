package com.example.amazonprimeclone.fragments.home.movie

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies
import com.example.amazonprimeclone.data.local.repository.MovieRecommendationRepository
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.MovieCategoryResults
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class MovieFragmentViewModel @Inject constructor(
    private val retrofitMovieRepository: RetrofitMovieRepository,
    recommendationRepository:MovieRecommendationRepository
):ViewModel() {

    private val slideList: MutableLiveData<List<MovieResponseResults>> = MutableLiveData()
    private val resultsList: MutableLiveData<List<MovieCategoryResults>> = MutableLiveData()
    var recommendationList: LiveData<List<RecommendedMovies>> = MutableLiveData()

    init {
        recommendationList = recommendationRepository.getAllRecommendedMovies()
        loadData()
    }

    fun getResultsList(): LiveData<List<MovieCategoryResults>> {
        return resultsList
    }

    fun getSlideList(): LiveData<List<MovieResponseResults>> {
        return slideList
    }

    private fun loadData() {
        getSlideImg()
        getCategories()
    }

    private fun getSlideImg() = viewModelScope.launch{
        val response = retrofitMovieRepository.getTopRated()
        if(response!=null){
            slideList.postValue(response.results.subList(4,13))
        }
    }

    private fun getCategories() = viewModelScope.launch{
        val response = retrofitMovieRepository.getCategories(Constants.API_KEY)
        if(response!=null){
            resultsList.postValue(response.genres)
        }
    }

}