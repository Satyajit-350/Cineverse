package com.example.amazonprimeclone.fragments.people.person_details

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.CastandCredits.SeriesKnownFor.Cast
import com.example.amazonprimeclone.modal.MovieResponseResults
import com.example.amazonprimeclone.modal.PersonDetailsModel
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val retrofitMovieRepository: RetrofitMovieRepository
): ViewModel() {

    private val seriesKnownFor: MutableLiveData<List<Cast>> = MutableLiveData()
    private val moviesKnownFor: MutableLiveData<List<MovieResponseResults>> = MutableLiveData()
    private val personDetailsData: MutableLiveData<PersonDetailsModel?> = MutableLiveData()

    private var personID: String? = null

    init {
        personID = savedStateHandle.get<String>("person_id")
        getPersonsData()
    }

    fun getMoviesKnownFor(): MutableLiveData<List<MovieResponseResults>> {
        return moviesKnownFor
    }

    fun getSeriesKnownFor(): MutableLiveData<List<Cast>> {
        return seriesKnownFor
    }

    fun getPersonDetailsData(): MutableLiveData<PersonDetailsModel?> {
        return personDetailsData
    }

    private fun getPersonsData() {
        loadPersonDetails()
        loadSeriesKnownFor()
        loadMovesKnownFor()
    }

    private fun loadPersonDetails() = viewModelScope.launch{
        val personDetails = retrofitMovieRepository.getPersonDetailsById(personID, Constants.API_KEY)
        if(personDetails!=null){
            personDetailsData.postValue(personDetails)
        }
    }

    private fun loadSeriesKnownFor() = viewModelScope.launch{
        val seriesCall = retrofitMovieRepository.getSeriesKnownFor(personID, Constants.API_KEY)
        if(seriesCall!=null){
            seriesKnownFor.postValue(seriesCall.cast)
        }
    }

    private fun loadMovesKnownFor() = viewModelScope.launch{
        val movieCall = retrofitMovieRepository.getMoviesKnownFor(personID, Constants.API_KEY)
        if(movieCall!=null){
            moviesKnownFor.postValue(movieCall.results)
        }
    }

}