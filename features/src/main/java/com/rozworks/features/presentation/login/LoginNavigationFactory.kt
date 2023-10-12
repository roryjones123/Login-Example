package com.rozworks.features.presentation.login

import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination.Login
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.login.composable.LoginRoute
import javax.inject.Inject

class LoginNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(builder: NavGraphBuilder) {
        builder.composable(Login.route) {
            LoginRoute()
        }
    }
}
