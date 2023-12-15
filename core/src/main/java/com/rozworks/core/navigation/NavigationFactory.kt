package com.rozworks.core.navigation

import androidx.compose.runtime.MutableState
import androidx.navigation.NavGraphBuilder

interface NavigationFactory {
    fun create(
        builder: NavGraphBuilder,
        showBottomNav: MutableState<Boolean>? = null,
        showTopBar: MutableState<Boolean>? = null
    )
}
