package com.rozworks.features.presentation.login.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun LoginButton(modifier: Modifier, onIntent: () -> Unit, enabled: Boolean = true) {
    Box {
        Button(
            enabled = enabled,
            onClick = { onIntent() },
            shape = RoundedCornerShape(dimensionResource(id = R.dimen.round_corners)),
            colors = ButtonDefaults.buttonColors(
                containerColor = MaterialTheme.colorScheme.surface,
                disabledContainerColor = MaterialTheme.colorScheme.surface.copy(alpha = DISABLED_ALPHA)
            ),
            modifier = modifier
                .fillMaxWidth()
                .height(dimensionResource(id = R.dimen.big_button_size))
        ) {
            Text(
                style = Typography.titleLarge,
                text = stringResource(id = R.string.sign_in),
                color = MaterialTheme.colorScheme.inverseSurface
            )
        }
    }
}