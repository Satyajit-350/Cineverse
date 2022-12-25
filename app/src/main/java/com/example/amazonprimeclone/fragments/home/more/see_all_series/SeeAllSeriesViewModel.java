package com.example.amazonprimeclone.fragments.home.more.see_all_series;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.modal.SearchSeries;
import com.example.amazonprimeclone.modal.SeriesResponseResults;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeeAllSeriesViewModel extends ViewModel {

    private final MutableLiveData<List<SeriesResponseResults>> allSeriesList;
    private final String id;
    public final String title;

    public SeeAllSeriesViewModel(SavedStateHandle savedStateHandle){
        allSeriesList = new MutableLiveData<>();
        id = savedStateHandle.get("id");
        title = savedStateHandle.get("title");

        getAllSeries();
    }

    public MutableLiveData<List<SeriesResponseResults>> getAllSeriesList() {
        return allSeriesList;
    }

    private void getAllSeries() {

        Call<SearchSeries> movieResponseCall = Constants.retrofitService.getCategoriesResultsSeries(id);
        movieResponseCall.enqueue(new Callback<SearchSeries>() {
            @Override
            public void onResponse(@NonNull Call<SearchSeries> call, @NonNull Response<SearchSeries> response) {
                SearchSeries searchSeries = response.body();
                //add to list
                if(searchSeries!=null){
                    allSeriesList.setValue(searchSeries.getResults());
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchSeries> call,@NonNull Throwable t) {

            }
        });

    }
}
