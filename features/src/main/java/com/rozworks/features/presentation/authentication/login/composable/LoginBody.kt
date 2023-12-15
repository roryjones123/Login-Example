package com.rozworks.features.presentation.authentication.login.composable

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun LoginBody(modifier: Modifier) {
    Text(
        text = stringResource(id = R.string.welcome_back_body),
        style = Typography.bodyLarge,
        modifier = modifier.fillMaxWidth(),
        textAlign = TextAlign.Start
    )
}