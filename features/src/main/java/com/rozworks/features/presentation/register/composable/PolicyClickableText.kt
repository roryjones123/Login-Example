package com.rozworks.features.presentation.register.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.style.TextAlign
import com.rozworks.core.design.Typography
import com.rozworks.loginexample.features.R

@Composable
fun PolicyClickableText() {
    var checked by remember { mutableStateOf(false) }

    val interactionSource = remember { MutableInteractionSource() }

    Row(
        modifier = Modifier
            .clickable(
                interactionSource = interactionSource,
                indication = null,
                onClick = { checked = !checked }
            )
            .background(
                when {
                    interactionSource.collectIsPressedAsState().value -> MaterialTheme.colorScheme.tertiary.copy(
                        alpha =
                        CLICKABLE_TRANSPARENCY
                    )

                    else -> Color.Transparent
                },
                shape = RoundedCornerShape(dimensionResource(id = R.dimen.clickable_text_corners))
            )
            .padding(all = dimensionResource(id = R.dimen.dimen_small)),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Checkbox(
            checked = checked,
            onCheckedChange = null,
            modifier = Modifier.padding(end = dimensionResource(id = R.dimen.dimen_medium))
        )

        Text(
            text = AnnotatedString(stringResource(id = R.string.privacy_policy_agreement)),
            style = Typography.bodyMedium.copy(textAlign = TextAlign.Start),
        )
    }
}

const val CLICKABLE_TRANSPARENCY = 0.2f