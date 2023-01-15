package com.example.amazonprimeclone.modal.MovieDetail

data class FavoriteMovie(
    val id: Int,
    val vote_average: Float,
    val title: String,
    val poster_path: String,
    val original_language: String,
    val overview: String,
    val release_date: String,
    val isMovie: Boolean
)
