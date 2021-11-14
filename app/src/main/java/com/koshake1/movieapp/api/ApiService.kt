package com.koshake1.movieapp.api

import com.koshake1.movieapp.model.Movie
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("3/movie/550")
    fun getMovies(
        @Query("api_key") apiKey: String,
    ): Deferred<List<Movie>>
}