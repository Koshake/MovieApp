package com.koshake1.movieapp.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.koshake1.movieapp.model.data.viewstate.AppStateEntity
import com.koshake1.movieapp.model.data.viewstate.MoviesViewState
import kotlinx.coroutines.*

abstract class BaseViewModel<D : AppStateEntity>(
) : ViewModel() {

    protected val mStateLiveData = MutableLiveData<MoviesViewState<D>>()
    val stateLiveData get() = mStateLiveData as LiveData<MoviesViewState<D>>

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected open fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    private fun handleError(error: Throwable) {
        mStateLiveData.postValue(MoviesViewState.Error(error))
    }
}