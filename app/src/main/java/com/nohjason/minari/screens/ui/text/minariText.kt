package com.nohjason.minari.screens.ui.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun minariText(
    text: String,
    size: Int = 30,
    color: Color = Color.Black
) {
    Text(
        text = text,
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        color = color
    )
}