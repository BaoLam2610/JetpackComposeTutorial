package com.lambao.tutorial.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.ReadOnlyComposable
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

data class Spacing(
    val default: Dp = 0.dp,
    val tiny: Dp = 2.dp,
    val extraTiny: Dp = 3.dp,
    val small: Dp = 4.dp,
    val extraSmall: Dp = 8.dp,
    val normal : Dp = 10.dp,
    val extraNormal: Dp = 12.dp,
    val medium: Dp = 16.dp,
    val extraMedium : Dp = 20.dp,
    val large: Dp = 32.dp,
    val extraLarge: Dp = 64.dp,
)

val LocalSpacing = compositionLocalOf { Spacing() }

val MaterialTheme.spacing: Spacing
    @Composable
    @ReadOnlyComposable
    get() = LocalSpacing.current
