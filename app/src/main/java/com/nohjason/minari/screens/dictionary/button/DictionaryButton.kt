package com.nohjason.minari.screens.dictionary.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun DictionaryButton() {
    var test by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (test) MinariBlue else MinariWhite)
            .clickable { test = !test }
    ) {
        MinariText(
            text = "wow",
            modifier = Modifier.padding(horizontal = 20.dp, vertical = 5.dp),
            color = if (test) Color.White else Color.Black,
            size = 15
        )
    }
}