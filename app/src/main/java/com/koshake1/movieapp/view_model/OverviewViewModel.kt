package com.koshake1.movieapp.view_model

import com.koshake1.movieapp.base.BaseViewModel
import com.koshake1.movieapp.model.data.Movie
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import com.koshake1.movieapp.model.repository.MoviesRepository
import com.koshake1.movieapp.util.API_KEY
import kotlinx.coroutines.launch

class OverviewViewModel(val repository: MoviesRepository) : BaseViewModel<Movie>() {
    fun getMovie(id : String) {
        mStateLiveData.postValue(MoviesViewState.Loading(true))
        viewModelCoroutineScope.launch {
            val movie = repository.getSingleMovie(id, API_KEY)
            mStateLiveData.postValue(MoviesViewState.Success(movie))
        }
    }
}