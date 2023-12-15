package com.rozworks.features.presentation.home.composable

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
fun MyListsRoute(
    viewModel: MyListsViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    MyListsScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent
    )
}

@Composable
internal fun MyListsScreen(
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