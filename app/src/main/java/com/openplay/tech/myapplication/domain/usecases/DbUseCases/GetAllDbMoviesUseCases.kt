package com.openplay.tech.myapplication.domain.usecases.DbUseCases

import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetAllDbMoviesUseCases @Inject constructor(private val movieRepository: MoviesRepository) {

    operator fun invoke() = movieRepository.getAllMovies()
}