package com.rozworks.features.presentation.register

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.register.composable.RegisterRoute
import javax.inject.Inject

class RegisterNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(builder: NavGraphBuilder) {
        builder.composable(NavigationDestination.Register.route) {
            RegisterRoute()
        }
    }
}
