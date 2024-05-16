package com.nohjason.minari.screens.dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.dictionary.button.CatagoryButton
import com.nohjason.minari.screens.dictionary.button.DictionaryButton
import com.nohjason.minari.screens.dictionary.card.WordCard
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun DictionaryScreen() {
    var text by remember { mutableStateOf("") }
    // kotlin.collections<T>.ImmutableList
    val test = MutableList(10) { Test(it, "가계부실위험지수 ${it}", true)}.toList()
    Box(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.height(15.dp))

            MinariTextField(
                value = text,
                onValueChange = {text = it},
                onClick = {}
            )

            Spacer(modifier = Modifier.height(15.dp))
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MinariWhite)
                    .padding(10.dp)
            ) {
                Column {
                    CatagoryButton()
                    Spacer(modifier = Modifier.height(10.dp))
                    LazyColumn {
                        items(
                            items = test,
                            key = { it.id }
                        ) { item ->

                            WordCard(
                                title = item.title,
                                isLike = item.isLike,
                                onLikeClick = { _ ->

                                }
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}

data class Test(
    val id: Int,
    val title: String,
    val isLike: Boolean
)

@Preview(showBackground = true)
@Composable
fun TestDictionaryScreen() {
    DictionaryScreen()
}