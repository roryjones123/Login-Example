package com.rozworks.features.presentation.authentication.login.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import com.rozworks.loginexample.features.R

@Composable
fun LoginContent(
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    onRegisterPress: () -> Unit,
    forgotPasswordPress: () -> Unit,
    attemptLogin: (String, String) -> Unit,
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        LoginHeader(Modifier.weight(0.36f))
        LoginBodyContent(Modifier.weight(0.36f), username, password, forgotPasswordPress)
        LoginFooter(Modifier.weight(0.36f), username, password, attemptLogin, onRegisterPress)
    }
}

@Composable
fun LoginHeader(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.dimen_extra_large),
                end = dimensionResource(id = R.dimen.dimen_extra_large),
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        LoginTitle(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_medium)))
        LoginBody(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)))
    }
}

@Composable
fun LoginBodyContent(
    modifier: Modifier,
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    forgotPasswordPress: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
        verticalArrangement = Arrangement.Center
    ) {
        UsernameEditText(
            label = stringResource(id = R.string.username),
            mutableText = username,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                .fillMaxWidth(),
        )
        PasswordEditText(
            label = stringResource(id = R.string.password),
            mutableText = password,
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                .fillMaxWidth()
        )
        ForgotPasswordClickableText(forgotPasswordPress)
    }
}

@Composable
fun LoginFooter(
    modifier: Modifier,
    username: MutableState<TextFieldValue>,
    password: MutableState<TextFieldValue>,
    attemptLogin: (String, String) -> Unit,
    onRegisterPress: () -> Unit
) {
    fun fieldsArentEmpty() = username.value.text.isNotEmpty() && password.value.text.isNotEmpty()

    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
        verticalArrangement = Arrangement.Bottom
    ) {
        LoginButton(
            enabled = fieldsArentEmpty(),
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_medium))
                .fillMaxWidth(),
            onIntent = { attemptLogin(username.value.text, password.value.text) }
        )

        SignUpClickableText(
            modifier = Modifier
                .padding(dimensionResource(id = R.dimen.dimen_extra_large))
                .fillMaxWidth(),
            onSignUpPress = onRegisterPress
        )
    }
}
