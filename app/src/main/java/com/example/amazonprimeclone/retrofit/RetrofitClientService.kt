package com.example.amazonprimeclone.retrofit

import com.example.amazonprimeclone.modal.*
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.Trailers.TrailerModel
import javax.inject.Inject

class RetrofitClientService  @Inject constructor(private val apiServices: RetrofitApiServices) {

    suspend fun getMoviesByQuery(api_key:String?,query: String?): SearchMovie? = apiServices.getMoviesByQuery(api_key,query)

    suspend fun getPersonByQuery(api_key:String?,query: String?): SearchPerson? = apiServices.getPersonByQuery(api_key,query)

    suspend fun getPopularPerson(api_key:String?): SearchPerson? = apiServices.getPopularPerson(api_key)

    suspend fun getCategories(api_key:String?): MovieCategory? = apiServices.getCategories(api_key)

    suspend fun getCategoriesResults(genre:String?): SearchMovie? = apiServices.getCategoriesResults(genre)

    suspend fun getTopRated(): SearchMovie? = apiServices.getTopRated()

    suspend fun getTopRatedSeries(): SearchSeries? = apiServices.getTopRatedSeries()

    suspend fun getCategoriesSeries(api_key: String?): SeriesCategory? = apiServices.getCategoriesSeries(api_key)

    suspend fun getCategoriesResultsSeries(genres: String?): SearchSeries? = apiServices.getCategoriesResultsSeries(genres)

    suspend fun getPersonDetailsById(personId: String?,api_key: String?): PersonDetailsModel? = apiServices.getPersonDetailsById(personId,api_key)

    suspend fun getMoviesKnownFor(id:String?,api_key: String?): SearchMovie? = apiServices.getMoviesKnownFor(id,api_key)

    suspend fun getSeriesKnownFor(id:String?,api_key: String?): SeriesKnownForCredits? = apiServices.getSeriesKnownFor(id,api_key)

    suspend fun getMovieDetails(id: String?,api_key: String?): MovieDetailsModel? = apiServices.getMovieDetails(id,api_key)

    suspend fun getCastDetails(id: String?,api_key: String?): MovieCredits? = apiServices.getCastDetails(id,api_key)

    suspend fun getMovieTrailers(id: String?,api_key: String?):TrailerModel? = apiServices.getMovieTrailers(id,api_key)

    suspend fun getMovieRecommendations(id: String?,api_key: String?):SearchMovie? = apiServices.getMovieRecommendations(id,api_key)

    suspend fun getSeriesDetails(id: String?,api_key: String?): SeriesDetailsModel? = apiServices.getSeriesDetails(id,api_key)

    suspend fun getSeriesRecommendations(id: String?,api_key: String?):SearchSeries? = apiServices.getSeriesRecommendations(id,api_key)

    suspend fun getSeriesTrailers(id: String?,api_key: String?): TrailerModel? = apiServices.getSeriesTrailers(id,api_key)

    suspend fun getSeriesCastDetails(id: String?,api_key: String?): SeriesCredits? = apiServices.getSeriesCastDetails(id,api_key)
}