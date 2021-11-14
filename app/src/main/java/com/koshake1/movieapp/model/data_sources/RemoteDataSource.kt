package com.koshake1.movieapp.model.data_sources

import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.data.MoviesResponse

interface RemoteDataSource {
    suspend fun getSingleMovieById(id : String, apiKey : String) : Movie

    suspend fun getMovies(apiKey : String) : MoviesResponse

}