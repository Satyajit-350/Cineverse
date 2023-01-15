package com.example.amazonprimeclone.data.local.modals

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_table")
data class MovieAndSeriesData(
    @PrimaryKey
    val id: Int,
    val vote_average: Float,
    val title: String,
    val poster_path: String,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val isMovie:Boolean
)
