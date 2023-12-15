package com.rozworks.features.presentation.navbarscreens.search

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.home.composable.SearchRoute
import javax.inject.Inject

class SearchNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(NavigationDestination.Search.route) {
            showTopBar?.value = true
            showBottomNav?.value = true
            SearchRoute()
        }
    }
}
