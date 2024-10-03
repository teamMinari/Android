package com.nohjason.minari.screens.home

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@OptIn(ExperimentalPagerApi::class)
@Composable
fun SwipeNews() {
    val context = LocalContext.current
    val pagerState = rememberPagerState()
    val list: List<ShowNews> = listOf(
        ShowNews(
            "https://imgnews.pstatic.net/image/032/2024/09/09/0003320025_001_20240909212314102.jpg?type=w647",
            "오후 9:00",
            "차·포 뗀 법안에도 플랫폼 업계는 “과하다”…사후 규제엔 ‘반색’",
            "https://n.news.naver.com/mnews/article/032/0003320025"
        ),
        ShowNews(
            "https://imgnews.pstatic.net/image/009/2024/09/09/0005363178_001_20240909213611393.jpg?type=w647",
            "오후 9:26",
            "“전기료 이게 맞아?” 집집마다 난리…역대급 폭염의 뒤끝, 서늘하네",
            "https://n.news.naver.com/mnews/article/009/0005363178"
        )
    )
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
                model = news.img,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(news.link))
                        context.startActivity(intent)
                    },
            )
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
                    text = news.time,
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