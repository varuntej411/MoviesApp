package com.openplay.tech.myapplication.data.remote

import com.openplay.tech.myapplication.commonutils.Constants
import com.openplay.tech.myapplication.data.responsemodels.MoviesDetailsResponseModel
import com.openplay.tech.myapplication.data.responsemodels.MoviesResponseModel
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @Headers(Constants.BearerToken, "accept: application/json")
    @GET(Constants.GET_POPULAR)
    suspend fun getAllMovies(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api_key") api_key: String,
    ): Response<MoviesResponseModel>

    @GET("/popular?{limit}{offset}{api_key}")
    suspend fun getMovieByPageLimit(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int,
        @Query("api_key") api_key: String,
    ): Response<MoviesDetailsResponseModel>

}