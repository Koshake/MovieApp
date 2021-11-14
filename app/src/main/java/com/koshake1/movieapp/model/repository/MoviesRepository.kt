package com.koshake1.movieapp.model.repository

import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.data.MoviesResponse

interface MoviesRepository {
    suspend fun getAllMovies(apiKey : String) : MoviesResponse

    suspend fun getSingleMovie(id : String, apiKey : String) : Movie
}