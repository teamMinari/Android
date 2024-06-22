package com.nohjason.minari.screens.home.words

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
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
                
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    RecommedWordsCard(
                        title1 = random[0].title,
                        starCount1 = random[0].starCount,
                        title2 = random[1].title,
                        starCount2 = random[1].starCount
                    )

                    RecommedWordsCard(
                        title1 = random[2].title,
                        starCount1 = random[2].starCount,
                        title2 = random[3].title,
                        starCount2 = random[3].starCount
                    )
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