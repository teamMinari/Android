package com.nohjason.minari.screens.home.words

import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
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
                Card(35.dp, 5.dp, Color.LightGray)
                Card(35.dp, 5.dp, Color.LightGray)
            }

        }
    }
}

@Composable
fun Card(
    height: Dp,
    clip: Dp,
    color: Color
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        Box(modifier = Modifier
            .weight(1f)
            .height(height)
            .clip(RoundedCornerShape(clip))
            .background(color)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier
                        .padding(5.dp)
                ) {
                    MinariText(text = "가산금리", size = 10)
                    Row(horizontalArrangement = Arrangement.spacedBy(2.dp)) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(7.dp),
                            tint = Color.Unspecified
                        )
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(7.dp),
                            tint = Color.Unspecified
                        )
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = null,
                            modifier = Modifier.size(7.dp),
                            tint = Color.Unspecified
                        )
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    painter = painterResource(id = R.drawable.minari_hart),
                    contentDescription = "hart",
                    tint = Color.Unspecified,
                    modifier = Modifier.padding(10.dp)
                )
            }
        }
        Box(modifier = Modifier
            .weight(1f)
            .height(height)
            .clip(RoundedCornerShape(clip))
            .background(color)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun TestRecommend() {
    RecommendWords()
}

@Preview(showBackground = true)
@Composable
fun Cardprivew() {
    Card(30.dp, 5.dp, Color.LightGray)
}