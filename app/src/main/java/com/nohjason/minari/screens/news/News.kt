package com.nohjason.minari.screens.news

import android.content.Intent
import android.net.Uri
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
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
import com.nohjason.minari.ui.theme.pretendard_bold

@Composable
fun News(
    navController: NavController,
    newsViewModel: NewsViewModel = viewModel()
) {
    val context = LocalContext.current
    val getAllNews by newsViewModel.getAllNews.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    var category by remember { mutableStateOf("security") }

    LaunchedEffect(Unit, category) {
        newsViewModel.getAllNews(token, category)
    }
    BackHandler(onBack = {
        navController.popBackStack(BottomScreen.Home.rout, inclusive = false)
    })

    LazyColumn(
        modifier = Modifier
            .background(Color(0xFFF5F6FA))
            .padding(top = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            SwipeNews()
        }
        item {
            val tagList = mapOf(
                "증권" to "securities",
                "금융" to "finance",
                "경제 일반" to "economy",
                "부동산" to "realEstate",
                "산업/재계" to "industrialBusiness"
            )
            var selectedTag by remember { mutableStateOf(tagList["증권"]) } // LazyRow 바깥으로 이동

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                item {
                    Spacer(modifier = Modifier.width(10.dp))
                }
                items(tagList.toList()) { tag ->
                    fun onTagClick(clickedTag: String) {
                        if (selectedTag != clickedTag) {
                            selectedTag = clickedTag
                        }
                    }
                    val isSelected = selectedTag == tag.second
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
                                onTagClick(tag.second)
                                category = tag.second
                            }
                            .padding(vertical = 5.dp, horizontal = 15.dp)
                    ) {
                        Text(
                            text = tag.first,
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
        if (getAllNews != null) {
            items(getAllNews!!.data) { item ->
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