package com.nohjason.minari.screens.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.line.minariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun News() {
    Box(
        modifier = Modifier
            .width(340.dp)
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

            minariLine(width = 275)

            Spacer(modifier = Modifier.height(10.dp))

            LazyColumn {
                items(50) { index ->
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(70.dp)
                    ) {
                        Row {
                            Image(
                                painter = painterResource(R.drawable.ic_launcher_background),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp, 70.dp)
                                    .border(1.dp, Color.Black)
                            )

                            Spacer(modifier = Modifier.width(10.dp))

                            Column {
                                MinariText(text = "제목", size = 13)
                                MinariText(text = "간단한 내용", size = 7)
                            }
                        }
                    }
                }
                item {
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun TestNews() {
    News()
}