package com.koshake1.movieapp.model.data

import com.koshake1.movieapp.model.data.viewstate.AppStateEntity

data class Movie(
    val id : String?,
    val original_title : String?,
    val overview : String?,
    val vote_average : Double?,
    val poster_path : String?,
    val release_date : String?,
    val runtime : Int?,
) : AppStateEntity
