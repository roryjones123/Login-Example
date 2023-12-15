package com.rozworks.features.presentation.authentication.login

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.rozworks.core.navigation.NavigationDestination.Login
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.features.presentation.authentication.login.composable.LoginRoute
import javax.inject.Inject

class LoginNavigationFactory @Inject constructor() : NavigationFactory {
    override fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>?,
        showTopBar: MutableState<Boolean>?
    ) {
        builder.composable(Login.route) {
            LoginRoute()
        }
    }
}
