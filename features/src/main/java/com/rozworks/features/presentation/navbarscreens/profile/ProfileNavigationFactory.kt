package com.rozworks.features.presentation.home

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.navbarscreens.home.composable.HomeRoute
import com.rozworks.features.presentation.navbarscreens.profile.composable.ProfileRoute
import javax.inject.Inject

class ProfileNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(NavigationDestination.Profile.route) {
            showTopBar?.value = true
            showBottomNav?.value = true
            ProfileRoute()
        }
    }
}
