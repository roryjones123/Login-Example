package com.rozworks.features.presentation.authentication.login.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rozworks.features.presentation.authentication.getstarted.composable.AuthenticationFlowBackground
import com.rozworks.features.presentation.authentication.login.LoginIntent
import com.rozworks.features.presentation.authentication.login.LoginUiState
import com.rozworks.features.presentation.authentication.login.LoginViewModel
import com.rozworks.features.presentation.authentication.login.composable.modals.ChangePasswordCodeModal
import com.rozworks.features.presentation.authentication.login.composable.modals.ForgotPasswordEmailModal
import com.rozworks.features.presentation.authentication.login.composable.modals.VerifyResetCodeModal

@Composable
fun LoginRoute(
    viewModel: LoginViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val username = viewModel.username
    val password = viewModel.password

    LoginScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
        username = username,
        password = password
    )
}

@Composable
internal fun LoginScreen(
    uiState: LoginUiState,
    onIntent: (LoginIntent) -> Unit,
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>
) {
    val snackbarHostState = remember { SnackbarHostState() }

    val email = mutableStateOf(TextFieldValue())
    val showEmailBottomSheet = mutableStateOf(false)
    val showCodeBottomSheet = mutableStateOf(false)
    val showResetPasswordSheet = mutableStateOf(false)

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when {
                    uiState.isError -> {
                        Toast.makeText(
                            LocalContext.current, uiState.errorMessage ?: "Something went wrong!", Toast.LENGTH_LONG
                        ).show()
                    }

                    uiState.isLoginSuccess -> {
                        Toast.makeText(LocalContext.current, "Login success!", Toast.LENGTH_LONG).show()
                    }

                    uiState.isPasswordChangeSuccess -> {
                        Toast.makeText(LocalContext.current, "Password change success!", Toast.LENGTH_LONG).show()
                    }
                }

                AuthenticationFlowBackground()
                if (uiState.isLoading) {
                    LoadingIndicator()
                }

                LoginContent(
                    username,
                    password,
                    forgotPasswordPress = { showEmailBottomSheet.value = true },
                    onRegisterPress = { onIntent(LoginIntent.RegisterClicked) }) { user, password ->
                    onIntent(LoginIntent.AttemptLogin(user, password))
                }

                ForgotPasswordEmailModal(
                    showEmailBottomSheet,
                    email,
                    forgotPasswordAttempt = { onIntent(LoginIntent.ForgotPasswordInput(email.value.text)) })

                if (uiState.forgotEmailEntered) {
                    showCodeBottomSheet.value = true
                    VerifyResetCodeModal(
                        showCodeBottomSheet,
                        uiState.forgottenPasswordEmail,
                        uiState.verifyCodeFailed
                    ) { email, code ->
                        onIntent(LoginIntent.VerifyCode(email, code))
                    }
                }

                if (uiState.forgotEmailCodeEntered) {
                    showResetPasswordSheet.value = true
                    ChangePasswordCodeModal(showResetPasswordSheet) { newPassword ->
                        onIntent(LoginIntent.AttemptResetPassword(newPassword, uiState.resetPasswordToken))
                    }
                }
            }
        })
}