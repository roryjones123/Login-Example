package com.rozworks.features.presentation.core

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.outlined.AccountBox
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.rozworks.core.navigation.NavigationDestination

@Composable
fun FoodsterBottomNav(navController: NavController) {
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentLocation = currentBackStackEntry?.destination?.route

    val items = listOf(Screen.Home, Screen.MyList, Screen.Search, Screen.Profile)
    NavigationBar {
        items.forEach { screen ->
            val icon = if (currentLocation == screen.route) {
                screen.iconFilled
            } else {
                screen.iconUnfilled
            }

            NavigationBarItem(
                label = { Text(screen.label) },
                icon = { Icon(icon, "Nav bar icon.") },
                selected = currentLocation == screen.route,
                onClick = {
                    navController.navigate(screen.route)
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = MaterialTheme.colorScheme.tertiary,
                    unselectedIconColor = MaterialTheme.colorScheme.inverseSurface.copy(alpha = 0.5f),
                    selectedIconColor = MaterialTheme.colorScheme.inverseSurface
                )
            )
        }
    }
}

sealed class Screen(val route: String, val label: String, val iconFilled: ImageVector, val iconUnfilled: ImageVector) {
    data object Home : Screen(NavigationDestination.Home.route, "Home", Icons.Default.Home, Icons.Outlined.Home)
    data object Search :
        Screen(NavigationDestination.Search.route, "Search", Icons.Default.Search, Icons.Outlined.Search)

    data object MyList : Screen(
        NavigationDestination.MyList.route, "My Lists", Icons.Default.Favorite, Icons.Outlined.FavoriteBorder
    )

    data object Profile :
        Screen(NavigationDestination.Profile.route, "Profile", Icons.Default.AccountBox, Icons.Outlined.AccountBox)
}