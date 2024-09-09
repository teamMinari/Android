package com.nohjason.minari.screens.news

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.navigation.bottombar.Screen

@Composable
fun News(navController: NavController) {
    BackHandler(onBack = {
        navController.popBackStack(Screen.Home.rout, inclusive = false)
    })
    val tag = listOf("금융", "증권", "산업/재계", "부동산", "글로벌 경제")
    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            SwipeNews()
        }

//        item {
//            LazyRow(
//                horizontalArrangement = Arrangement.spacedBy(10.dp)
//            ) {
//                items(tag) { item ->
//                    Box(
//                        modifier = Modifier
//                            .clip(CircleShape)
//                            .background(Color.Gray)
//                            .padding(vertical = 3.dp, horizontal = 10.dp)
//                    ) {
//                        Text(text = item, modifier = Modifier.align(Alignment.Center))
//                    }
//                }
//            }
//        }

        items(10) { item ->
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(modifier = Modifier
                    .size(width = 110.dp, height = 70.dp)
                    .clip(RoundedCornerShape(15.dp))
                    .background(Color.Blue)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "woiejfwe")
            }
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
private fun SwipeNews() {
    val pagerState = rememberPagerState()
    HorizontalPager(
        count = 10, // 아이템 개수
        state = pagerState,
        itemSpacing = 20.dp // 페이지 간의 간격을 설정
    ) { page ->
        // 페이지 내용
        Row {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color.LightGray),
            ) {
                Text(text = "$page", modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    News(navController = rememberNavController())
}