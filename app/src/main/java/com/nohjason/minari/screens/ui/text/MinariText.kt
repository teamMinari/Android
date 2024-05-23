package com.nohjason.minari.screens.ui.text

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp

@Composable
fun MinariText(
    modifier: Modifier = Modifier,
    text: String,
    size: Int = 30,
    color: Color = Color.Black,
    maxLines: Int = Int.MAX_VALUE,
    minLines: Int = 1,
    overflow: TextOverflow = TextOverflow.Clip,
) {
    Text(
        modifier = modifier,
        text = text,
        fontSize = size.sp,
        fontWeight = FontWeight.Bold,
        color = color,
        maxLines = maxLines,
        minLines = minLines,
        overflow = overflow
    )
}