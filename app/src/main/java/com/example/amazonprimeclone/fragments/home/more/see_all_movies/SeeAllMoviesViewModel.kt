package com.example.amazonprimeclone.fragments.home.more.see_all_movies

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SeeAllMoviesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val retrofitMovieRepository: RetrofitMovieRepository
):ViewModel() {

    private val allMoviesList: MutableLiveData<List<MovieResponseResults>> = MutableLiveData()
    private var id: String? = null
    var title: String? = null

    init {
        id = savedStateHandle.get<String>("id")
        title = savedStateHandle.get<String>("title")
        getAllMovies()
    }

    fun getAllMoviesList(): MutableLiveData<List<MovieResponseResults>> {
        return allMoviesList
    }

    private fun getAllMovies() = viewModelScope.launch{
        val movieResponseCall = retrofitMovieRepository.getCategoriesResults(id)
        if(movieResponseCall!=null){
            allMoviesList.postValue(movieResponseCall.results)
        }
    }

}