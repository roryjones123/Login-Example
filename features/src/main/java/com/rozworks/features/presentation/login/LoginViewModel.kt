package com.rozworks.features.presentation.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.domain.usecase.ForgotPasswordUseCase
import com.rozworks.features.domain.usecase.LoginUseCase
import com.rozworks.features.domain.usecase.ResetPasswordUseCase
import com.rozworks.features.domain.usecase.VerifyCodeUseCase
import com.rozworks.features.presentation.NavigationCommands.registerCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
import com.rozworks.features.presentation.login.LoginUiState.PartialState
import kotlinx.coroutines.flow.flowOf

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val navigationManager: NavigationManager,
    private val loginUseCase: LoginUseCase,
    private val forgotPasswordUseCase: ForgotPasswordUseCase,
    private val resetPasswordUseCase: ResetPasswordUseCase,
    private val verifyCodeUseCase: VerifyCodeUseCase,
    savedStateHandle: SavedStateHandle,
    loginInitialState: LoginUiState,
) : BaseViewModel<LoginUiState, PartialState, LoginIntent>(
    savedStateHandle,
    loginInitialState,
) {
    val username = mutableStateOf(TextFieldValue())
    val password = mutableStateOf(TextFieldValue())
    override fun mapIntents(intent: LoginIntent): Flow<PartialState> = when (intent) {
        is LoginIntent.AttemptLogin -> attemptLogin(intent.username, intent.password)
        is LoginIntent.RegisterClicked -> navigateToRegister()
        is LoginIntent.AttemptResetPassword -> resetPassword(intent.password, intent.token)
        is LoginIntent.ForgotPasswordInput -> forgotPassword(intent.email)
        is LoginIntent.VerifyCode -> verifyCode(intent.code, intent.email)
    }

    override fun reduceUiState(
        previousState: LoginUiState,
        partialState: PartialState,
    ): LoginUiState = when (partialState) {
        is PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )

        is PartialState.LoginError -> previousState.copy(
            isLoading = false,
            isError = true,
            errorMessage = partialState.message
        )

        is PartialState.LoginSuccess -> previousState.copy(
            isLoading = false,
            isError = false,
            isLoginSuccess = true
        )

        is PartialState.Register -> previousState

        is PartialState.ForgotEmailEntered -> previousState.copy(
            forgotEmailEntered = true,
            isLoading = false,
            forgottenPasswordEmail = partialState.emailEntered
        )

        is PartialState.CodeVerifySuccess -> previousState.copy(
            isLoading = false,
            forgotEmailCodeEntered = true,
            forgotEmailEntered = false,
            resetPasswordToken = partialState.token
        )

        PartialState.PasswordResetSuccess -> previousState.copy(
            isLoading = false,
            forgotEmailEntered = false,
            forgotEmailCodeEntered = false
        )

        is PartialState.VerifyCodeError -> previousState.copy(verifyCodeFailed = true)
    }

    private fun navigateToRegister(): Flow<PartialState> {
        navigationManager.navigate(registerCommand)
        return flowOf(PartialState.Register)
    }

    private fun attemptLogin(username: String, password: String): Flow<PartialState> =
        loginUseCase(username, password).map { response ->
            response.fold(
                onSuccess = { _ ->
                    PartialState.LoginSuccess
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is LoginUseCase.LoginError.ConnectionError -> {
                            PartialState.LoginError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }

    private fun forgotPassword(email: String): Flow<PartialState> =
        forgotPasswordUseCase(email).map { response ->
            response.fold(
                onSuccess = { _ ->
                    PartialState.ForgotEmailEntered(email)
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is ForgotPasswordUseCase.ForgotPasswordError.ConnectionError -> {
                            PartialState.ForgotEmailEntered(email)
                        }

                        else -> {
                            PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }

    private fun verifyCode(code: String, email: String): Flow<PartialState> =
        verifyCodeUseCase(code, email).map { response ->
            response.fold(
                onSuccess = { token ->
                    PartialState.CodeVerifySuccess(token)
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is VerifyCodeUseCase.VerifyCodeError.ConnectionError -> {
                            PartialState.VerifyCodeError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            PartialState.VerifyCodeError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }

    private fun resetPassword(newPassword: String, token: String): Flow<PartialState> =
        resetPasswordUseCase(newPassword, token).map { response ->
            response.fold(
                onSuccess = { _ ->
                    PartialState.PasswordResetSuccess
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is LoginUseCase.LoginError.ConnectionError -> {
                            PartialState.LoginError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(PartialState.Loading)
        }
}