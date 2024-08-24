package com.openplay.tech.myapplication.MainNavGraphs

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.openplay.tech.myapplication.presentation.screens.HomeScreen
import com.openplay.tech.myapplication.presentation.screens.CartScreen
import com.openplay.tech.myapplication.presentation.screens.ProfileScreen
import com.openplay.tech.myapplication.presentation.screens.SearchScreen

@Composable
fun NavigationGraph(
    navController: NavHostController,
    contentPaddingValues: PaddingValues
) {
    NavHost(navController = navController, startDestination = Route.HomeScreen.screen) {

        composable(route = Route.HomeScreen.screen) {
            HomeScreen(contentPaddingValues = contentPaddingValues)
        }
        composable(route = Route.SearchScreen.screen) {
            SearchScreen(contentPaddingValues = contentPaddingValues)
        }
        composable(route = Route.WatchListScreen.screen) {
            CartScreen()
        }
        composable(route = Route.ProfileScreen.screen) {
            ProfileScreen()
        }
    }
}
