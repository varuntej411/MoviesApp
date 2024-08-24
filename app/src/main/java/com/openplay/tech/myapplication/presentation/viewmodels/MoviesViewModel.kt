package com.food.store.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.domain.usecases.GetAllMoviesUseCase
import com.openplay.tech.myapplication.presentation.uistate.MoviesListUiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class ProductViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase
) : ViewModel() {

    private val _states = MutableStateFlow(MoviesListUiState())
    val states: StateFlow<MoviesListUiState> = _states.asStateFlow()

    // we can use *mutable-state-of* but it cannot handle large data like mutable-stateflow
    // private val _state = mutableStateOf(ProductListState())
    // val state: State<ProductListState> = _state

    init {
        getAllProducts()
    }

    fun onEvent(event: Event) {
        when(event) {
            is Event.FetchAllMovies -> {
                getAllProducts()
            }
        }
    }

    private fun getAllProducts() {
        getAllMoviesUseCase().onEach { result ->
            when (result) {
                is APIDataStatus.LOADING -> {
                    _states.update {
                        MoviesListUiState(isLoading = true)
                    }
                }

                is APIDataStatus.SUCCESS -> {
                    _states.value = MoviesListUiState(products = result.data ?: emptyList())
                }

                is APIDataStatus.ERROR -> {
                    _states.value = MoviesListUiState(error = result.message ?: "An Unexpected Error Occurred")
                }
            }
        }.launchIn(viewModelScope)
    }
}

sealed interface Event {
    data class FetchAllMovies(val id: String) : Event
}

sealed interface Navigation {

}
