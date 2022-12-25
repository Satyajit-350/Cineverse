package com.example.amazonprimeclone.retrofit;

import com.example.amazonprimeclone.modal.CastandCredits.Movies.MovieCredits;
import com.example.amazonprimeclone.modal.CastandCredits.Series.SeriesCredits;
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits;
import com.example.amazonprimeclone.modal.MovieCategory;
import com.example.amazonprimeclone.modal.MovieDetail.MovieDetailsModel;
import com.example.amazonprimeclone.modal.PersonDetailsModel;
import com.example.amazonprimeclone.modal.SearchMovie;
import com.example.amazonprimeclone.modal.SearchPerson;
import com.example.amazonprimeclone.modal.SearchSeries;
import com.example.amazonprimeclone.modal.SeriesCategory;

import com.example.amazonprimeclone.modal.SeriesDetail.SeriesDetailsModel;
import com.example.amazonprimeclone.modal.Trailers.TrailerModel;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface RetrofitService {

    //https://api.themoviedb.org/3/search/movie?api_key="YOUR_API_KEY"&query="MOVIE_NAME"
    //api to get the movies by search

    //https://api.themoviedb.org/3/search/person?api_key="YOUR_API_KEY"&query="ACTOR_NAME"
    //api to get the persons name

    @GET("search/movie")
    Call<SearchMovie> getMoviesByQuery(@Query("api_key")String api_key,@Query("query")String query);

    @GET("search/person")
    Call<SearchPerson> getPersonByQuery(@Query("api_key")String api_key,@Query("query")String query);

    @GET("person/popular")
    Call<SearchPerson> getPopularPerson(@Query("api_key")String api_key);

    //get the movies categories list
    @GET("genre/movie/list")
    Call<MovieCategory> getCategories(@Query("api_key")String api_key);

    @GET("https://api.themoviedb.org/3/discover/movie?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchMovie> getCategoriesResults(@Query("with_genres")String genres);

    @GET("movie/popular?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchMovie> getTopRated();

    @GET("tv/popular?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchSeries> getTopRatedSeries();

    //get the series categories list
    @GET("genre/tv/list")
    Call<SeriesCategory> getCategoriesSeries(@Query("api_key")String api_key);

    @GET("https://api.themoviedb.org/3/discover/tv?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchSeries> getCategoriesResultsSeries(@Query("with_genres")String genres);

    //get person details by id
    @GET("person/{person_id}")
    Call<PersonDetailsModel> getPersonDetailsById(@Path("person_id")String personId,@Query("api_key")String apiKey);

    @GET("discover/movie")
    Call<SearchMovie> getMoviesKnownFor(@Query("with_cast") String id,@Query("api_key")String apiKey);

    @GET("person/{person_id}/tv_credits")
    Call<SeriesKnownForCredits> getSeriesKnownFor(@Path("person_id") String id, @Query("api_key")String apiKey);

    //get movie details by id
    @GET("movie/{movie_id}")
    Call<MovieDetailsModel> getMovieDetails(@Path("movie_id")String movie_id,@Query("api_key")String apiKey);

    //get movies cast ans crew details
    @GET("movie/{movie_id}/credits")
    Call<MovieCredits> getCastDetails(@Path("movie_id")String movie_id,@Query("api_key")String apiKey);

    //get movie trailers
    @GET("movie/{movie_id}/videos")
    Call<TrailerModel> getMovieTrailers(@Path("movie_id")String movieId, @Query("api_key")String apiKey);

    //get recommendation according to movie id
    @GET("movie/{movie_id}/recommendations")
    Call<SearchMovie> getMovieRecommendations(@Path("movie_id")String movieId,@Query("api_key")String apiKey);

    //get series details by id
    @GET("tv/{tv_id}")
    Call<SeriesDetailsModel> getSeriesDetails(@Path("tv_id")String series_id, @Query("api_key")String apiKey);

    //get recommendation according to series id
    @GET("tv/{tv_id}/recommendations")
    Call<SearchSeries> getSeriesRecommendations(@Path("tv_id")String series_id,@Query("api_key")String apiKey);

    //get series trailers
    @GET("tv/{tv_id}/videos")
    Call<TrailerModel> getSeriesTrailers(@Path("tv_id")String series_id, @Query("api_key")String apiKey);

    //get series cast ans crew details
    @GET("tv/{tv_id}/credits")
    Call<SeriesCredits> getSeriesCastDetails(@Path("tv_id")String series_id, @Query("api_key")String apiKey);

}
