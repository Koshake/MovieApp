package com.koshake1.movieapp.model.repository.image

interface ImageLoader<T> {
    fun loadImage(
        target: T,
        url: String,
        listener: ImageLoadingListener? = null
    )
}