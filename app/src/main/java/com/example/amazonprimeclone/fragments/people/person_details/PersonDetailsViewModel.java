package com.example.amazonprimeclone.fragments.people.person_details;

import static com.example.amazonprimeclone.data.remote.Constants.API_KEY;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

import com.example.amazonprimeclone.data.remote.Constants;
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.Cast;
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.SeriesKnownForCredits;
import com.example.amazonprimeclone.modal.MovieResponseResults;
import com.example.amazonprimeclone.modal.PersonDetailsModel;
import com.example.amazonprimeclone.modal.SearchMovie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PersonDetailsViewModel extends ViewModel {

    private final MutableLiveData<List<Cast>> seriesKnownFor;
    private final MutableLiveData<List<MovieResponseResults>> moviesKnownFor;
    private final MutableLiveData<PersonDetailsModel> personDetailsData;

    private final String personID;

    public PersonDetailsViewModel(SavedStateHandle savedStateHandle){
        seriesKnownFor = new MutableLiveData<>();
        moviesKnownFor = new MutableLiveData<>();
        personDetailsData = new MutableLiveData<>();
        personID = savedStateHandle.get("person_id");

        getPersonsData();
    }

    public MutableLiveData<List<MovieResponseResults>> getMoviesKnownFor() {
        return moviesKnownFor;
    }

    public MutableLiveData<List<Cast>> getSeriesKnownFor() {
        return seriesKnownFor;
    }

    public MutableLiveData<PersonDetailsModel> getPersonDetailsData() {
        return personDetailsData;
    }

    private void getPersonsData() {
        loadPersonDetails();
        loadSeriesKnownFor();
        loadMovesKnownFor();
    }

    private void loadPersonDetails() {
        Call<PersonDetailsModel> personDetails = Constants.retrofitService.getPersonDetailsById(personID,API_KEY);
        personDetails.enqueue(new Callback<PersonDetailsModel>() {
            @Override
            public void onResponse(@NonNull Call<PersonDetailsModel> call, @NonNull Response<PersonDetailsModel> response) {
                if(response.isSuccessful()){
                    personDetailsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<PersonDetailsModel> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadSeriesKnownFor(){
        Call<SeriesKnownForCredits> seriesCall = Constants.retrofitService.getSeriesKnownFor(personID,API_KEY);
        seriesCall.enqueue(new Callback<SeriesKnownForCredits>() {
            @Override
            public void onResponse(@NonNull Call<SeriesKnownForCredits> call, @NonNull Response<SeriesKnownForCredits> response) {
                if(response.isSuccessful()){
                    SeriesKnownForCredits searchseries = response.body();
                    if(searchseries!=null){
                        seriesKnownFor.setValue(searchseries.getCast());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SeriesKnownForCredits> call, @NonNull Throwable t) {

            }
        });
    }

    private void loadMovesKnownFor(){
        Call<SearchMovie> movieCall = Constants.retrofitService.getMoviesKnownFor(personID,API_KEY);
        movieCall.enqueue(new Callback<SearchMovie>() {
            @Override
            public void onResponse(@NonNull Call<SearchMovie> call, @NonNull Response<SearchMovie> response) {
                if(response.isSuccessful()){
                    SearchMovie searchMovie = response.body();
                    if(searchMovie!=null){
                        moviesKnownFor.setValue(searchMovie.getResults());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<SearchMovie> call, @NonNull Throwable t) {

            }
        });
    }

}
