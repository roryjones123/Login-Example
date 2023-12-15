package com.rozworks.core.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun NavigationHost(
    navController: NavHostController,
    factories: Set<NavigationFactory>,
    modifier: Modifier = Modifier,
    startingDestination: String,
    showBottomNav: MutableState<Boolean>,
    showTopBar: MutableState<Boolean>
) {
    NavHost(
        navController = navController,
        startDestination = startingDestination,
        modifier = modifier
    ) {
        factories.forEach {
            it.create(this, showBottomNav, showTopBar)
        }
    }
}
