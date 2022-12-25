package com.example.amazonprimeclone.fragments.search;

import static com.example.amazonprimeclone.data.remote.Constants.API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.modal.SearchMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<MovieResponseResults>> movieResults;

    public SearchFragmentViewModel(){
        movieResults = new MutableLiveData<>();
    }

    public LiveData<List<MovieResponseResults>> getMovieResults(String query) {
        loadMovieResults(query);
        return movieResults;
    }

    private void loadMovieResults(String query) {
        Call<SearchMovie> movieResponseCall = Constants.retrofitService.getMoviesByQuery(API_KEY,query);
        movieResponseCall.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call, @NonNull Response<SearchMovie> response) {
                SearchMovie searchMovie = response.body();
                //add to list
                if(searchMovie!=null){
                    movieResults.setValue(searchMovie.getResults());
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchMovie> call, @NonNull Throwable t) {

            }
        });
    }


}
