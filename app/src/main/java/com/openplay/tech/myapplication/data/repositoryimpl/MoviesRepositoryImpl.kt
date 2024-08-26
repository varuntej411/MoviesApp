package com.openplay.tech.myapplication.data.repositoryimpl

import com.openplay.tech.myapplication.data.local.MoviesDAO
import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.data.remote.MovieApiService
import com.openplay.tech.myapplication.domain.mapper.toDomainMoviesDetailsModel
import com.openplay.tech.myapplication.domain.mapper.toDomainMoviesModel
import com.openplay.tech.myapplication.domain.model.MoviesDetailsModel
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject


class MoviesRepositoryImpl @Inject constructor(
    private val moviesRepository: MovieApiService,
    private val moviesDao: MoviesDAO
) :
    MoviesRepository {

    override suspend fun getAllMovies(limit:Int, offset:Int, api_key:String): Result<List<MoviesModel>> {
        return try {
            val response = moviesRepository.getAllMovies(limit = limit, offset = offset, api_key = api_key)
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    Result.success(it.toDomainMoviesModel())
                } ?: run {
                    Result.failure(Exception("Error Occurred"))
                }
            } else {
                Result.failure(Exception("Error Occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun getMovieByPopular(
        limit: Int,
        offset: Int,
        api_key: String
    ): Result<MoviesDetailsModel> {
        return try {
            val response = moviesRepository.getMovieByPageLimit(
                limit = limit,
                offset = offset,
                api_key = api_key
            )
            if (response.isSuccessful) {
                response.body()?.results?.let {
                    Result.success(it.first().toDomainMoviesDetailsModel())
                } ?: run {
                    Result.failure(Exception("Error Occurred"))
                }
            } else {
                Result.failure(Exception("Error Occurred"))
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }

    override suspend fun insertMovies(moviesEntity: MoviesEntity) {
        moviesDao.insertAllMovies(moviesEntity)
    }

//    override suspend fun updateMovies(moviesEntity: MoviesEntity) {
//        moviesDao.updateAllMovies(moviesEntity)
//    }

    override suspend fun deleteMovies(moviesEntity: MoviesEntity) {
       moviesDao.deleteMovies(moviesEntity)
    }

    override fun getAllMovies(): Flow<List<MoviesEntity>> {
       return moviesDao.getAllMovies()
    }

}
