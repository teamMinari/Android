package com.nohjason.minari.screens.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun RecommendWords() {
    Box(
        modifier = Modifier
            .size(325.dp, 150.dp)
            .background(Color.White)
            .clip(shape = RoundedCornerShape(20.dp))
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp, vertical = 5.dp)
        ) {
            MinariText(text = "오늘의 추천 경제 단어", size = 15)
            Spacer(modifier = Modifier.height(5.dp))
            MinariLine()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestRecommend() {
    RecommendWords()
}