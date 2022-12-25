package com.example.amazonprimeclone.data.local.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.amazonprimeclone.data.local.MovieDatabase
import com.example.amazonprimeclone.data.local.mapper.FavoriteMapper
import com.example.amazonprimeclone.data.local.modals.MovieAndSeriesData
import com.example.amazonprimeclone.modal.MovieDetail.FavoriteMovie
import java.util.concurrent.Executors
import javax.inject.Inject

class MovieAndSeriesRepository @Inject constructor(movieDatabase: MovieDatabase) {

    private val movieAndSeriesDao = movieDatabase.movieAndSeriesDao()

    private val executorService = Executors.newSingleThreadExecutor()

    fun insert(favoriteMovie: FavoriteMovie) {
        executorService.execute { movieAndSeriesDao.insert(FavoriteMapper.favoriteToDatabaseFavorite(favoriteMovie)) }
    }

    fun getAllData() : LiveData<List<MovieAndSeriesData>> = movieAndSeriesDao.getAllData()

    fun delete(favoriteMovie: FavoriteMovie){
        executorService.execute {movieAndSeriesDao.delete(FavoriteMapper.favoriteToDatabaseFavorite(favoriteMovie))}
    }

    private val _movieByIdExists= MutableLiveData<Boolean>(false)
    val movieByIdExists: LiveData<Boolean>
        get() = _movieByIdExists

    suspend fun getMovie(movieId:Int) {
        var response = movieAndSeriesDao.getMovie(movieId)
        if (response!=null)
            _movieByIdExists.postValue(true)
        else
            _movieByIdExists.postValue(false)
    }

}