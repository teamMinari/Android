package com.nohjason.minari.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.news.NewsViewModel
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeNews(
    newsViewModel: NewsViewModel = viewModel()
) {
    val getHotNews by newsViewModel.getAllNews.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    LaunchedEffect(Unit) {
        newsViewModel.getAllNews(token, "HOT_NEWS")
    }
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    if (getHotNews != null) {
        val list = getHotNews!!.data
        HorizontalPager(
            count = list.size, // 아이템 개수
            state = pagerState,
            itemSpacing = 20.dp // 페이지 간의 간격을 설정
        ) { page ->
            val news = list[page]
            // 페이지 내용
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .padding(horizontal = 20.dp)
                    .drawColoredShadow(
                        color = Color.Black,
                        alpha = 0.3f,
                        borderRadius = 10.dp,
                        shadowRadius = 8.dp,
                        offsetX = 5.dp,
                        offsetY = 5.dp
                    )
                    .clip(RoundedCornerShape(10.dp))
            ) {
                AsyncImage(
                    model = news.thumbnail,
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.url))
                            context.startActivity(intent)
                        },
                )
                Box(
                    modifier = Modifier
                        .background(
                            brush = Brush.verticalGradient(
                                colors = listOf(
                                    Color(0x33D9D9D9),  // 시작 색상
                                    Color(0xFF000000)   // 끝 색상
                                )
                            )
                        )
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp)
                            .align(Alignment.BottomCenter)
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .border(1.dp, Color.White, CircleShape)
                                .padding(horizontal = 5.dp)
                        ) {
                            Text(
                                text = "🔥HOT",
                                fontSize = 13.sp,
                                fontFamily = pretendard_semibold,
                                color = Color.White
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        Text(
                            text = "${news.uploadTime}",
                            color = Color.White,
                            fontSize = 10.sp,
                            fontFamily = pretendard_regular
                        )
                        Text(
                            text = news.title,
                            color = Color.White,
                            fontSize = 15.sp,
                            fontFamily = pretendard_bold,
                            maxLines = 2,
                            overflow = TextOverflow.Ellipsis
                        )
                    }
                }
            }
        }
    } else {
        androidx.compose.material3.CircularProgressIndicator()
    }
}