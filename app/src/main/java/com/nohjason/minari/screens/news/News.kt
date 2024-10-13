package com.nohjason.minari.screens.news

import android.content.Intent
import android.net.Uri
import android.nfc.Tag
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.home.SwipeNews
import com.nohjason.minari.screens.home.drawColoredShadow
import com.nohjason.minari.ui.theme.pretendard_bold

@Composable
fun News(
    navController: NavController,
    newsViewModel: NewsViewModel = viewModel()
) {
    val context = LocalContext.current
    val getallNews by newsViewModel.getAllNews.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    var category by remember { mutableStateOf("main") }
    LaunchedEffect(Unit, category) {
        newsViewModel.getAllNews(token, category)
    }
    BackHandler(onBack = {
        navController.popBackStack(BottomScreen.Home.rout, inclusive = false)
    })
    LazyColumn(
        modifier = Modifier.padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            SwipeNews()
        }
        item {
            val tagList = listOf("main", "HOT_NEWS", "산업/재계", "부동산", "글로벌 경제")
            var selectedTag by remember { mutableStateOf<String?>(tagList[0]) } // LazyRow 바깥으로 이동

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(10.dp))
                }
                items(tagList) { tag ->
                    // 태그 선택 처리 함수
                    fun onTagClick(clickedTag: String) {
                        // 클릭한 태그가 현재 선택된 태그와 동일한 경우 아무 동작도 하지 않음
                        if (selectedTag != clickedTag) {
                            selectedTag = clickedTag
                        }
                    }

                    val isSelected = selectedTag == tag

                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .border(
                                1.dp,
                                if (isSelected) Color(0x00000000) else Color(0xFFECEFFB),
                                shape = CircleShape
                            )
                            .background(if (isSelected) Color.Blue else Color.White)
                            .clickable {
                                onTagClick(tag)
                                category = tag
                            }
                            .padding(vertical = 5.dp, horizontal = 15.dp)
                    ) {
                        Text(
                            text = tag,
                            color = if (isSelected) Color.White else Color.Black,
                            fontSize = 13.sp,
                            fontFamily = pretendard_bold
                        )
                    }
                }
                item {
                    Spacer(modifier = Modifier.width(10.dp))
                }
            }
        }
        if (getallNews != null) {
            items(getallNews!!.data) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                            context.startActivity(intent)
                        }
                        .padding(horizontal = 20.dp),
                ) {
                    Box(
                        modifier = Modifier
                            .height(70.dp)
//                                .padding(horizontal = 20.dp)
                            .clip(RoundedCornerShape(10.dp))
                    ) {
                        AsyncImage(
                            model = item.thumbnail,
                            contentDescription = null,
                            contentScale = ContentScale.Crop,
                            modifier = Modifier.width(110.dp)
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(text = item.title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        } else {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    News(navController = rememberNavController())
}