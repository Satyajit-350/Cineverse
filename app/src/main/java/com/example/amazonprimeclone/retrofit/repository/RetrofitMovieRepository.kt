package com.example.amazonprimeclone.retrofit.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonprimeclone.modal.*
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.Trailers.TrailerModel
import com.example.amazonprimeclone.retrofit.RetrofitApiServices
import com.example.amazonprimeclone.retrofit.utils.NetworkResult
import javax.inject.Inject

class RetrofitMovieRepository @Inject constructor(private val apiServices: RetrofitApiServices) {

    private val _movieDetailsLiveData = MutableLiveData<NetworkResult<MovieDetailsModel>>()
    val movieDetailsLiveData : LiveData<NetworkResult<MovieDetailsModel>> get() = _movieDetailsLiveData


    suspend fun getMoviesByQuery(api_key:String?,query: String?): SearchMovie? {
        val response = apiServices.getMoviesByQuery(api_key,query)
        Log.d("MovieResponse",response?.total_results.toString())
        return response
    }

    suspend fun getPersonByQuery(api_key:String?,query: String?): SearchPerson? {
        val response = apiServices.getPersonByQuery(api_key,query)
        Log.d("PersonResponse",response?.total_results.toString())
        return response;
    }

    suspend fun getPopularPerson(api_key:String?): SearchPerson? {
        val response = apiServices.getPopularPerson(api_key)
        Log.d("PopularPersonResponse",response?.total_results.toString())
        return response;
    }

    suspend fun getCategories(api_key:String?): MovieCategory? {
        val response = apiServices.getCategories(api_key)
        Log.d("Total Category",response?.genres?.size.toString())
        return response;
    }

    suspend fun getCategoriesResults(genre:String?): SearchMovie? {
        val response = apiServices.getCategoriesResults(genre)
        return response;
    }

    suspend fun getTopRated(): SearchMovie? {
        val response = apiServices.getTopRated()
        return response;
    }

    suspend fun getTopRatedSeries(): SearchSeries? {
        val response = apiServices.getTopRatedSeries()
        return response;
    }

    suspend fun getCategoriesSeries(api_key: String?): SeriesCategory? {
        val response = apiServices.getCategoriesSeries(api_key)
        return response;
    }

    suspend fun getCategoriesResultsSeries(genres: String?): SearchSeries? {
        val response = apiServices.getCategoriesResultsSeries(genres)
        return response;
    }

    suspend fun getPersonDetailsById(personId: String?,api_key: String?): PersonDetailsModel? {
        val response = apiServices.getPersonDetailsById(personId,api_key)
        return response;
    }

    suspend fun getMoviesKnownFor(id:String?,api_key: String?): SearchMovie? {
        val response = apiServices.getMoviesKnownFor(id,api_key)
        return response;
    }

    suspend fun getSeriesKnownFor(id:String?,api_key: String?): SeriesKnownForCredits? {
        val response = apiServices.getSeriesKnownFor(id,api_key)
        return response;
    }

    suspend fun getMovieDetails(id: String?,api_key: String?): MovieDetailsModel? {
        val response = apiServices.getMovieDetails(id,api_key)
        if(response!=null){
            _movieDetailsLiveData.postValue(NetworkResult.Success(response))
        }else{
            _movieDetailsLiveData.postValue(NetworkResult.Error("Something went wrong"))
        }
        return response;
    }

    suspend fun getCastDetails(id: String?,api_key: String?): MovieCredits? {
        val response = apiServices.getCastDetails(id,api_key)
        return response;
    }

    suspend fun getMovieTrailers(id: String?,api_key: String?): TrailerModel? {
        val response = apiServices.getMovieTrailers(id,api_key)
        return response;
    }

    suspend fun getMovieRecommendations(id: String?,api_key: String?):SearchMovie? {
        val response = apiServices.getMovieRecommendations(id,api_key)
        return response;
    }

    suspend fun getSeriesDetails(id: String?,api_key: String?): SeriesDetailsModel? {
        val response = apiServices.getSeriesDetails(id,api_key)
        return response;
    }

    suspend fun getSeriesRecommendations(id: String?,api_key: String?): SearchSeries? {
        val response = apiServices.getSeriesRecommendations(id,api_key)
        return response;
    }

    suspend fun getSeriesTrailers(id: String?,api_key: String?): TrailerModel? {
        val response = apiServices.getSeriesTrailers(id,api_key)
        return response;
    }

    suspend fun getSeriesCastDetails(id: String?,api_key: String?): SeriesCredits? {
        val response = apiServices.getSeriesCastDetails(id,api_key)
        return response;
    }

    
}