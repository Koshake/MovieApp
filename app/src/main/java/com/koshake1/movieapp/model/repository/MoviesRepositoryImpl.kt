package com.koshake1.movieapp.model.repository

import com.koshake1.movieapp.model.data_sources.RemoteDataSource

class MoviesRepositoryImpl(private val dataSource : RemoteDataSource) : MoviesRepository {
    override suspend fun getAllMovies(apiKey : String) =
        dataSource.getMovies(apiKey)

    override suspend fun getSingleMovie(id : String, apiKey : String) =
        dataSource.getSingleMovieById(id, apiKey)
}