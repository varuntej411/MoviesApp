package com.openplay.tech.myapplication.data.repositoryimpl

import com.openplay.tech.myapplication.data.dto.MoviesDto
import com.openplay.tech.myapplication.data.remote.MovieApiService
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(private val moviesRepository: MovieApiService) :
    MoviesRepository {
    override suspend fun getAllMovies() : List<MoviesDto> {
        return moviesRepository.getAllMovies()
    }

}
