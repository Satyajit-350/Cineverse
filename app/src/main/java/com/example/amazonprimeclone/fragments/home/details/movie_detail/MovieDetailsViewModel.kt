package com.example.amazonprimeclone.fragments.home.details.movie_detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies
import com.example.amazonprimeclone.data.local.repository.MovieAndSeriesRepository
import com.example.amazonprimeclone.data.local.repository.MovieRecommendationRepository
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCast
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.modal.Trailers.TrailerResponse
import com.example.amazonprimeclone.retrofit.network.repository.RecommendationRepository
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieAndSeriesRepository: MovieAndSeriesRepository,
    private val recommendationRepository: RecommendationRepository,
    private val retrofitMovieRepository: RetrofitMovieRepository,
    private val movieRecommendationRepository: MovieRecommendationRepository
) : ViewModel() {

    val recommendationMovieData: MutableLiveData<List<MovieResponseResults>> = MutableLiveData()
    val movieTrailerResponse: MutableLiveData<List<TrailerResponse>> = MutableLiveData()
    val movieCastResponse: MutableLiveData<List<MovieCast>> = MutableLiveData()
    val movieDetails: MutableLiveData<MovieDetailsModel?> = MutableLiveData()
    private val movieID: String?
    private var favoriteMovie: FavoriteMovie? = null

    init {
        movieID = savedStateHandle.get<String>("movie_id")
        movieExistsOrNot()
        movieData
    }

    val result = movieAndSeriesRepository.movieByIdExists

    private fun movieExistsOrNot() = viewModelScope.launch(Dispatchers.IO){
        movieAndSeriesRepository.getMovie(movieID!!.toInt())
    }

    fun insertMovie(movieName: String) = viewModelScope.launch(Dispatchers.IO) {
        movieAndSeriesRepository.insert(favoriteMovie!!)
        //for recommendations
        try {
            val response = recommendationRepository.getRecommendedMovies(movieName)
            for (movies in response) {
                //insert into the database
                movieRecommendationRepository.insert(RecommendedMovies(movies.movieId,movies.movieName,movies.poster))
            }

        } catch (e: Exception) {
            e.printStackTrace()
            Log.d("response",e.message.toString())
        }

    }
    fun deleteMovie() {
        movieAndSeriesRepository.delete(favoriteMovie!!)
    }

    private val movieData: Unit
        get() {
            loadMovieDetails()
            loadMovieCast()
            loadMovieTrailers()
            loadMovieRecommendations()
        }

    private fun loadMovieDetails() = viewModelScope.launch(Dispatchers.IO){

        val response = retrofitMovieRepository.getMovieDetails(movieID,Constants.API_KEY)
        if(response!=null){

            movieDetails.postValue(response)
            favoriteMovie = FavoriteMovie(
                response.id,
                response.vote_average,
                response.title,
                response.poster_path,
                response.original_language,
                response.overview,
                response.release_date,
            true)
        }
    }

    private fun loadMovieCast() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getCastDetails(movieID,Constants.API_KEY)
        if(response!=null){
            movieCastResponse.postValue(response.cast)
        }
    }

    private fun loadMovieTrailers() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getMovieTrailers(movieID,Constants.API_KEY)
        if(response!=null){
            movieTrailerResponse.postValue(response.results)
        }
    }

    private fun loadMovieRecommendations() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getMovieRecommendations(movieID,Constants.API_KEY)
        if(response!=null){
            recommendationMovieData.postValue(response.results)
        }
    }
}
