package com.example.amazonprimeclone.fragments.home.details.movie_detail

import androidx.lifecycle.*
import com.example.amazonprimeclone.data.local.repository.MovieAndSeriesRepository
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCast
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.modal.SearchMovie
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
class MovieDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val movieAndSeriesRepository: MovieAndSeriesRepository
) :
    ViewModel() {
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

    val result= movieAndSeriesRepository.movieByIdExists
    private fun movieExistsOrNot() = viewModelScope.launch(Dispatchers.IO){
        movieAndSeriesRepository.getMovie(movieID!!.toInt())
    }

    fun insertMovie() {
        movieAndSeriesRepository.insert(favoriteMovie!!)
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

    private fun loadMovieDetails() {
        val movieDetailsModelCall =
            Constants.retrofitService.getMovieDetails(movieID, Constants.API_KEY)
        movieDetailsModelCall.enqueue(object : Callback<MovieDetailsModel?> {
            override fun onResponse(
                call: Call<MovieDetailsModel?>,
                response: Response<MovieDetailsModel?>
            ) {
                if (response.isSuccessful) {
                    movieDetails.value = response.body()
                    if (response.body() != null) {
                        favoriteMovie = FavoriteMovie(
                            response.body()!!.id,
                            response.body()!!.vote_average,
                            response.body()!!
                                .title,
                            response.body()!!.poster_path,
                            response.body()!!.original_language,
                            response.body()!!.overview,
                            response.body()!!
                                .release_date
                        )
                    }
                }
            }

            override fun onFailure(call: Call<MovieDetailsModel?>, t: Throwable) {}
        })
    }

    private fun loadMovieCast() {
        val movieCastCall = Constants.retrofitService.getCastDetails(movieID, Constants.API_KEY)
        movieCastCall.enqueue(object : Callback<MovieCredits?> {
            override fun onResponse(call: Call<MovieCredits?>, response: Response<MovieCredits?>) {
                if (response.isSuccessful) {
                    val movieCredits = response.body()
                    if (movieCredits != null) {
                        movieCastResponse.value = movieCredits.cast
                    }
                }
            }

            override fun onFailure(call: Call<MovieCredits?>, t: Throwable) {}
        })
    }

    private fun loadMovieTrailers() {
        val movieTrailerCall =
            Constants.retrofitService.getMovieTrailers(movieID, Constants.API_KEY)
        movieTrailerCall.enqueue(object : Callback<TrailerModel?> {
            override fun onResponse(call: Call<TrailerModel?>, response: Response<TrailerModel?>) {
                if (response.isSuccessful) {
                    val movieTrailer = response.body()
                    if (movieTrailer != null) {
                        movieTrailerResponse.value = movieTrailer.results
                    }
                }
            }

            override fun onFailure(call: Call<TrailerModel?>, t: Throwable) {}
        })
    }

    private fun loadMovieRecommendations() {
        val searchMovieCall =
            Constants.retrofitService.getMovieRecommendations(movieID, Constants.API_KEY)
        searchMovieCall.enqueue(object : Callback<SearchMovie?> {
            override fun onResponse(call: Call<SearchMovie?>, response: Response<SearchMovie?>) {
                if (response.isSuccessful) {
                    val searchMovie = response.body()
                    if (searchMovie != null) {
                        recommendationMovieData.value = searchMovie.results
                    }
                }
            }

            override fun onFailure(call: Call<SearchMovie?>, t: Throwable) {}
        })
    }
}
