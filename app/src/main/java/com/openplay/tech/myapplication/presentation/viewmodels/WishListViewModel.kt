package com.openplay.tech.myapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openplay.tech.myapplication.commonutils.UiText
import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.usecases.DbUseCases.GetAllDbMoviesUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WishListViewModel @Inject constructor(private val getAllDbMoviesUseCases: GetAllDbMoviesUseCases) :
    ViewModel() {

    private val _uiState = MutableStateFlow(WishListScreen.UiState())
    val uiState: StateFlow<WishListScreen.UiState> = _uiState.asStateFlow()


    init {
        fetchMovies()
    }

    private fun fetchMovies() = viewModelScope.launch {
        getAllDbMoviesUseCases.invoke().collectLatest { list ->
            _uiState.update {
                WishListScreen.UiState(data = list)
            }
        }
    }
}

object WishListScreen {

    data class UiState(
        val isLoading: Boolean = false,
        val error: UiText = UiText.Idle,
        val data: List<MoviesEntity>? = null,
    )


}