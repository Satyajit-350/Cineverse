package com.example.amazonprimeclone.modal.CastandCredits.Series

data class SeriesCast(
    val adult: Boolean,
    val character: String,
    val credit_id: String,
    val gender: Int,
    val id: Int,
    val known_for_department: String,
    val name: String,
    val order: Int,
    val original_name: String,
    val popularity: Double,
    val profile_path: String
){
    val profileImage get() = "https://image.tmdb.org/t/p/w500$profile_path"
}