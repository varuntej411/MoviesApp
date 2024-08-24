package com.openplay.tech.myapplication.data.dto

import android.graphics.Movie
import com.openplay.tech.myapplication.domain.model.MoviesModel

data class MoviesDto(
    val id: Int
)

fun MoviesDto.toMoviesModel(): MoviesModel {
    return MoviesModel(
      id =id
    )
}
