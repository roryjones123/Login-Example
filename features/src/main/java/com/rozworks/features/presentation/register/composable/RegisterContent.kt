package com.rozworks.features.presentation.register.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.features.presentation.getstarted.composable.RegisterBody
import com.rozworks.features.presentation.getstarted.composable.RegisterButton
import com.rozworks.features.presentation.getstarted.composable.RegisterTitle
import com.rozworks.features.presentation.login.composable.PasswordEditText
import com.rozworks.features.presentation.login.composable.PasswordValidationResult
import com.rozworks.features.presentation.login.composable.UsernameEditText
import com.rozworks.features.presentation.login.composable.UsernameValidationResult
import com.rozworks.loginexample.features.R

@Composable
fun RegisterContent(
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>,
    onSignInPress: () -> Unit,
    onRegisterAttempted: (String, String, String) -> Unit
) {
    val emailValidationErrors = remember { mutableStateOf<List<EmailValidationResult>>(emptyList()) }
    val passwordValidationErrors = remember { mutableStateOf<List<PasswordValidationResult>>(emptyList()) }
    val usernameValidationErrors = remember { mutableStateOf<List<UsernameValidationResult>>(emptyList()) }

    fun isValidationErrors(): Boolean =
        emailValidationErrors.value.isEmpty() && passwordValidationErrors.value.isEmpty() && usernameValidationErrors.value.isEmpty()

    fun anyTextIsEmpty(): Boolean =
        !username.value.text.isEmpty() && !password.value.text.isEmpty() && !email.value.text.isEmpty()

    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        RegisterHeader(Modifier.weight(0.3f))
        RegisterBodyContent(
            modifier = Modifier.weight(0.4f),
            username = username,
            email = email,
            password = password,
            usernameValidationErrors = usernameValidationErrors,
            emailValidationErrors = emailValidationErrors,
            passwordValidationErrors = passwordValidationErrors
        )
        RegisterFooter(
            username = username,
            password = password,
            email = email,
            modifier = Modifier.weight(0.3f),
            isEnabled = anyTextIsEmpty() && isValidationErrors(),
            onRegisterAttempted = onRegisterAttempted,
            onSignInPress = onSignInPress
        )
    }
}

@Composable
fun RegisterHeader(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.dimen_extra_large),
                end = dimensionResource(id = R.dimen.dimen_extra_large)
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        RegisterTitle(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_medium)))
        RegisterBody(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)))
    }
}

@Composable
fun RegisterBodyContent(
    modifier: Modifier,
    username: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    usernameValidationErrors: MutableState<List<UsernameValidationResult>>,
    emailValidationErrors: MutableState<List<EmailValidationResult>>,
    passwordValidationErrors: MutableState<List<PasswordValidationResult>>
) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.dimen_extra_large),
                end = dimensionResource(id = R.dimen.dimen_extra_large)
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        UsernameEditText(
            label = stringResource(id = R.string.username),
            mutableText = username,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                .fillMaxWidth(),
            validationErrors = usernameValidationErrors
        )
        EmailEditText(
            label = stringResource(id = R.string.email),
            mutableText = email,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                .fillMaxWidth(),
            validationErrors = emailValidationErrors
        )
        PasswordEditText(
            label = stringResource(id = R.string.password),
            mutableText = password,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_small))
                .fillMaxWidth(),
            validationErrors = passwordValidationErrors
        )
        PolicyClickableText()
    }
}

@Composable
fun RegisterFooter(
    username: MutableState<TextFieldValue>,
    email: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    modifier: Modifier,
    isEnabled: Boolean,
    onRegisterAttempted: (String, String, String) -> Unit,
    onSignInPress: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
        verticalArrangement = Arrangement.Bottom
    ) {
        RegisterButton(
            isEnabled = isEnabled,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_large))
                .fillMaxWidth(),
            onIntent = { onRegisterAttempted(username.value.text, password.value.text, email.value.text) }
        )
        SignInClickableText(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_extra_large))
                .fillMaxWidth(),
            onTextClick = onSignInPress
        )
    }
}
