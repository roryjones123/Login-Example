package com.rozworks.features.presentation.authentication.login.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun ForgotPasswordClickableText(forgotPasswordPress: () -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.forgot_password)),
        onClick = { forgotPasswordPress.invoke() },
        style = Typography.bodyMedium
    )
}