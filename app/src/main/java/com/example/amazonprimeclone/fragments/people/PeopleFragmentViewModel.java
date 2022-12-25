package com.example.amazonprimeclone.fragments.people;

import static com.example.amazonprimeclone.data.remote.Constants.API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.modal.PersonResponseResults;
import com.example.amazonprimeclone.modal.SearchPerson;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PeopleFragmentViewModel extends ViewModel {

    private final MutableLiveData<List<PersonResponseResults>> popularPersonList;
    private final MutableLiveData<List<PersonResponseResults>> performPersonListSearch;

    public PeopleFragmentViewModel(){
        popularPersonList = new MutableLiveData<>();
        performPersonListSearch = new MutableLiveData<>();
        loadPopularPersons();
    }

    public MutableLiveData<List<PersonResponseResults>> getPopularPersonList() {
        return popularPersonList;
    }

    public LiveData<List<PersonResponseResults>> getPerformPersonListSearch(String query) {
        loadResults(query);
        return performPersonListSearch;
    }

    private void loadResults(String query) {
        Call<SearchPerson> movieResponseCall = Constants.retrofitService.getPersonByQuery(API_KEY,query);
        movieResponseCall.enqueue(new Callback<SearchPerson>() {
            @Override
            public void onResponse(@NonNull Call<SearchPerson> call, @NonNull Response<SearchPerson> response) {
                SearchPerson searchPerson = response.body();
                //add to list
                if(searchPerson!=null){
                    performPersonListSearch.setValue(searchPerson.getResults());
                }

            }

            @Override
            public void onFailure(@NonNull Call<SearchPerson> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadPopularPersons() {
        Call<SearchPerson> movieResponseCall = Constants.retrofitService.getPopularPerson(API_KEY);
        movieResponseCall.enqueue(new Callback<SearchPerson>() {
            @Override
            public void onResponse(@NonNull Call<SearchPerson> call,@NonNull Response<SearchPerson> response) {
                SearchPerson searchPerson = response.body();
                //add to list
                if(searchPerson!=null){
                    popularPersonList.setValue(searchPerson.getResults());
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchPerson> call,@NonNull Throwable t) {

            }
        });
    }
}
