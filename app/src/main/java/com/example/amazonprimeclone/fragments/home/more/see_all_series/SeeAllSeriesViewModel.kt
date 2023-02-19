package com.example.amazonprimeclone.fragments.home.more.see_all_series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.modal.SeriesResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeeAllSeriesViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val retrofitMovieRepository: RetrofitMovieRepository
): ViewModel(){

    private val allSeriesList: MutableLiveData<List<SeriesResponseResults>> = MutableLiveData()
    private var id: String? = null
    var title: String? = null

    init {
        id = savedStateHandle.get<String>("id")
        title = savedStateHandle.get<String>("title")
        getAllSeries()
    }

    fun getAllSeriesList(): MutableLiveData<List<SeriesResponseResults>> {
        return allSeriesList
    }

    private fun getAllSeries() = viewModelScope.launch{
        val response = retrofitMovieRepository.getCategoriesResultsSeries(id)
        if(response!=null){
            allSeriesList.postValue(response.results)
        }
    }

}