package com.koshake1.movieapp.model.data_sources

import com.koshake1.movieapp.api.ApiService

class RemoteDataSourceImpl(private val api : ApiService) : RemoteDataSource {
    override suspend fun getSingleMovieById(id: String, apiKey : String) =
        api.getMovie(id, apiKey).await()

    override suspend fun getMovies(apiKey : String) =
        api.getMovies(apiKey).await()
}