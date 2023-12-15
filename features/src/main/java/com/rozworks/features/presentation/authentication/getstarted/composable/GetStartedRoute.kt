package com.rozworks.features.presentation.authentication.getstarted.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.rozworks.features.presentation.authentication.getstarted.GetStartedViewModel

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
    onIntent: (com.rozworks.features.presentation.authentication.getstarted.GetStartedIntent) -> Unit
) {
    val snackbarHostState = remember { SnackbarHostState() }
    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                AuthenticationFlowBackground()
                GetStartedContent(
                    loginClicked = { onIntent(com.rozworks.features.presentation.authentication.getstarted.GetStartedIntent.LoginClick) },
                    registerClicked = { onIntent(com.rozworks.features.presentation.authentication.getstarted.GetStartedIntent.RegisterClick) })
            }
        })
}