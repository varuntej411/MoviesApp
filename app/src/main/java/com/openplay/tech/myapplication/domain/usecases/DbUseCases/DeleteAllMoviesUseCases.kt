package com.openplay.tech.myapplication.domain.usecases.DbUseCases

import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class DeleteAllMoviesUseCases @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(moviesEntity: MoviesEntity) = flow<Unit> {
        moviesRepository.deleteMovies(moviesEntity)
    }.flowOn(Dispatchers.IO)
}