package com.rozworks.features.presentation.navbarscreens.home

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.navbarscreens.home.composable.HomeRoute
import javax.inject.Inject

class HomeNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(NavigationDestination.Home.route) {
            showTopBar?.value = true
            showBottomNav?.value = true
            HomeRoute()
        }
    }
}
