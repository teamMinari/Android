package com.nohjason.minari.screens.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.line.minariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun RecommendWords() {
    Box(
        modifier = Modifier
            .size(325.dp, 150.dp)
            .clip(shape = RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(10.dp)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding()
        ) {
            MinariText(text = "오늘의 추천 경제 단어", size = 15)
            minariLine()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestRecommend() {
    RecommendWords()
}