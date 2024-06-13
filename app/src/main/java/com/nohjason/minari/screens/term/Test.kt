package com.nohjason.minari.screens.term

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.news.NewsCard
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariLightGray
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.MinariWhiteBlue

// home스크린에서 받은 글자를 표시하는 테스트 화면
@Composable
fun Test(
    title: String,
    navController: NavController
) {

    val word =
        if (title.isNotEmpty()) allWords.find { it.title == title }
        else null
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MinariWhite)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp))
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(vertical = 5.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.navigate(BottomBarScreen.Home.rout) }) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_radio_button_unchecked_24),
                            contentDescription = null,
                        )
                    }
                    MinariTextField(
                        value = text,
                        onValueChange = {text = it},
                        onClick = {navController.navigate("test/${title}")},
                    )
                }
            }

            Spacer(modifier = Modifier.height(10.dp))

            if (word != null) {
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .background(Color.White)
                        .weight(0.4f)
                ) {
                    LazyColumn(
                        modifier = Modifier
                            .padding(10.dp)
                    ) {
                        item {
                            LazyRow {
                                items(word.starCount) { item ->
                                    Icon(
                                        painter = painterResource(R.drawable.star),
                                        contentDescription = null,
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(20.dp)
                                    )
                                }
                            }
                            MinariText(text = word.title)
                            MinariLine()
                            MinariText(text = word.value, size = 13)
                        }
                    }
                }

                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(0.6f)
                ) {
                    Column {
                        MinariText(text = "관련 용어", size = 15)
                        LazyRow(modifier = Modifier.padding(vertical = 5.dp)) {
                            itemsIndexed(word.dummyTermSimilarButton) { index, item ->
                                Box(
                                    modifier = Modifier
                                        .clip(CircleShape)
                                        .background(Color.White)
                                        .border(
                                            1.dp,
                                            Color.Black,
                                            shape = CircleShape
                                        )
                                        .padding(vertical = 3.dp, horizontal = 10.dp)
                                ) {
                                    MinariText(text = item.title, size = 10)
                                }
                                if (index != word.dummyTermSimilarButton.size - 1) {
                                    Spacer(modifier = Modifier.width(5.dp))
                                }
                            }
                        }
                        LazyColumn(
                            modifier = Modifier
                                .padding(10.dp)
                                .background(Color.White)
                        ) {
                            items(10) { index ->
                                NewsCard()
                                if (index != 9) {
                                    Spacer(modifier = Modifier.height(10.dp))
                                }
                            }
                        }
                    }
                }

            } else {
                MinariText(text = "정보 없음")
            }

        }
    }
}

@Preview
@Composable
fun TermTest() {
    Test(title = "황금낙하산", navController = rememberNavController())
}