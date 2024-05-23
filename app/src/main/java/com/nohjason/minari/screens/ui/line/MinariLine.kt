package com.nohjason.minari.screens.ui.line

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.minari.ui.theme.MinariGray

sealed class BorderType(val size: Dp) {
    data object Thin : BorderType(1.dp)
    data object Border : BorderType(5.dp)
}

@Composable
fun MinariLine(
    thickness: BorderType = BorderType.Thin
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clip(CircleShape)
            .background(MinariGray)
            .height(thickness.size)
    )
}

@Preview(showBackground = true)
@Composable
fun Test() {
    MinariLine()
}