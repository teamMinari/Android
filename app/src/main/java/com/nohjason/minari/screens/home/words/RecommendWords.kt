package com.nohjason.minari.screens.home.words

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.data.word.Word
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.screens.home.words.card.RecommedWordsCard
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun RecommendWords() {

    Box(
        modifier = Modifier
            .clip(shape = RoundedCornerShape(15.dp))
            .fillMaxWidth()
            .height(150.dp)
            .background(Color.White)

    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        ) {
            MinariText(text = "오늘의 추천 경제 단어", size = 15)
            MinariLine()
            Column(
                modifier = Modifier
                    .fillMaxSize(),
                verticalArrangement = Arrangement.SpaceAround,  // 세로 방향의 정렬을 SpaceAround로 설정
                horizontalAlignment = Alignment.CenterHorizontally  // 가로 방향의 정렬을 가운데로 설정
            ) {
                val random = getRandomWords(allWords, 4)

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RecommedWordsCard(title = random[0].title, starCount = random[0].starCount)
                    RecommedWordsCard(title = random[1].title, starCount = random[1].starCount)
                }
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceAround
                ) {
                    RecommedWordsCard(title = random[2].title, starCount = random[2].starCount)
                    RecommedWordsCard(title = random[3].title, starCount = random[3].starCount)
                }
            }

        }
    }
}

fun getRandomWords(words: List<Word>, count: Int): List<Word> {
    return words.shuffled().take(count)
}

@Preview(showBackground = true)
@Composable
fun TestRecommend() {
    RecommendWords()
}