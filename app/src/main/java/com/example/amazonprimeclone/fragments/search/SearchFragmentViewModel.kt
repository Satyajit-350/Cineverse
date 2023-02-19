package com.example.amazonprimeclone.fragments.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchFragmentViewModel  @Inject constructor(private val retrofitMovieRepository: RetrofitMovieRepository): ViewModel() {

    private val movieResults: MutableLiveData<List<MovieResponseResults>> = MutableLiveData()

    fun getMovieResults(query: String): LiveData<List<MovieResponseResults>> {
        loadMovieResults(query)
        return movieResults
    }

    private fun loadMovieResults(query: String) = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getMoviesByQuery(Constants.API_KEY,query)
        if(response!=null){
            movieResults.postValue(response.results)
        }
    }
}