package com.rozworks.features.presentation.register.composable

import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.rozworks.features.presentation.getstarted.composable.AuthenticationFlowBackground
import com.rozworks.features.presentation.login.composable.LoadingIndicator
import com.rozworks.features.presentation.register.RegisterIntent
import com.rozworks.features.presentation.register.RegisterUiState
import com.rozworks.features.presentation.register.RegisterViewModel

@Composable
fun RegisterRoute(
    viewModel: RegisterViewModel = hiltViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    val username = viewModel.username
    val password = viewModel.password
    val email = viewModel.email

    RegisterScreen(
        uiState = uiState,
        onIntent = viewModel::acceptIntent,
        username = username,
        password = password,
        email = email
    )
}

@Composable
internal fun RegisterScreen(
    uiState: RegisterUiState,
    onIntent: (RegisterIntent) -> Unit,
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>
) {
    val snackbarHostState = remember { SnackbarHostState() }

    if (uiState.isRegisterSuccess) {
        Toast.makeText(LocalContext.current, "Register success!", Toast.LENGTH_SHORT).show()
    }

    Scaffold(
        snackbarHost = { SnackbarHost(snackbarHostState) },
        content = { padding ->
            Box(modifier = Modifier.padding(padding)) {
                when {
                    uiState.isError -> {
                        Toast.makeText(
                            LocalContext.current, uiState.errorMessage ?: "Something went wrong", Toast.LENGTH_LONG
                        ).show()
                    }
                }

                AuthenticationFlowBackground()

                if (uiState.isLoading) {
                    LoadingIndicator()
                }

                RegisterContent(
                    username,
                    password,
                    email,
                    onSignInPress = { onIntent(RegisterIntent.SignInPress) }
                ) { user, password, email ->
                    onIntent(RegisterIntent.AttemptRegister(user, password, email))
                }
            }
        })
}