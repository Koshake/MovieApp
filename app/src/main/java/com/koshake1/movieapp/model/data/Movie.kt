package com.koshake1.movieapp.model.data

import com.koshake1.movieapp.model.data.viewstate.AppStateEntity

data class Movie(
    val original_title : String?,
    val overview : String?,
    val popularity : Double?,
    val poster_path : String?,
    val release_date : String?,
    val runtime : Int?,
) : AppStateEntity
