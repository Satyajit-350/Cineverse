package com.example.amazonprimeclone.retrofit;

import com.example.amazonprimeclone.modal.SearchMovie;
import com.example.amazonprimeclone.modal.SearchSeries;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("https://api.themoviedb.org/3/discover/movie?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchMovie> getCategoriesResults(@Query("with_genres")String genres);

    @GET("https://api.themoviedb.org/3/discover/tv?api_key=5177b3d25448b9eec5d4b28b8dfabc83")
    Call<SearchSeries> getCategoriesResultsSeries(@Query("with_genres")String genres);

}
