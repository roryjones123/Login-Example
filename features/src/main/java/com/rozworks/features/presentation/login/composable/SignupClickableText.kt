package com.rozworks.features.presentation.login.composable

import androidx.compose.foundation.text.ClickableText
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun SignUpClickableText(modifier: Modifier, onSignUpPress: () -> Unit) {
    ClickableText(
        text = AnnotatedString(stringResource(id = R.string.sign_up_text)),
        modifier = modifier,
        onClick = { onSignUpPress.invoke() },
        style = Typography.bodyMedium.copy(textAlign = TextAlign.Center, color = MaterialTheme.colorScheme.surface),
    )
}