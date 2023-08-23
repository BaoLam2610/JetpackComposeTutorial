package com.lambao.tutorial.ui.home

import androidx.annotation.DrawableRes
import androidx.compose.ui.graphics.Color

data class Feature(
    val title: String,
    @DrawableRes val iconId: Int,
    val darkColor: Color
)