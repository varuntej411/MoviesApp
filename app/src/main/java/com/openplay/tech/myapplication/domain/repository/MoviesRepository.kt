package com.openplay.tech.myapplication.domain.repository

import com.openplay.tech.myapplication.data.dto.MoviesDto

interface MoviesRepository {
    suspend fun getAllMovies(): List<MoviesDto>

    // for offline data
    // suspend fun insertProducts(product: ProductEntity)
    // suspend fun updateProducts(product: ProductEntity)
    // suspend fun deleteProducts(product: ProductEntity)
    // fun getAllOfflineProducts(): Flow<List<ProductEntity>>
}