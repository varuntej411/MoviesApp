package com.openplay.tech.myapplication.domain.usecases

import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.domain.model.MoviesDetailsModel
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMoviesDetailsUseCase @Inject constructor(
    private val moviesRepository: MoviesRepository
) {

    operator fun invoke(limit: Int, offset: Int, api_key: String): Flow<APIDataStatus<MoviesDetailsModel>> = flow {
        try {
            emit(APIDataStatus.LOADING())
            val detailsModelResult = moviesRepository.getMovieByPopular(limit = limit, offset = offset, api_key = api_key)

            if (detailsModelResult.isSuccess) {
                emit(APIDataStatus.SUCCESS(data = detailsModelResult.getOrThrow()))
            } else {
                emit(
                    APIDataStatus.ERROR(
                        detailsModelResult.exceptionOrNull()?.localizedMessage ?: "An Unexpected Error Occurred"
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