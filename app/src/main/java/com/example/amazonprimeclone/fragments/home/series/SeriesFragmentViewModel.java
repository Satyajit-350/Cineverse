package com.example.amazonprimeclone.fragments.home.series;

import static com.example.amazonprimeclone.data.remote.Constants.API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.modal.SearchSeries;
import com.example.amazonprimeclone.modal.SeriesCategory;
import com.example.amazonprimeclone.modal.SeriesCategoryResults;
import com.example.amazonprimeclone.modal.SeriesResponseResults;
import com.example.amazonprimeclone.data.remote.Constants;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SeriesFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<SeriesResponseResults>> slideList;
    private final MutableLiveData<List<SeriesCategoryResults>> resultsList;

    public SeriesFragmentViewModel(){
        slideList = new MutableLiveData<>();
        resultsList = new MutableLiveData<>();
        getData();
    }

    public MutableLiveData<List<SeriesCategoryResults>> getResultsList() {
        return resultsList;
    }

    public MutableLiveData<List<SeriesResponseResults>> getSlideList() {
        return slideList;
    }

    private void getData() {
        loadSlideList();
        loadResultList();
    }

    private void loadSlideList() {
        Call<SearchSeries> movieResponseCall = Constants.retrofitService.getTopRatedSeries();
        movieResponseCall.enqueue(new Callback<SearchSeries>() {
            @Override
            public void onResponse(@NonNull Call<SearchSeries> call, @NonNull Response<SearchSeries> response) {
                SearchSeries category = response.body();
                //add to list
                if(category!=null){
                    slideList.setValue(category.getResults().subList(0,9));
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchSeries> call, @NonNull Throwable t) {

            }
        });

    }

    private void loadResultList() {
        Call<SeriesCategory> movieResponseCall = Constants.retrofitService.getCategoriesSeries(API_KEY);
        movieResponseCall.enqueue(new Callback<SeriesCategory>() {
            @Override
            public void onResponse(@NonNull Call<SeriesCategory> call, @NonNull Response<SeriesCategory> response) {
                SeriesCategory category = response.body();
                //add to list
                if(category!=null){
                    resultsList.setValue(category.getGenres());
                }

            }

            @Override
            public void onFailure(@NonNull Call<SeriesCategory> call, @NonNull Throwable t) {

            }
        });
    }

}
