package com.rozworks.features.presentation.login.composable

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size

@Composable
fun LoginBackground() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F5F5))  // A light grey background
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val bigBubbleSize = Size(size.width * 1.5f, size.height * 0.9f)
            val innerBubbleSize = Size(size.width * 1.5f, size.height * 0.6f)

            drawOval(
                color = Color(0xFF4CAF50),
                topLeft = Offset(-bigBubbleSize.width * 0.3f, size.height - bigBubbleSize.height + 500f),
                size = bigBubbleSize
            )
            drawOval(
                color = Color(0xFF2E7D32),
                topLeft = Offset(-innerBubbleSize.width * 0.3f, size.height - innerBubbleSize.height + 500f),
                size = innerBubbleSize
            )

            val topBubble1Size = Size(size.width * 0.35f, size.height * 0.3f)
            val topBubble2Size = Size(size.width * 0.22f, size.height * 0.22f)

            drawOval(
                color = Color(0xFF8BC34A),
                topLeft = Offset(size.width - topBubble1Size.width + 100f, (-topBubble1Size.height * 0.2f) - 100f),
                size = topBubble1Size
            )
            drawOval(
                color = Color(0xFF7CB342),
                topLeft = Offset(size.width - (topBubble2Size.width * 1.3f) + 200f, topBubble2Size.height * 0.3f),
                size = topBubble2Size
            )
        }
    }
}