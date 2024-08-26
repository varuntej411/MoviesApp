package com.openplay.tech.myapplication.MainNavGraphs

sealed class Route(val screen: String) {
//    data object MainScreenNavigation: Route(screen = "main_screen")
    data object HomeScreen: Route(screen = "home_screen")
    data object SearchScreen: Route(screen = "search_screen")
    data object WatchListScreen: Route(screen = "watchlist_screen")
    data object ProfileScreen: Route(screen = "profile_screen")
    data object MoreDetailsScreen: Route(screen = "moredetails_screen")
}