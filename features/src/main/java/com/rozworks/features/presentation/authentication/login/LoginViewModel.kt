package com.rozworks.features.presentation.authentication.login

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.SavedStateHandle
import com.rozworks.core.BaseViewModel
import com.rozworks.core.navigation.NavigationManager
import com.rozworks.features.domain.usecase.ForgotPasswordUseCase
import com.rozworks.features.domain.usecase.LoginUseCase
import com.rozworks.features.domain.usecase.ResetPasswordUseCase
import com.rozworks.features.domain.usecase.VerifyCodeUseCase
import com.rozworks.features.presentation.NavigationCommands
import com.rozworks.features.presentation.NavigationCommands.registerCommand
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject
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
) : BaseViewModel<LoginUiState, LoginUiState.PartialState, LoginIntent>(
    savedStateHandle,
    loginInitialState,
) {
    val username = mutableStateOf(TextFieldValue())
    val password = mutableStateOf(TextFieldValue())
    override fun mapIntents(intent: LoginIntent): Flow<LoginUiState.PartialState> = when (intent) {
        is LoginIntent.AttemptLogin -> attemptLogin(intent.username, intent.password)
        is LoginIntent.RegisterClicked -> navigateToRegister()
        is LoginIntent.AttemptResetPassword -> resetPassword(intent.password, intent.token)
        is LoginIntent.ForgotPasswordInput -> forgotPassword(intent.email)
        is LoginIntent.VerifyCode -> verifyCode(intent.code, intent.email)
    }

    override fun reduceUiState(
        previousState: LoginUiState,
        partialState: LoginUiState.PartialState,
    ): LoginUiState = when (partialState) {
        is LoginUiState.PartialState.Loading -> previousState.copy(
            isLoading = true,
            isError = false
        )

        is LoginUiState.PartialState.LoginError -> previousState.copy(
            isLoading = false,
            isError = true,
            errorMessage = partialState.message,
            isLoginSuccess = false
        )

        is LoginUiState.PartialState.LoginSuccess -> previousState.copy(
            isLoading = false,
            isError = false,
            isLoginSuccess = true
        )

        is LoginUiState.PartialState.Register -> previousState

        is LoginUiState.PartialState.ForgotEmailEntered -> previousState.copy(
            forgotEmailEntered = true,
            isLoading = false,
            forgottenPasswordEmail = partialState.emailEntered,
            isLoginSuccess = false
        )

        is LoginUiState.PartialState.CodeVerifySuccess -> previousState.copy(
            isLoading = false,
            forgotEmailCodeEntered = true,
            forgotEmailEntered = false,
            resetPasswordToken = partialState.token,
            isLoginSuccess = false
        )

        LoginUiState.PartialState.PasswordResetSuccess -> previousState.copy(
            isLoading = false,
            forgotEmailEntered = false,
            forgotEmailCodeEntered = false,
            isLoginSuccess = false
        )

        is LoginUiState.PartialState.VerifyCodeError -> previousState.copy(verifyCodeFailed = true)
    }

    private fun navigateToRegister(): Flow<LoginUiState.PartialState> {
        navigationManager.navigate(registerCommand)
        return flowOf(LoginUiState.PartialState.Register)
    }

    private fun attemptLogin(username: String, password: String): Flow<LoginUiState.PartialState> =
        loginUseCase(username, password).map { response ->
            response.fold(
                onSuccess = { _ ->
                    navigationManager.navigate(NavigationCommands.homeCommand)
                    LoginUiState.PartialState.LoginSuccess
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is LoginUseCase.LoginError.ConnectionError -> {
                            LoginUiState.PartialState.LoginError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            LoginUiState.PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(LoginUiState.PartialState.Loading)
        }

    private fun forgotPassword(email: String): Flow<LoginUiState.PartialState> =
        forgotPasswordUseCase(email).map { response ->
            response.fold(
                onSuccess = { _ ->
                    LoginUiState.PartialState.ForgotEmailEntered(email)
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is ForgotPasswordUseCase.ForgotPasswordError.ConnectionError -> {
                            LoginUiState.PartialState.ForgotEmailEntered(email)
                        }

                        else -> {
                            LoginUiState.PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(LoginUiState.PartialState.Loading)
        }

    private fun verifyCode(code: String, email: String): Flow<LoginUiState.PartialState> =
        verifyCodeUseCase(code, email).map { response ->
            response.fold(
                onSuccess = { token ->
                    LoginUiState.PartialState.CodeVerifySuccess(token)
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is VerifyCodeUseCase.VerifyCodeError.ConnectionError -> {
                            LoginUiState.PartialState.VerifyCodeError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            LoginUiState.PartialState.VerifyCodeError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(LoginUiState.PartialState.Loading)
        }

    private fun resetPassword(newPassword: String, token: String): Flow<LoginUiState.PartialState> =
        resetPasswordUseCase(newPassword, token).map { response ->
            response.fold(
                onSuccess = { _ ->
                    LoginUiState.PartialState.PasswordResetSuccess
                },
                onFailure = { throwable ->
                    when (throwable) {
                        is LoginUseCase.LoginError.ConnectionError -> {
                            LoginUiState.PartialState.LoginError(throwable.message ?: "Something went wrong!")
                        }

                        else -> {
                            LoginUiState.PartialState.LoginError("Something went wrong!")
                        }
                    }
                }
            )
        }.onStart {
            emit(LoginUiState.PartialState.Loading)
        }
}