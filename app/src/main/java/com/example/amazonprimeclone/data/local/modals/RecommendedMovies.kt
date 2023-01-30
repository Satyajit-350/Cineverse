package com.example.amazonprimeclone.data.local.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "recommendation_table")
data class RecommendedMovies(
    @PrimaryKey
    val movieId: String,
    val movieName: String,
    val poster: String
)
