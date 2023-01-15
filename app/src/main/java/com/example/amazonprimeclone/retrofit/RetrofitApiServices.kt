package com.example.amazonprimeclone.retrofit

import com.example.amazonprimeclone.modal.*
import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel
import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel
import com.example.amazonprimeclone.modal.Trailers.TrailerModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitApiServices {

    @GET("search/movie")
    suspend fun getMoviesByQuery(@Query("api_key")api_key:String?, @Query("query")query:String?):SearchMovie?

    @GET("search/person")
    suspend fun getPersonByQuery(
        @Query("api_key") api_key: String?,
        @Query("query") query: String?
    ): SearchPerson?

    @GET("person/popular")
    suspend fun getPopularPerson(@Query("api_key") api_key: String?): SearchPerson?

    //get the movies categories list
    @GET("genre/movie/list")
    suspend fun getCategories(@Query("api_key") api_key: String?): MovieCategory?

    @GET("https://api.themoviedb.org/3/discover/movie?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    suspend fun getCategoriesResults(@Query("with_genres") genres: String?): SearchMovie?

    @GET("movie/popular?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    suspend fun getTopRated(): SearchMovie?

    @GET("tv/popular?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    suspend fun getTopRatedSeries(): SearchSeries?

    //get the series categories list
    @GET("genre/tv/list")
    suspend fun getCategoriesSeries(@Query("api_key") api_key: String?): SeriesCategory?

    @GET("https://api.themoviedb.org/3/discover/tv?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    suspend fun getCategoriesResultsSeries(@Query("with_genres") genres: String?): SearchSeries?

    //get person details by id
    @GET("person/{person_id}")
    suspend fun getPersonDetailsById(
        @Path("person_id") personId: String?,
        @Query("api_key") apiKey: String?
    ): PersonDetailsModel?

    @GET("discover/movie")
    suspend fun getMoviesKnownFor(
        @Query("with_cast") id: String?,
        @Query("api_key") apiKey: String?
    ): SearchMovie?

    @GET("person/{person_id}/tv_credits")
    suspend fun getSeriesKnownFor(
        @Path("person_id") id: String?,
        @Query("api_key") apiKey: String?
    ): SeriesKnownForCredits?

    //get movie details by id
    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") movie_id: String?,
        @Query("api_key") apiKey: String?
    ): MovieDetailsModel?

    //get movies cast ans crew details
    @GET("movie/{movie_id}/credits")
    suspend fun getCastDetails(
        @Path("movie_id") movie_id: String?,
        @Query("api_key") apiKey: String?
    ): MovieCredits?

    //get movie trailers
    @GET("movie/{movie_id}/videos")
    suspend fun getMovieTrailers(
        @Path("movie_id") movieId: String?,
        @Query("api_key") apiKey: String?
    ): TrailerModel?

    //get recommendation according to movie id
    @GET("movie/{movie_id}/recommendations")
    suspend fun getMovieRecommendations(
        @Path("movie_id") movieId: String?,
        @Query("api_key") apiKey: String?
    ): SearchMovie?

    //get series details by id
    @GET("tv/{tv_id}")
    suspend fun getSeriesDetails(
        @Path("tv_id") series_id: String?,
        @Query("api_key") apiKey: String?
    ): SeriesDetailsModel?

    //get recommendation according to series id
    @GET("tv/{tv_id}/recommendations")
    suspend fun getSeriesRecommendations(
        @Path("tv_id") series_id: String?,
        @Query("api_key") apiKey: String?
    ): SearchSeries?

    //get series trailers
    @GET("tv/{tv_id}/videos")
    suspend fun getSeriesTrailers(
        @Path("tv_id") series_id: String?,
        @Query("api_key") apiKey: String?
    ): TrailerModel?

    //get series cast ans crew details
    @GET("tv/{tv_id}/credits")
    suspend fun getSeriesCastDetails(
        @Path("tv_id") series_id: String?,
        @Query("api_key") apiKey: String?
    ): SeriesCredits?

}