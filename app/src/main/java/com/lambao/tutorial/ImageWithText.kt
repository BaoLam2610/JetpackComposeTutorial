package com.lambao.tutorial

import androidx.compose.ui.graphics.painter.Painter

data class ImageWithText(
    val painter: Painter,
    val text: String
)