package com.rozworks.features.presentation.getstarted.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rozworks.features.presentation.getstarted.GetStartedIntent
import com.rozworks.features.presentation.getstarted.GetStartedViewModel

@Composable
fun GetStartedRoute(
    viewModel: GetStartedViewModel = hiltViewModel(),
) {
    GetStartedScreen(
        onIntent = viewModel::acceptIntent
    )
}

@Composable
internal fun GetStartedScreen(
    onIntent: (GetStartedIntent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AuthenticationFlowBackground()
                GetStartedContent(
                    loginClicked = { onIntent(GetStartedIntent.LoginClick) },
                    registerClicked = { onIntent(GetStartedIntent.RegisterClick) })
            }
        })
}