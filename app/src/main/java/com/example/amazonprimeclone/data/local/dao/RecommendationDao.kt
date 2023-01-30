package com.example.amazonprimeclone.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.amazonprimeclone.data.local.modals.RecommendedMovies

@Dao
interface RecommendationDao {

    @Query("SELECT COUNT(*) FROM recommendation_table")
    fun getCount(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(recommendedMovies: RecommendedMovies)

    @Delete
    suspend fun delete(recommendedMovies: RecommendedMovies)

    @Query("DELETE FROM recommendation_table WHERE movieId = (SELECT MIN(movieId) FROM recommendation_table)")
    fun deleteOldestRow()

    @Query("SELECT * FROM recommendation_table ORDER BY movieId DESC")
    fun getAllRecommendedMovies(): LiveData<List<RecommendedMovies>>

}