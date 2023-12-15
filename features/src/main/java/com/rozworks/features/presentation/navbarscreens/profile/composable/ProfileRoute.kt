package com.rozworks.features.presentation.navbarscreens.profile.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rozworks.features.presentation.core.FoodsterTopBar
import com.rozworks.features.presentation.home.MyListsIntent
import com.rozworks.features.presentation.home.MyListsUiState
import com.rozworks.features.presentation.navbarscreens.mylists.MyListsViewModel

@Composable
fun ProfileRoute(
    viewModel: MyListsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    ProfileScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
internal fun ProfileScreen(
    uiState: MyListsUiState,
    onIntent: (MyListsIntent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
            }
        })
}