package com.nohjason.minari.screens.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
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
import com.nohjason.minari.screens.home.news.News
import com.nohjason.minari.screens.home.news.button.CategoryNewsButton
import com.nohjason.minari.screens.home.news.button.NewsCategoryButton
import com.nohjason.minari.screens.home.words.RecommendWords
import com.nohjason.minari.screens.profile.Menu
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun HomeScreen(
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(10.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.grape),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier.size(35.dp)
                )
                MinariText(text = "청For도", size = 20)
                Spacer(modifier = Modifier.fillMaxWidth())
            }

            MinariTextField(
                modifier = Modifier
                    .height(30.dp)
                    .padding(horizontal = 30.dp)
                    .fillMaxWidth()
                    .background(MinariWhite, shape = CircleShape),
                value = text,
                onValueChange = { text = it },
                onClick = { if (text.isNotEmpty()) navController.navigate("test/${text}") }
            )

            Box(
                modifier = Modifier
                    .padding(top = 10.dp)
                    .fillMaxSize()
                    .background(MinariWhite),
            ) {
                Column(
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 10.dp)
                        .fillMaxSize(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    RecommendWords()

                    Spacer(modifier = Modifier.height(20.dp))
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        NewsCategoryButton(
                            image = painterResource(id = R.drawable.img_house),
                            onClick = {}
                        )

                        NewsCategoryButton(
                            image = painterResource(id = R.drawable.img_chart),
                            onClick = {}
                        )

                        NewsCategoryButton(
                            image = painterResource(id = R.drawable.img_coin),
                            onClick = {}
                        )

                        NewsCategoryButton(
                            image = painterResource(id = R.drawable.img_folder),
                            onClick = {}
                        )
                    }

                    Spacer(modifier = Modifier.height(20.dp))
                    News()
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewHome() {
    HomeScreen(navController = rememberNavController())
}

@Preview(showBackground = true)
@Composable
fun TestMinariTextField() {
    var text by remember { mutableStateOf("") }
    Box(modifier = Modifier.fillMaxSize()) {
        MinariTextField(
            value = text,
            onValueChange = { text = it },
            onClick = {},
            modifier = Modifier
                .height(30.dp)
                .padding(horizontal = 30.dp)
                .fillMaxWidth()
                .background(MinariWhite, shape = CircleShape),
        )
    }
}
