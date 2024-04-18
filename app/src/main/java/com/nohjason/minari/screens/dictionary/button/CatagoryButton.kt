package com.nohjason.minari.screens.dictionary.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun CatagoryButton() {
    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(MinariBlue)
            .clickable { }
    ) {
        Text(
            text = "wow",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 5.dp),
            color = Color.White
        )
    }
}