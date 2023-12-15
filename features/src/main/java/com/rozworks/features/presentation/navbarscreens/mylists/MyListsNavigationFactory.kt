package com.rozworks.features.presentation.home

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.navbarscreens.home.composable.HomeRoute
import com.rozworks.features.presentation.home.composable.MyListsRoute
import javax.inject.Inject

class MyListsNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(NavigationDestination.MyList.route) {
            showTopBar?.value = true
            showBottomNav?.value = true
            MyListsRoute()
        }
    }
}
