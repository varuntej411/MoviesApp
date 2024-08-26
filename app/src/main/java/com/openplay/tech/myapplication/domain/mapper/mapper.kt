package com.openplay.tech.myapplication.domain.mapper

import com.openplay.tech.myapplication.data.responsemodels.MoviesList
import com.openplay.tech.myapplication.domain.model.MoviesDetailsModel
import com.openplay.tech.myapplication.domain.model.MoviesModel

fun List<MoviesList>.toDomainMoviesModel(): List<MoviesModel> = map {
    MoviesModel(
        adult = it.adult,
        backdrop_path = it.backdrop_path,
        genre_ids = it.genre_ids,
        id = it.id,
        original_language = it.original_language,
        original_title = it.original_title,
        overview = it.overview,
        popularity = it.popularity,
        poster_path = it.poster_path,
        release_date = it.release_date,
        title = it.title,
        video = it.video,
        vote_average = it.vote_average,
        vote_count = it.vote_count
    )
}

fun MoviesList.toDomainMoviesDetailsModel(): MoviesDetailsModel {
    return MoviesDetailsModel(
        adult = adult,
        backdrop_path = backdrop_path,
        genre_ids = genre_ids,
        id = id,
        original_language = original_language,
        original_title = original_title,
        overview = overview,
        popularity = popularity,
        poster_path = poster_path,
        release_date = release_date,
        title = title,
        video = video,
        vote_average = vote_average,
        vote_count = vote_count
    )
}
