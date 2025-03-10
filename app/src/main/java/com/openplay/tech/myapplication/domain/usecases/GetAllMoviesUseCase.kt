package com.openplay.tech.myapplication.domain.usecases

import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {

    operator fun invoke(
        limit: Int,
        offset: Int,
        apiKey: String,
    ): Flow<APIDataStatus<List<MoviesModel>>> = flow {

        try {
            emit(APIDataStatus.LOADING())
            delay(2_000)
            val movies =
                moviesRepository.getAllMovies(limit = limit, offset = offset, api_key = apiKey)
            if (movies.isSuccess) {
                emit(APIDataStatus.SUCCESS(data = movies.getOrThrow()))
            } else {
                emit(
                    APIDataStatus.ERROR(
                        movies.exceptionOrNull()?.localizedMessage ?: "An Unexpected Error Occurred"
                    )
                )
            }
        } catch (e: HttpException) {
            emit(APIDataStatus.ERROR(e.localizedMessage ?: "An Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(
                APIDataStatus.ERROR(
                    e.message ?: "Couldn't reach server, Check Your Internet Connection"
                )
            )
        }
    }.flowOn(IO)
}

