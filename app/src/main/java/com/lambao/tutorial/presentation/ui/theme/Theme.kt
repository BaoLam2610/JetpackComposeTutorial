package com.lambao.tutorial.presentation.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val darkColorScheme = darkColorScheme(
    primary = Color.Green,
    background = DarkBlue,
    onPrimary = Color.DarkGray,
    onBackground = TextWhite
)

@Composable
fun TutorialTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = darkColorScheme,
        typography = Typography,
        content = content
    )
}