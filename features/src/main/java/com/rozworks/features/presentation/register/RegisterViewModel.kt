package com.rozworks.features.presentation.register

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.domain.usecase.RegisterUseCase
import com.rozworks.features.presentation.NavigationCommands.loginCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import com.rozworks.features.presentation.register.RegisterUiState.PartialState
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val registerUseCase: RegisterUseCase,
    savedStateHandle: SavedStateHandle,
    registerInitialState: RegisterUiState,
) : BaseViewModel<RegisterUiState, PartialState, RegisterIntent>(
    savedStateHandle,
    registerInitialState,
) {
    val username = mutableStateOf(TextFieldValue())
    val password = mutableStateOf(TextFieldValue())
    val email = mutableStateOf(TextFieldValue())
    override fun mapIntents(intent: RegisterIntent): Flow<PartialState> = when (intent) {
        is RegisterIntent.AttemptRegister -> attemptRegister(intent.username, intent.password, intent.email)
        is RegisterIntent.SignInPress -> navigateToLogin()
    }

    override fun reduceUiState(
        previousState: RegisterUiState,
        partialState: PartialState,
    ): RegisterUiState = when (partialState) {
        is PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )

        is PartialState.ServerError -> previousState.copy(
            isLoading = false,
            isError = true,
            errorMessage = partialState.message
        )

        is PartialState.Success -> previousState.copy(
            isLoading = false,
            isError = false,
            isRegisterSuccess = true
        )

        PartialState.Login -> previousState
    }

    private fun attemptRegister(username: String, password: String, email: String): Flow<PartialState> =
        registerUseCase(username, password, email).map { response ->
            response.fold(
                onSuccess = { _ ->
                    PartialState.Success
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is RegisterUseCase.RegisterError.ConnectionError -> {
                            PartialState.ServerError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            PartialState.ServerError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }

    private fun navigateToLogin(): Flow<PartialState> {
        navigationManager.navigate(loginCommand)
        return flowOf(PartialState.Login)
    }
}