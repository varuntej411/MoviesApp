package com.openplay.tech.myapplication.presentation.uistate

import com.openplay.tech.myapplication.domain.model.MoviesModel

data class MoviesListUiState(
    val isLoading: Boolean = false,
    val products: List<MoviesModel> = emptyList(),
    val error: String = ""
)