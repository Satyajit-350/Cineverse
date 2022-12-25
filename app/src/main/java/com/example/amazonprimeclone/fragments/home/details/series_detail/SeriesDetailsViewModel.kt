package com.example.amazonprimeclone.fragments.home.details.series_detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.local.repository.MovieAndSeriesRepository
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCast
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import com.example.amazonprimeclone.modal.SearchSeries
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.SeriesResponseResults
import com.example.amazonprimeclone.modal.Trailers.TrailerModel
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject
@HiltViewModel
class SeriesDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieAndSeriesRepository: MovieAndSeriesRepository
) : ViewModel() {

    private val seriesDetailsList: MutableLiveData<SeriesDetailsModel?> = MutableLiveData()
    private val recommendationSeriesData: MutableLiveData<List<SeriesResponseResults>> = MutableLiveData()
    private val seriesTrailerResponse: MutableLiveData<List<TrailerResponse>> = MutableLiveData()
    private val seriesCastResponse: MutableLiveData<List<SeriesCast>> = MutableLiveData()

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

    private val seriesData: Unit
        get() {
            loadSeriesDetails()
            loadSeriesCast()
            loadSeriesTrailers()
            loadSeriesRecommendations()
        }

    private fun loadSeriesRecommendations() {
        val searchMovieCall =
            Constants.retrofitService.getSeriesRecommendations(seriesID, Constants.API_KEY)
        searchMovieCall.enqueue(object : Callback<SearchSeries?> {
            override fun onResponse(call: Call<SearchSeries?>, response: Response<SearchSeries?>) {
                if (response.isSuccessful) {
                    val searchSeries = response.body()
                    if (searchSeries != null) {
                        recommendationSeriesData.value = searchSeries.results
                    }
                }
            }

            override fun onFailure(call: Call<SearchSeries?>, t: Throwable) {}
        })
    }

    private fun loadSeriesTrailers() {
        val movieTrailerCall =
            Constants.retrofitService.getSeriesTrailers(seriesID, Constants.API_KEY)
        movieTrailerCall.enqueue(object : Callback<TrailerModel?> {
            override fun onResponse(call: Call<TrailerModel?>, response: Response<TrailerModel?>) {
                if (response.isSuccessful) {
                    val movieTrailer = response.body()
                    if (movieTrailer != null) {
                        seriesTrailerResponse.value = movieTrailer.results
                    }
                }
            }

            override fun onFailure(call: Call<TrailerModel?>, t: Throwable) {}
        })
    }

    private fun loadSeriesCast() {
        val movieCastCall =
            Constants.retrofitService.getSeriesCastDetails(seriesID, Constants.API_KEY)
        movieCastCall.enqueue(object : Callback<SeriesCredits?> {
            override fun onResponse(
                call: Call<SeriesCredits?>,
                response: Response<SeriesCredits?>
            ) {
                if (response.isSuccessful) {
                    val seriesCredits = response.body()
                    if (seriesCredits != null) {
                        seriesCastResponse.value = seriesCredits.cast
                    }
                }
            }

            override fun onFailure(call: Call<SeriesCredits?>, t: Throwable) {}
        })
    }

    private fun loadSeriesDetails() {
        val movieDetailsModelCall =
            Constants.retrofitService.getSeriesDetails(seriesID, Constants.API_KEY)
        movieDetailsModelCall.enqueue(object : Callback<SeriesDetailsModel?> {
            override fun onResponse(
                call: Call<SeriesDetailsModel?>,
                response: Response<SeriesDetailsModel?>
            ) {
                if (response.isSuccessful) {
                    seriesDetailsList.value = response.body()
                    if (response.body() != null) {
                        favoriteMovie = FavoriteMovie(
                            response.body()!!.id,
                            response.body()!!.vote_average.toFloat(),
                            response.body()!!
                                .name,
                            response.body()!!.posterUrl,
                            response.body()!!.original_language,
                            response.body()!!.overview,
                            response.body()!!
                                .first_air_date
                        )
                    }
                }
            }

            override fun onFailure(call: Call<SeriesDetailsModel?>, t: Throwable) {}
        })
    }

}