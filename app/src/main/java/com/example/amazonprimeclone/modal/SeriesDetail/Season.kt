package com.example.amazonprimeclone.modal.SeriesDetail

data class Season(
    val air_date: String,
    val episode_count: Int,
    val id: Int,
    val name: String,
    val overview: String,
    val poster_path: String,
    val season_number: Int
){
    val posterPath get() = "https://image.tmdb.org/t/p/original$poster_path"

    val episodeCount get() = "$episode_count Episodes"
}