package com.example.amazonprimeclone.fragments.home.mylist

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData
import com.example.amazonprimeclone.data.local.repository.MovieAndSeriesRepository
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MyListViewModel @Inject constructor(private val movieAndSeriesRepository: MovieAndSeriesRepository): ViewModel() {

    val getMovieData: LiveData<List<MovieAndSeriesData>> = movieAndSeriesRepository.getAllData()

}