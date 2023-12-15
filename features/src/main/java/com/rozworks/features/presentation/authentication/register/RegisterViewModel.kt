package com.rozworks.features.presentation.authentication.register

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
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class RegisterViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val registerUseCase: RegisterUseCase,
    savedStateHandle: SavedStateHandle,
    registerInitialState: RegisterUiState,
) : BaseViewModel<RegisterUiState, RegisterUiState.PartialState, RegisterIntent>(
    savedStateHandle,
    registerInitialState,
) {
    val username = mutableStateOf(TextFieldValue())
    val password = mutableStateOf(TextFieldValue())
    val email = mutableStateOf(TextFieldValue())
    override fun mapIntents(intent: RegisterIntent): Flow<RegisterUiState.PartialState> = when (intent) {
        is RegisterIntent.AttemptRegister -> attemptRegister(intent.username, intent.password, intent.email)
        is RegisterIntent.SignInPress -> navigateToLogin()
    }

    override fun reduceUiState(
        previousState: RegisterUiState,
        partialState: RegisterUiState.PartialState,
    ): RegisterUiState = when (partialState) {
        is RegisterUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )

        is RegisterUiState.PartialState.ServerError -> previousState.copy(
            isLoading = false,
            isError = true,
            errorMessage = partialState.message,
            isRegisterSuccess = false
        )

        is RegisterUiState.PartialState.Success -> previousState.copy(
            isLoading = false,
            isError = false,
            isRegisterSuccess = true,
            errorMessage = null
        )

        RegisterUiState.PartialState.Login -> previousState
    }

    private fun attemptRegister(username: String, password: String, email: String): Flow<RegisterUiState.PartialState> =
        registerUseCase(username, password, email).map { response ->
            response.fold(
                onSuccess = { _ ->
                    RegisterUiState.PartialState.Success
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is RegisterUseCase.RegisterError.ConnectionError -> {
                            RegisterUiState.PartialState.ServerError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            RegisterUiState.PartialState.ServerError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(RegisterUiState.PartialState.Loading)
        }

    private fun navigateToLogin(): Flow<RegisterUiState.PartialState> {
        navigationManager.navigate(loginCommand)
        return flowOf(RegisterUiState.PartialState.Login)
    }
}