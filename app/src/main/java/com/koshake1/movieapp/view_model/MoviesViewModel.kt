package com.koshake1.movieapp.view_model

import com.koshake1.movieapp.base.BaseViewModel
import com.koshake1.movieapp.model.data.MoviesResponse
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import com.koshake1.movieapp.model.repository.MoviesRepository
import com.koshake1.movieapp.util.API_KEY
import kotlinx.coroutines.*

class MoviesViewModel(private val repository: MoviesRepository)
    : BaseViewModel<MoviesResponse>() {

    fun getMovies() {
        mStateLiveData.postValue(MoviesViewState.Loading(true))
        viewModelCoroutineScope.launch {
            val movies = repository.getAllMovies(API_KEY)
            mStateLiveData.postValue(MoviesViewState.Success(movies))
        }
    }
}