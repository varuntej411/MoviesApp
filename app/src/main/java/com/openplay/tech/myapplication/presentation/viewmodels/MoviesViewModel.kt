package com.openplay.tech.myapplication.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.openplay.tech.myapplication.commonutils.APIDataStatus
import com.openplay.tech.myapplication.commonutils.Constants
import com.openplay.tech.myapplication.commonutils.UiText
import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.domain.usecases.DbUseCases.DeleteAllMoviesUseCases
import com.openplay.tech.myapplication.domain.usecases.DbUseCases.GetAllDbMoviesUseCases
import com.openplay.tech.myapplication.domain.usecases.DbUseCases.InsertAllMoviesUseCases
import com.openplay.tech.myapplication.domain.usecases.GetAllMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    private val getAllMoviesUseCase: GetAllMoviesUseCase,
    private val insertAllMoviesUseCases: InsertAllMoviesUseCases,
    private val deleteAllMoviesUseCases: DeleteAllMoviesUseCases
) : ViewModel() {

    private val _uiStates = MutableStateFlow(MoviesList.UiState())
    var uiStates: StateFlow<MoviesList.UiState> = _uiStates.asStateFlow()

    private var currentPage = 0

    init {
        getAllMovies()
    }

    fun onEvent(event: MoviesList.Event) {
        when (event) {
            is MoviesList.Event.GetAllMovies -> {
                getAllMovies()
            }

            is MoviesList.Event.insertAllMoviesDb -> {
                insertAllMoviesUseCases.invoke(event.moviesModel.toMoviesEntity())
                    .launchIn(viewModelScope)
            }

            is MoviesList.Event.deleteMoviesDb -> {
                deleteAllMoviesUseCases.invoke(event.moviesModel.toMoviesEntity())
                    .launchIn(viewModelScope)
            }

            else -> {}
        }
    }

    private fun getAllMovies() = getAllMoviesUseCase.invoke(
        limit = currentPage, offset = currentPage, apiKey = Constants.API_KEY
    ).onEach { result ->
        when (result) {
            is APIDataStatus.LOADING -> {
                _uiStates.update {
                    MoviesList.UiState(
                        isLoading = true
                    )
                }
            }

            is APIDataStatus.SUCCESS -> {
                _uiStates.update {
                    MoviesList.UiState(data = result.data)
                }
            }

            is APIDataStatus.ERROR -> {
                _uiStates.update {
                    MoviesList.UiState(
                        error = UiText.RemoteString(
                            result.message ?: "An Unexpected Error Occurred"
                        )
                    )
                }
            }
        }
    }.launchIn(viewModelScope)


    private fun MoviesModel.toMoviesEntity(): MoviesEntity {
        return MoviesEntity(
            id = id,
            adult = adult,
            backdrop_path = backdrop_path,
            original_language = original_language,
            original_title = original_title,
            overview = overview,
            popularity = popularity,
            poster_path = poster_path,
            release_date = release_date,
            title = title,
            video = video,
            vote_average = vote_average,
            vote_count = vote_count
        )
    }

}

object MoviesList {

    data class UiState(
        val isLoading: Boolean = false,
        val data: List<MoviesModel>? = null,
        val error: UiText = UiText.Idle,
    )

    sealed interface Navigation {
        data class GoToMoviesDetailsScreen(val limit: Int, val offset: Int, val api_key: String) :
            Navigation
    }

    sealed interface Event {
        data class GetAllMovies(val limit: Int, val offset: Int, val api_key: String) : Event
        data class insertAllMoviesDb(val moviesModel: MoviesModel) : Event
        data class deleteMoviesDb(val moviesModel: MoviesModel) : Event
    }

}