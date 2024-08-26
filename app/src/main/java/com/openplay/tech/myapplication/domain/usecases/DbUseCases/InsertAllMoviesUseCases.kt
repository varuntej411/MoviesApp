package com.openplay.tech.myapplication.domain.usecases.DbUseCases

import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class InsertAllMoviesUseCases @Inject constructor(private val movieRepository: MoviesRepository) {
    operator fun invoke(moviesEntity: MoviesEntity) = flow<Unit> {
        movieRepository.insertMovies(moviesEntity)
    }.flowOn(IO)
}