package com.example.amazonprimeclone.fragments.people

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.amazonprimeclone.data.remote.Constants
import com.example.amazonprimeclone.modal.PersonResponseResults
import com.example.amazonprimeclone.retrofit.repository.RetrofitMovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PeopleFragmentViewModel @Inject constructor(private val retrofitMovieRepository: RetrofitMovieRepository): ViewModel() {

    private val popularPersonList: MutableLiveData<List<PersonResponseResults>> = MutableLiveData()
    private val performPersonListSearch: MutableLiveData<List<PersonResponseResults>> = MutableLiveData()

    fun getPopularPersonLists(): MutableLiveData<List<PersonResponseResults>> {
        loadPopularPersons()
        return popularPersonList
    }

    fun getPerformPersonListSearch(query: String?): LiveData<List<PersonResponseResults>> {
        loadResults(query!!)
        return performPersonListSearch
    }

    private fun loadResults(query: String) = viewModelScope.launch(Dispatchers.IO){

        val response = retrofitMovieRepository.getPersonByQuery(Constants.API_KEY, query)

        if(response!=null){
            performPersonListSearch.postValue(response.results)
        }
    }

    private fun loadPopularPersons() = viewModelScope.launch(Dispatchers.IO){
        val response = retrofitMovieRepository.getPopularPerson(Constants.API_KEY)
        if(response!=null){
            popularPersonList.postValue(response.results)
        }
    }
}