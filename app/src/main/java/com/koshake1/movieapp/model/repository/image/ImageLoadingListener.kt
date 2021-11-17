package com.koshake1.movieapp.model.repository.image

interface ImageLoadingListener {
    fun onLoadingSuccess(): Boolean
    fun onLoadingError(t: Throwable?): Boolean
}