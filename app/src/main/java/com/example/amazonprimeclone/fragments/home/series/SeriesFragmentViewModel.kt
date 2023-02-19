package com.example.amazonprimeclone.fragments.home.series

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.SeriesCategoryResults
import com.example.amazonprimeclone.modal.SeriesResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeriesFragmentViewModel @Inject constructor(
    private val retrofitMovieRepository: RetrofitMovieRepository
): ViewModel() {

    private val slideList: MutableLiveData<List<SeriesResponseResults>> = MutableLiveData()
    private val resultsList: MutableLiveData<List<SeriesCategoryResults>> = MutableLiveData()

    init {
        getData()
    }

    fun getResultsList(): MutableLiveData<List<SeriesCategoryResults>> {
        return resultsList
    }

    fun getSlideList(): MutableLiveData<List<SeriesResponseResults>> {
        return slideList
    }

    private fun getData() {
        loadSlideList()
        loadResultList()
    }

    private fun loadSlideList() = viewModelScope.launch{
        val response = retrofitMovieRepository.getTopRatedSeries()
        if(response!=null){
            slideList.postValue(response.results.subList(0,9))
        }
    }

    private fun loadResultList() = viewModelScope.launch{
        val response = retrofitMovieRepository.getCategoriesSeries(Constants.API_KEY)
        if(response!=null){
            resultsList.postValue(response.genres)
        }
    }

}