package com.example.gram.ui

import androidx.compose.foundation.border
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradient
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.onPositioned
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

fun Modifier.diagonalGradientBorder(
    colors: List<Color>,
    borderSize: Dp = 2.dp,
    shape: Shape,
    isFromRight: Boolean = false
) = gradientBorder(
    colors = colors,
    borderSize = borderSize,
    shape = shape
) { gradientColors, size ->

    var startX = 0f
    var starty = 0f
    var endX = size.width.toFloat()
    var endY = size.height.toFloat()

    if (isFromRight) {
        startX = size.width.toFloat()
        starty = 0f
        endX = 0f
        endY = size.height.toFloat()
    }

    LinearGradient(
        colors = gradientColors,
        startX = startX,
        startY = starty,
        endX = endX,
        endY = endY
    )
}

fun Modifier.gradientBorder(
    colors: List<Color>,
    borderSize: Dp = 2.dp,
    shape: Shape,
    brushProvider: (List<Color>, IntSize) -> LinearGradient
) = composed {
    var size = remember { mutableStateOf(IntSize.Zero) }
    val gradient = remember(colors, size) { brushProvider(colors, size.value) }
    val sizeProvider = onPositioned { size.value = it.size }
    sizeProvider then border(
        width = borderSize,
        brush = gradient,
        shape = shape
    )
}