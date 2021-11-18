package com.koshake1.movieapp.api

import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.data.MoviesResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/{id}")
    fun getMovie(
        @Path("id") id: String,
        @Query("api_key") apiKey: String,
    ): Deferred<Movie>

    @GET("discover/movie")
    fun getMovies(
        @Query("api_key") apiKey: String,
    ): Deferred<MoviesResponse>
}