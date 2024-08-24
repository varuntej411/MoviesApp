package com.openplay.tech.myapplication.data.remote

import com.openplay.tech.myapplication.data.dto.MoviesDto
import retrofit2.http.GET

interface MovieApiService {

    @GET()
    fun getAllMovies(): List<MoviesDto>
}