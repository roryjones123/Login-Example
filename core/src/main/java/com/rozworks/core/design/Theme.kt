package com.rozworks.core.design

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

// fix this
private val DarkColorScheme = darkColorScheme(
    primary = MossDark40,
    secondary = GreenDark40,
    tertiary = GreyDark40,
    onTertiary = Color.Gray,
    surface = Color.White,
    inverseSurface = Color.Black
)

private val LightColorScheme = lightColorScheme(
    primary = MossLight40,
    secondary = GreenLight40,
    tertiary = GreyLight40,
    onTertiary = Color.Gray,
    surface = Color.White,
    inverseSurface = Color.Black
)

@Composable
fun TemplateTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = pickColorScheme(darkTheme)
    val view = LocalView.current

    if (!view.isInEditMode) {
        val currentWindow = (view.context as? Activity)?.window
            ?: error("Not in an activity - unable to get Window reference")

        SideEffect {
            currentWindow.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(currentWindow, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content,
    )
}

@Composable
fun pickColorScheme(
    darkTheme: Boolean,
): ColorScheme = when {
    darkTheme -> DarkColorScheme
    else -> LightColorScheme
}
