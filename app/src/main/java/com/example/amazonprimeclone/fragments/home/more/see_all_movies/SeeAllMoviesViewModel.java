package com.example.amazonprimeclone.fragments.home.more.see_all_movies;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.modal.SearchMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllMoviesViewModel extends ViewModel {

    private final MutableLiveData<List<MovieResponseResults>> allMoviesList;
    private final String id;
    public final String title;

    public SeeAllMoviesViewModel(SavedStateHandle savedStateHandle){
        allMoviesList = new MutableLiveData<>();
        id = savedStateHandle.get("id");
        title = savedStateHandle.get("title");

        getAllMovies();
    }

    public MutableLiveData<List<MovieResponseResults>> getAllMoviesList() {
        return allMoviesList;
    }

    private void getAllMovies() {
        Call<SearchMovie> movieResponseCall = Constants.retrofitService.getCategoriesResults(id);
        movieResponseCall.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call,@NonNull Response<SearchMovie> response) {
                SearchMovie searchMovie = response.body();
                //add to list
                if(searchMovie!=null){
                    allMoviesList.setValue(searchMovie.getResults());
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchMovie> call,@NonNull Throwable t) {

            }
        });
    }
}
