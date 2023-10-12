package com.rozworks.features.presentation.getstarted

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.getstarted.composable.GetStartedRoute
import javax.inject.Inject

class GetStartedNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.GetStarted.route) {
            GetStartedRoute()
        }
    }
}
