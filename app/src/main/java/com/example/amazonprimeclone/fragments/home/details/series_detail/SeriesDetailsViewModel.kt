package com.example.amazonprimeclone.fragments.home.details.series_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.local.repository.MovieAndSeriesRepository
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCast
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import com.example.amazonprimeclone.modal.SeriesDetail.Season
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.SeriesResponseResults
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject
@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieAndSeriesRepository: MovieAndSeriesRepository,
    private val retrofitMovieRepository: RetrofitMovieRepository
) : ViewModel() {

    private val seriesDetailsList: MutableLiveData<SeriesDetailsModel?> = MutableLiveData()
    private val recommendationSeriesData: MutableLiveData<List<SeriesResponseResults>> = MutableLiveData()
    private val seriesTrailerResponse: MutableLiveData<List<TrailerResponse>> = MutableLiveData()
    private val seriesCastResponse: MutableLiveData<List<SeriesCast>> = MutableLiveData()
    private val seriesSeasonsResponse: MutableLiveData<List<Season>> = MutableLiveData()

    private var seriesID: String? = null
    private var favoriteMovie: FavoriteMovie? = null

    init {
        seriesID = savedStateHandle.get<String>("series_id")
        movieExistsOrNot()
        seriesData
    }

    val result= movieAndSeriesRepository.movieByIdExists
    private fun movieExistsOrNot() = viewModelScope.launch(Dispatchers.IO){
        movieAndSeriesRepository.getMovie(seriesID!!.toInt())
    }

    fun insertMovie() {
        movieAndSeriesRepository.insert(favoriteMovie!!)
    }

    fun deleteMovie() {
        movieAndSeriesRepository.delete(favoriteMovie!!)
    }

    fun getRecommendationSeriesData(): MutableLiveData<List<SeriesResponseResults>> {
        return recommendationSeriesData
    }

    fun getSeriesTrailerResponse(): MutableLiveData<List<TrailerResponse>> {
        return seriesTrailerResponse
    }

    fun getSeriesDetailsList(): MutableLiveData<SeriesDetailsModel?> {
        return seriesDetailsList
    }

    fun getSeriesCastResponse(): MutableLiveData<List<SeriesCast>> {
        return seriesCastResponse
    }
    fun getSeriesSeasons(): MutableLiveData<List<Season>> {
        return seriesSeasonsResponse
    }



    private val seriesData: Unit
        get() {
            loadSeriesDetails()
            loadSeriesCast()
            loadSeriesTrailers()
            loadSeriesRecommendations()
        }


    private fun loadSeriesRecommendations() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getSeriesRecommendations(seriesID,Constants.API_KEY)
        if(response!=null){
            recommendationSeriesData.postValue(response.results)
        }
    }

    private fun loadSeriesTrailers() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getSeriesTrailers(seriesID,Constants.API_KEY)
        if(response!=null){
            seriesTrailerResponse.postValue(response.results)
        }
    }

    private fun loadSeriesCast() = viewModelScope.launch(Dispatchers.IO) {
        val response = retrofitMovieRepository.getSeriesCastDetails(seriesID,Constants.API_KEY)
        if(response!=null){
            seriesCastResponse.postValue(response.cast)
        }
    }

    private fun loadSeriesDetails() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getSeriesDetails(seriesID,Constants.API_KEY)
        if(response!= null){
            seriesDetailsList.postValue(response)
            seriesSeasonsResponse.postValue(response.seasons)
            favoriteMovie = FavoriteMovie(
                response.id,
                response.vote_average.toFloat(),
                response.name,
                response.posterUrl,
                response.original_language,
                response.overview,
                response.first_air_date,
                false
            )
        }
    }

}
