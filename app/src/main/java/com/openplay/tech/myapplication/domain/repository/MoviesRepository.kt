package com.openplay.tech.myapplication.domain.repository

import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.model.MoviesDetailsModel
import com.openplay.tech.myapplication.domain.model.MoviesModel
import kotlinx.coroutines.flow.Flow

interface MoviesRepository {

    suspend fun getAllMovies(limit: Int, offset: Int, api_key: String): Result<List<MoviesModel>>

    suspend fun getMovieByPopular(
        limit: Int,
        offset: Int,
        api_key: String,
    ): Result<MoviesDetailsModel>

    // for offline data
    suspend fun insertMovies(moviesEntity: MoviesEntity)
  //  suspend fun updateMovies(moviesEntity: MoviesEntity)
    suspend fun deleteMovies(moviesEntity: MoviesEntity)
    fun getAllMovies(): Flow<List<MoviesEntity>>
}