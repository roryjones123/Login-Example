package com.rozworks.features.presentation.authentication.getstarted.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

@Composable
fun AuthenticationFlowBackground() {
    val primary = MaterialTheme.colorScheme.primary
    val secondary = MaterialTheme.colorScheme.secondary
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.tertiary)
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val bigBubbleSize = Size(size.width * BUBBLE_WIDTH_MULTIPLIER, size.height * BUBBLE_LARGE_HEIGHT_MULTIPLIER)
            val innerBubbleSize =
                Size(size.width * BUBBLE_WIDTH_MULTIPLIER, size.height * BUBBLE_SMALL_HEIGHT_MULTIPLIER)

            drawOval(
                color = secondary,
                topLeft = Offset(
                    -bigBubbleSize.width * BUBBLE_OFFSET_MULTIPLIER,
                    size.height - bigBubbleSize.height + BUBBLE_OFFSET
                ),
                size = bigBubbleSize
            )

            drawOval(
                color = primary,
                topLeft = Offset(
                    -innerBubbleSize.width * BUBBLE_OFFSET_MULTIPLIER,
                    size.height - innerBubbleSize.height + BUBBLE_OFFSET
                ),
                size = innerBubbleSize
            )
        }
    }
}

const val BUBBLE_OFFSET_MULTIPLIER = 0.3f
const val BUBBLE_OFFSET = 500f
const val BUBBLE_WIDTH_MULTIPLIER = 1.5f
const val BUBBLE_SMALL_HEIGHT_MULTIPLIER = 0.6f
const val BUBBLE_LARGE_HEIGHT_MULTIPLIER = 0.9f