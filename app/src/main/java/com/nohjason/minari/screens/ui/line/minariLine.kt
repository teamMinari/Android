package com.nohjason.minari.screens.ui.line

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.unit.dp
import com.nohjason.minari.ui.theme.MinariGray

@Composable
fun minariLine(
    width: Int,
) {
    Canvas(
        modifier = Modifier
            .width(width.dp)
//            .padding(top = 15.dp, bottom = 10.dp)
    ) {
        val canvasWidth = size.width
        val canvasHeight = size.height
        drawLine(
            start = Offset(x = 0.dp.toPx(), y = canvasHeight / 2),
            end = Offset(x = canvasWidth, y = canvasHeight / 2),
            color = MinariGray,
            strokeWidth = 1.dp.toPx() // instead of 5.dp.toPx() , you can also pass 5f
        )
    }
}