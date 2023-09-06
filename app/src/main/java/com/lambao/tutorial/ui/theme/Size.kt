package com.lambao.tutorial.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class IconSize(
    val small: Dp = 18.dp,
    val default: Dp = 24.dp,
    val medium: Dp = 28.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 36.dp
)

val LocalIconSize = compositionLocalOf { IconSize() }

val MaterialTheme.iconSize: IconSize
    @Composable
    @ReadOnlyComposable
    get() = LocalIconSize.current
