package com.nohjason.minari.screens.home.words.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun RecommedWordsCard() {
    Row(
        modifier = Modifier
            .background(Color.Gray)
            .width(100.dp)
    ) {
        MinariText(text = "펀드")
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    RecommedWordsCard()
}