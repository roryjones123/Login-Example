package com.rozworks.features.presentation.authentication.getstarted.composable

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import com.rozworks.features.presentation.authentication.login.composable.LoginButton
import com.rozworks.features.presentation.getstarted.composable.RegisterButton
import com.rozworks.loginexample.features.R

@Composable
fun GetStartedContent(
    registerClicked: () -> Unit,
    loginClicked: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        GetStartedHeader(Modifier.weight(0.35f))
        GetStartedFooter(Modifier.weight(0.6f), registerClicked, loginClicked)
    }
}

@Composable
fun GetStartedHeader(modifier: Modifier) {
    Column(
        modifier = modifier
            .padding(
                start = dimensionResource(id = R.dimen.dimen_extra_large),
                end = dimensionResource(id = R.dimen.dimen_extra_large)
            ),
        verticalArrangement = Arrangement.Bottom
    ) {
        GetStartedTitle(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_medium)))
        GetStartedBody(modifier = Modifier.padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large)))
    }
}

@Composable
fun GetStartedFooter(
    modifier: Modifier,
    registerClicked: () -> Unit,
    loginClicked: () -> Unit
) {
    Column(
        modifier = modifier
            .padding(horizontal = dimensionResource(id = R.dimen.dimen_extra_large)),
        verticalArrangement = Arrangement.Bottom
    ) {
        RegisterButton(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_large))
                .fillMaxWidth(),
            onIntent = { registerClicked.invoke() },
            isEnabled = true
        )
        LoginButton(
            modifier = Modifier
                .padding(bottom = dimensionResource(id = R.dimen.dimen_extra_large))
                .fillMaxWidth(),
            onIntent = { loginClicked.invoke() }
        )
    }
}