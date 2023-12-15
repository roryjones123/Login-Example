package com.rozworks.features.presentation.authentication.getstarted

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.authentication.getstarted.composable.GetStartedRoute
import javax.inject.Inject

class GetStartedNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(NavigationDestination.GetStarted.route) {
            GetStartedRoute()
        }
    }
}
