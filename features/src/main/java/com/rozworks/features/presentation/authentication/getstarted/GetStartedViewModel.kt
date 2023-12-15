package com.rozworks.features.presentation.authentication.getstarted

import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.presentation.NavigationCommands.loginCommand
import com.rozworks.features.presentation.NavigationCommands.registerCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class GetStartedViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    loginInitialState: GetStartedUiState,
) : BaseViewModel<GetStartedUiState, GetStartedUiState.PartialState, GetStartedIntent>(
    savedStateHandle,
    loginInitialState,
) {
    override fun mapIntents(intent: GetStartedIntent): Flow<GetStartedUiState.PartialState> = when (intent) {
        is GetStartedIntent.RegisterClick -> navigateToRegister()
        is GetStartedIntent.LoginClick -> navigateToLogin()
    }

    override fun reduceUiState(
        previousState: GetStartedUiState,
        partialState: GetStartedUiState.PartialState,
    ): GetStartedUiState = when (partialState) {
        is GetStartedUiState.PartialState.Register -> previousState
        is GetStartedUiState.PartialState.Login -> previousState
    }

    private fun navigateToLogin(): Flow<GetStartedUiState.PartialState> {
        navigationManager.navigate(loginCommand)
        return flowOf(GetStartedUiState.PartialState.Login)
    }

    private fun navigateToRegister(): Flow<GetStartedUiState.PartialState> {
        navigationManager.navigate(registerCommand)
        return flowOf(GetStartedUiState.PartialState.Register)
    }
}