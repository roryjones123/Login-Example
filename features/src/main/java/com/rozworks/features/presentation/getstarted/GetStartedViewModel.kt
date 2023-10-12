package com.rozworks.features.presentation.getstarted

import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.presentation.NavigationCommands.loginCommand
import com.rozworks.features.presentation.NavigationCommands.registerCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject
import com.rozworks.features.presentation.getstarted.GetStartedUiState.PartialState
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class GetStartedViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    savedStateHandle: SavedStateHandle,
    loginInitialState: GetStartedUiState,
) : BaseViewModel<GetStartedUiState, PartialState, GetStartedIntent>(
    savedStateHandle,
    loginInitialState,
) {
    override fun mapIntents(intent: GetStartedIntent): Flow<PartialState> = when (intent) {
        is GetStartedIntent.RegisterClick -> navigateToRegister()
        is GetStartedIntent.LoginClick -> navigateToLogin()
    }

    override fun reduceUiState(
        previousState: GetStartedUiState,
        partialState: PartialState,
    ): GetStartedUiState = when (partialState) {
        is PartialState.Register -> previousState
        is PartialState.Login -> previousState
    }

    private fun navigateToLogin(): Flow<PartialState> {
        navigationManager.navigate(loginCommand)
        return flowOf(PartialState.Login)
    }

    private fun navigateToRegister(): Flow<PartialState> {
        navigationManager.navigate(registerCommand)
        return flowOf(PartialState.Register)
    }
}