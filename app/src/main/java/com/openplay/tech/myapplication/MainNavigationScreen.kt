package com.openplay.tech.myapplication

import android.annotation.SuppressLint
import android.content.Context
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MediumTopAppBar
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.openplay.tech.myapplication.MainNavGraphs.BottomNavigationBar
import com.openplay.tech.myapplication.MainNavGraphs.NavigationGraph
import com.openplay.tech.myapplication.MainNavGraphs.Route
import com.openplay.tech.myapplication.MainNavGraphs.bottomBarItems
import com.openplay.tech.myapplication.commonutils.SubImageLoader
import com.openplay.tech.myapplication.presentation.components.CustomToolBar

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter", "RememberReturnType")
@Composable
fun MainNavigationScreen(navController: NavHostController) {

    val context = LocalContext.current
    val navBottomItems = bottomBarItems
    val backStackState = navController.currentBackStackEntryAsState().value
    var selectedItem by rememberSaveable {
        mutableIntStateOf(0)
    }

    selectedItem = when (backStackState?.destination?.route) {
        Route.HomeScreen.screen -> 0
        Route.SearchScreen.screen -> 1
        Route.WatchListScreen.screen -> 2
        Route.ProfileScreen.screen -> 3
        else -> 0
    }

    val isBottomBarVisible = remember(key1 = backStackState) {
        backStackState?.destination?.route == Route.HomeScreen.screen
                || backStackState?.destination?.route == Route.SearchScreen.screen
                || backStackState?.destination?.route == Route.WatchListScreen.screen
                || backStackState?.destination?.route == Route.ProfileScreen.screen
    }

    Scaffold(
        modifier = Modifier.fillMaxWidth(),
        topBar = {
            CustomToolBar()
        },
        bottomBar = {
            BottomNavigationBar(
                navItems = navBottomItems,
                selectedItem = selectedItem,
                onClickItem = { index ->
                    when (index) {
                        0 -> navigateToTab(
                            navController = navController,
                            route = Route.HomeScreen.screen
                        )

                        1 -> navigateToTab(
                            navController = navController,
                            route = Route.SearchScreen.screen
                        )

                        2 -> navigateToTab(
                            navController = navController,
                            route = Route.WatchListScreen.screen
                        )

                        3 -> navigateToTab(
                            navController = navController,
                            route = Route.ProfileScreen.screen
                        )
                    }
                }
            )
        }
    ) { innerPaddingValues ->
        NavigationGraph(navController = navController, contentPaddingValues = innerPaddingValues)
    }

}


@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.screen
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screenRoute ->
            navController.popBackStack(route = screenRoute, inclusive = true)
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController) {
    //   navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.HomeScreen.screen
    )
}

@Preview
@Composable
fun MainScreenPreview() {
    MainNavigationScreen(navController = rememberNavController())
}