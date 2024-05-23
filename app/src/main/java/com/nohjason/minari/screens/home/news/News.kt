package com.nohjason.minari.screens.home.news

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun News() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .background(Color.White)
            .border(1.dp, Color.Black)
    ) {
        Column(
            modifier = Modifier
                .padding(horizontal = 20.dp, vertical = 10.dp)
                .fillMaxSize(),
        ) {
            MinariText(text = "오늘의 경제 기사/사건", size = 15)

            Spacer(modifier = Modifier.height(10.dp))

            MinariLine()

            Spacer(modifier = Modifier.height(20.dp))

            LazyColumn {
                items(getDummyUsers()) { index ->
                    NewsCard(index.title, index.value)
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }
    }
}

data class Test(
    val id: Int,
    val title: String,
    val value: String
)

fun getDummyUsers(): List<Test> {
    return listOf(
        Test(1, "[뭔말이지?]브리핑 영업", "브리핑 영업은 법인보험대리점(GA) 설계사가 회사나 단체를 방문해 다수의 소비자에게 보험 상품"),
        Test(2, "[뭔말이지?]브리핑 영업", "브리핑 영업은 법인보험대리점(GA) 설계사가 회사나 단체를 방문해 다수의 소비자에게 보험 상품"),
        Test(3, "[뭔말이지?]브리핑 영업", "브리핑 영업은 법인보험대리점(GA) 설계사가 회사나 단체를 방문해 다수의 소비자에게 보험 상품"),
        Test(4, "[뭔말이지?]브리핑 영업", "브리핑 영업은 법인보험대리점(GA) 설계사가 회사나 단체를 방문해 다수의 소비자에게 보험 상품"),
        Test(5, "[뭔말이지?]브리핑 영업", "브리핑 영업은 법인보험대리점(GA) 설계사가 회사나 단체를 방문해 다수의 소비자에게 보험 상품")
    )
}

@Preview(showBackground = true)
@Composable
fun TestNews() {
    News()
}