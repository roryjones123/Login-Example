package com.rozworks.core.navigation

sealed class NavigationDestination(
    val route: String,
) {
    data object Login : NavigationDestination("loginDestination")
    data object Register : NavigationDestination("registerDestination")
    data object GetStarted : NavigationDestination("getStartedDestination")
    data object Home : NavigationDestination("homeDestination")
    data object MyList : NavigationDestination("myListDestination")
    data object Search : NavigationDestination("searchDestination")
    data object Profile : NavigationDestination("profileDestination")
    data object Back : NavigationDestination("navigationBack")
}
