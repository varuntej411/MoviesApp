package com.openplay.tech.myapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.commonutils.Constants
import com.openplay.tech.myapplication.commonutils.UiText
import com.openplay.tech.myapplication.domain.model.MoviesDetailsModel
import com.openplay.tech.myapplication.domain.usecases.GetAllMoviesDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesDetailsUi.UiState
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesDetailsUi.Event
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class MoviesDetailsViewModel @Inject constructor(
    private val getAllMoviesDetailsUseCase: GetAllMoviesDetailsUseCase,
) : ViewModel() {

    private val _uiStates = MutableStateFlow(UiState())
    val uiStates: StateFlow<UiState> = _uiStates.asStateFlow()

    private var currentPage = 0

    init {
        getAllMovieDetails()
    }

    fun onEvent(event: Event) {
        when (event) {
            is Event.FetchAllMovies -> {
                currentPage++
                getAllMovieDetails()
            }
        }
    }


    private fun getAllMovieDetails() = getAllMoviesDetailsUseCase.invoke(
        limit = currentPage,
        offset = currentPage,
        api_key = Constants.API_KEY
    ).onEach { result ->

        when (result) {
            is APIDataStatus.LOADING -> {
                _uiStates.update {
                    UiState(isLoading = true)
                }
            }

            is APIDataStatus.SUCCESS -> {
                _uiStates.update {
                    UiState(detailsModels = result.data)
                }
            }

            is APIDataStatus.ERROR -> {
                _uiStates.update {
                    UiState(
                        error = UiText.RemoteString(
                            message = result.message ?: "Error Occured"
                        )
                    )
                }
            }
        }
    }.launchIn(viewModelScope)

}


object MoviesDetailsUi {

    data class UiState(
        val isLoading: Boolean = false,
        val detailsModels: MoviesDetailsModel? = null,
        val error: UiText? = null,
    )


    sealed class Event {
        data class FetchAllMovies(val title: String) : Event()
    }

}