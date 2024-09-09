package com.nohjason.minari.screens.profile.element

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun NowNoun(){
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(150.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(
            modifier = Modifier
                .fillMaxSize()
                .padding(end = 50.dp) // 칭호 이미지가 그래프 끝에 위치할 공간을 확보
        ) {
            val barWidth = size.width
            val barHeight = size.height
            val filledWidth = barWidth * (30 / 100) // 채워진 부분의 너비
            //exp / goal 받아서 어디까지 채워야하는지

            // 배경 바
            drawRect(
                color = Color.White,
                size = Size(barWidth, barHeight)
            )

            // 채워진 바
            drawRect(
                color = Color.Blue,
                size = Size(filledWidth, barHeight)
            )
        }

        // 칭호 이미지
//        Imagesge(
//            painter = painterResource(id = titleImageResId),
//            contentDescription = "Title",
//            modifier = Modifier
//                .align(Alignment.CenterEnd)
//                .size(50.dp)
//                .offset(x = (-25.dp)) // 이미지 크기에 맞춰 적절히 위치 조정
//        )
        }
}

@Preview
@Composable
fun PreNoun(){
    NowNoun()
}