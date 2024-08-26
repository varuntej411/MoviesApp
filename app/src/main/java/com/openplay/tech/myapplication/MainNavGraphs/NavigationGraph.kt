package com.openplay.tech.myapplication.MainNavGraphs

import android.util.Log
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.openplay.tech.myapplication.data.local.MoviesEntity
import com.openplay.tech.myapplication.domain.model.MoviesModel
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesList
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesViewModel
import com.openplay.tech.myapplication.presentation.screens.HomeScreen
import com.openplay.tech.myapplication.presentation.screens.CartScreen
import com.openplay.tech.myapplication.presentation.screens.MoreDetailsScreen
import com.openplay.tech.myapplication.presentation.screens.ProfileScreen
import com.openplay.tech.myapplication.presentation.screens.SearchScreen
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesDetailsUi
import com.openplay.tech.myapplication.presentation.viewmodels.MoviesDetailsViewModel

@Composable
fun NavigationGraph(
    navController: NavHostController,
    contentPaddingValues: PaddingValues,
) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.screen) {
        composable(route = Route.HomeScreen.screen) {
            val moviesViewModel = hiltViewModel<MoviesViewModel>()

            HomeScreen(navController = navController, contentPaddingValues = contentPaddingValues,
                wishListClicked = {
                    moviesViewModel.onEvent(MoviesList.Event.insertAllMoviesDb(it))
                    Log.d("saveddata", "${it}")
                },
                unWishListClicked = {
                    moviesViewModel.onEvent(MoviesList.Event.deleteMoviesDb(it))
                    Log.d("deleteddata", "${it}")
                })
        }
        composable(route = Route.SearchScreen.screen) {
            SearchScreen(contentPaddingValues = contentPaddingValues)
        }
        composable(route = Route.WatchListScreen.screen) {
            CartScreen(
                navController= navController,
                contentPaddingValues = contentPaddingValues
            )
        }
        composable(route = Route.ProfileScreen.screen) {
            ProfileScreen()
        }
        composable(route = Route.MoreDetailsScreen.screen) {
            val moviesDetailsViewModel = hiltViewModel<MoviesDetailsViewModel>()
            val title = it.arguments?.getString("title")
            LaunchedEffect(key1 = title) {
                title?.let {
                    moviesDetailsViewModel.onEvent(MoviesDetailsUi.Event.FetchAllMovies(it))
                }
            }
            MoreDetailsScreen(
                navController = navController,
                contentPaddingValues = contentPaddingValues
            )
        }
    }
}
