package com.rozworks.core.navigation

sealed class NavigationDestination(
    val route: String,
) {
    data object Login : NavigationDestination("loginDestination")
    data object Register : NavigationDestination("registerDestination")
    data object GetStarted : NavigationDestination("getStartedDestination")
    data object Back : NavigationDestination("navigationBack")
}
