package com.koshake1.movieapp.model.data.viewstate

sealed class MoviesViewState<out T : AppStateEntity> {
    data class Success<out T : AppStateEntity>(val data: T) : MoviesViewState<T>()
    data class Error<out T : AppStateEntity>(val error: Throwable) : MoviesViewState<T>()
    data class Loading<out T : AppStateEntity>(val isLoading: Boolean) : MoviesViewState<T>()
}