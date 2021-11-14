package com.koshake1.movieapp.model

data class MoviesResponse (
    val pages : Int,
    val total_results : Int,
    val total_pages : Int,
    val results : List<Movie>
)