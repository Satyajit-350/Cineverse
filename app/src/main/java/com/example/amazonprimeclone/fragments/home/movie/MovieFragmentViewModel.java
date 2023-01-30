package com.example.amazonprimeclone.fragments.home.movie;

import static com.example.amazonprimeclone.data.remote.Constants.API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.data.local.modals.RecommendedMovies;
import com.example.amazonprimeclone.data.local.repository.MovieRecommendationRepository;
import com.example.amazonprimeclone.modal.MovieCategory;
import com.example.amazonprimeclone.modal.MovieCategoryResults;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.modal.SearchMovie;
import com.example.amazonprimeclone.data.remote.Constants;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@HiltViewModel
public class MovieFragmentViewModel extends ViewModel {

    private final MovieRecommendationRepository recommendationRepository;
    private final MutableLiveData<List<MovieResponseResults>> slideList;
    private final MutableLiveData<List<MovieCategoryResults>> resultsList;
    public final LiveData<List<RecommendedMovies>> recommendationList;

    @Inject
    public MovieFragmentViewModel(MovieRecommendationRepository recommendationRepository){
        slideList = new MutableLiveData<>();
        resultsList = new MutableLiveData<>();
        this.recommendationRepository = recommendationRepository;
        recommendationList = recommendationRepository.getAllRecommendedMovies();
        loadData();
    }

    public LiveData<List<MovieCategoryResults>> getResultsList() {
        return resultsList;
    }

    public LiveData<List<MovieResponseResults>> getSlideList() {
        return slideList;
    }

    private void loadData() {
        getSlideImg();
        getCategories();
    }

    private void getSlideImg() {

        Call<SearchMovie> movieResponseCall = Constants.retrofitService.getTopRated();
        movieResponseCall.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call, @NonNull Response<SearchMovie> response) {
                SearchMovie s_category = response.body();
                if(s_category!=null){
                    slideList.setValue(s_category.getResults().subList(4,13));
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchMovie> call, @NonNull Throwable t) {

            }
        });
    }

    private void getCategories() {

        Call<MovieCategory> movieResponseCall = Constants.retrofitService.getCategories(API_KEY);
        movieResponseCall.enqueue(new Callback<MovieCategory>() {
            @Override
            public void onResponse(@NonNull Call<MovieCategory> call, @NonNull Response<MovieCategory> response) {
                MovieCategory m_category = response.body();
                if(m_category!=null){
                    resultsList.setValue(m_category.getGenres());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieCategory> call, @NonNull Throwable t) {

            }
        });

    }
}
