package com.koshake1.movieapp.api

import com.koshake1.movieapp.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    fun getMovie(
        @Query("id") id: Int,
        @Query("api_key") apiKey: String,
    ): Deferred<Movie>

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
    ): Deferred<List<Movie>>
}