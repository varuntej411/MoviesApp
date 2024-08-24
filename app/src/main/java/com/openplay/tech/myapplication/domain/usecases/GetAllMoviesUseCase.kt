package com.openplay.tech.myapplication.domain.usecases

import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.data.dto.MoviesDto
import com.openplay.tech.myapplication.data.dto.toMoviesModel
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.domain.repository.MoviesRepository
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val moviesRepository: MoviesRepository) {
    operator fun invoke(): Flow<APIDataStatus<List<MoviesModel>>> = flow {
        try {
            emit(APIDataStatus.LOADING())
            val products = moviesRepository.getAllMovies().map { it.toMoviesModel() }
            emit(APIDataStatus.SUCCESS(products))
        } catch (e: HttpException) {
            emit(APIDataStatus.ERROR(e.localizedMessage ?: "An Unexpected Error Occurred"))
        } catch (e: IOException) {
            emit(APIDataStatus.ERROR(e.message ?: "Couldn't reach server, Check Your Internet Connection"))
        }
    }.flowOn(IO)
}
