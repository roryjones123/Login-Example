package com.rozworks.loginexample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.NavOptions
import androidx.navigation.compose.rememberNavController
import com.rozworks.core.SecureStorageDataSource
import com.rozworks.core.design.TemplateTheme
import com.rozworks.core.navigation.NavigationDestination
import com.rozworks.core.navigation.NavigationFactory
import com.rozworks.core.navigation.NavigationHost
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.core.utils.collectWithLifecycle
import com.rozworks.features.presentation.core.FoodsterBottomNav
import com.rozworks.features.presentation.core.FoodsterTopBar
import com.rozworks.loginexample.core.R
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navigationFactories: @JvmSuppressWildcards Set<NavigationFactory>

    @Inject
    lateinit var navigationManager: NavigationManager

    @Inject
    lateinit var secureStorageDataSource: SecureStorageDataSource


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val startingDestination = if (secureStorageDataSource.getToken() == null) {
            NavigationDestination.GetStarted.route
        } else {
            NavigationDestination.Home.route
        }

        setContent {
            TemplateTheme {
                val navController = rememberNavController()
                val showBottomBar = remember { mutableStateOf(false) }
                val showTopBar = remember { mutableStateOf(false) }

                Scaffold(
                    bottomBar = {
                        if (showBottomBar.value) {
                            FoodsterBottomNav(navController = navController)
                        }
                    },
                    topBar = {
                        if (showTopBar.value) {
                            FoodsterTopBar(navController = navController)
                        }
                    }
                ) {
                    NavigationHost(
                        modifier = Modifier
                            .padding(it),
                        navController = navController,
                        factories = navigationFactories,
                        startingDestination = startingDestination,
                        showBottomNav = showBottomBar,
                        showTopBar = showTopBar
                    )
                }

                navigationManager
                    .navigationEvent
                    .collectWithLifecycle(
                        key = navController,
                    ) {
                        when (it.destination) {
                            NavigationDestination.Back.route -> navController.navigateUp()
                            else -> navController.navigate(
                                it.destination, NavOptions.Builder()
                                    .setEnterAnim(R.anim.slide_in_right)
                                    .setExitAnim(R.anim.slide_out_left)
                                    .setPopEnterAnim(R.anim.slide_in_left)
                                    .setPopExitAnim(R.anim.slide_out_right)
                                    .build()
                            )
                        }
                    }
            }
        }
    }
}
