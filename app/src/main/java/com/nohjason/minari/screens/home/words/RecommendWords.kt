package com.nohjason.minari.screens.home.words

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.home.words.card.RecommedWordsCard
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
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center
            ) {
                Spacer(modifier = Modifier.weight(0.3f))
                Row {
                    Box(modifier = Modifier
                        .background(Color.Gray)
                        .height(10.dp)
                        .weight(0.5f)
                    ) {
                        MinariText(text = "wow")
                    }
                    Box(modifier = Modifier
                        .background(Color.Black)
                        .height(10.dp)
                        .weight(0.5f)
                    ) {
                        MinariText(text = "wow")
                    }
                }
                Spacer(modifier = Modifier.weight(0.3f))
                Row {
                    Box(modifier = Modifier
                        .background(Color.Gray)
                        .height(10.dp)
                        .weight(0.5f)
                    )
                    Box(modifier = Modifier
                        .background(Color.Black)
                        .height(10.dp)
                        .weight(0.5f)
                    )
                }
                Spacer(modifier = Modifier.weight(0.3f))
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestRecommend() {
    RecommendWords()
}