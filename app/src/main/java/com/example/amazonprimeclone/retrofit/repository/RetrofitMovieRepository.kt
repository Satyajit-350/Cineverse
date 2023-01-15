package com.example.amazonprimeclone.retrofit.repository

import android.util.Log
import com.example.amazonprimeclone.modal.*
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.Trailers.TrailerModel
import com.example.amazonprimeclone.retrofit.RetrofitClientService
import javax.inject.Inject

class RetrofitMovieRepository @Inject constructor(private val retrofitClientService: RetrofitClientService) {

    suspend fun getMoviesByQuery(api_key:String?,query: String?): SearchMovie? {
        val response = retrofitClientService.getMoviesByQuery(api_key,query)
        Log.d("MovieResponse",response?.total_results.toString())
        return response
    }

    suspend fun getPersonByQuery(api_key:String?,query: String?): SearchPerson? {
        val response = retrofitClientService.getPersonByQuery(api_key,query)
        Log.d("PersonResponse",response?.total_results.toString())
        return response;
    }

    suspend fun getPopularPerson(api_key:String?): SearchPerson? {
        val response = retrofitClientService.getPopularPerson(api_key)
        Log.d("PopularPersonResponse",response?.total_results.toString())
        return response;
    }

    suspend fun getCategories(api_key:String?): MovieCategory? {
        val response = retrofitClientService.getCategories(api_key)
        Log.d("Total Category",response?.genres?.size.toString())
        return response;
    }

    suspend fun getCategoriesResults(genre:String?): SearchMovie? {
        val response = retrofitClientService.getCategoriesResults(genre)
        return response;
    }

    suspend fun getTopRated(): SearchMovie? {
        val response = retrofitClientService.getTopRated()
        return response;
    }

    suspend fun getTopRatedSeries(): SearchSeries? {
        val response = retrofitClientService.getTopRatedSeries()
        return response;
    }

    suspend fun getCategoriesSeries(api_key: String?): SeriesCategory? {
        val response = retrofitClientService.getCategoriesSeries(api_key)
        return response;
    }

    suspend fun getCategoriesResultsSeries(genres: String?): SearchSeries? {
        val response = retrofitClientService.getCategoriesResultsSeries(genres)
        return response;
    }

    suspend fun getPersonDetailsById(personId: String?,api_key: String?): PersonDetailsModel? {
        val response = retrofitClientService.getPersonDetailsById(personId,api_key)
        return response;
    }

    suspend fun getMoviesKnownFor(id:String?,api_key: String?): SearchMovie? {
        val response = retrofitClientService.getMoviesKnownFor(id,api_key)
        return response;
    }

    suspend fun getSeriesKnownFor(id:String?,api_key: String?): SeriesKnownForCredits? {
        val response = retrofitClientService.getSeriesKnownFor(id,api_key)
        return response;
    }

    suspend fun getMovieDetails(id: String?,api_key: String?): MovieDetailsModel? {
        val response = retrofitClientService.getMovieDetails(id,api_key)
        return response;
    }

    suspend fun getCastDetails(id: String?,api_key: String?): MovieCredits? {
        val response = retrofitClientService.getCastDetails(id,api_key)
        return response;
    }

    suspend fun getMovieTrailers(id: String?,api_key: String?): TrailerModel? {
        val response = retrofitClientService.getMovieTrailers(id,api_key)
        return response;
    }

    suspend fun getMovieRecommendations(id: String?,api_key: String?):SearchMovie? {
        val response = retrofitClientService.getMovieRecommendations(id,api_key)
        return response;
    }

    suspend fun getSeriesDetails(id: String?,api_key: String?): SeriesDetailsModel? {
        val response = retrofitClientService.getSeriesDetails(id,api_key)
        return response;
    }

    suspend fun getSeriesRecommendations(id: String?,api_key: String?): SearchSeries? {
        val response = retrofitClientService.getSeriesRecommendations(id,api_key)
        return response;
    }

    suspend fun getSeriesTrailers(id: String?,api_key: String?): TrailerModel? {
        val response = retrofitClientService.getSeriesTrailers(id,api_key)
        return response;
    }

    suspend fun getSeriesCastDetails(id: String?,api_key: String?): SeriesCredits? {
        val response = retrofitClientService.getSeriesCastDetails(id,api_key)
        return response;
    }
    
}