package com.nohjason.minari.screens.rout

import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold

@Composable
fun Grape() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MinariWhite),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                    .background(Color.White)
                    .padding(10.dp)
            ) {
                Image(painter = painterResource(id = R.drawable.o), contentDescription = null)
                Row {
                    Text(text = "경제 시작하기")
                    Icon(
                        painter = painterResource(id = R.drawable.my_words),
                        contentDescription = null
                    )
                }
                LazyRow {
                    items(5) { item ->
                        Text(text = "$item")
                    }
                }
                Text(text = "1%의 고등학생만 어쩌고 저쩌고")
                Text(text = "시작하면 알기 좋은 지식")
                Text(text = "없음")
            }
        }

        items(5) {
//            Box(modifier = Modifier.padding(horizontal = 10.dp)) {
//                Column(
//                    modifier = Modifier
//                        .fillMaxWidth()
//                        .clip(RoundedCornerShape(20.dp))
//                        .background(Color.White)
//                        .padding(10.dp)
//                ) {
//                    Row {
//                        Spacer(modifier = Modifier.weight(0.1f))
//                        Box(
//                            modifier = Modifier
//                                .width(50.dp)
//                                .padding(horizontal = 3.dp)
//                                .clip(CircleShape)
//                                .background(Color(0xFF5DC067))
//                        ) {
//                            Text(
//                                text = "300xp",
//                                fontSize = 10.sp,
//                                fontFamily = pretendard_bold,
//                                color = Color.White,
//                                modifier = Modifier.align(Alignment.Center)
//                            )
//                        }
//                    }
//                    Row(
//                        verticalAlignment = Alignment.CenterVertically
//                    ) {
//                        Icon(
//                            painter = painterResource(id = R.drawable.o),
//                            contentDescription = null,
//                            modifier = Modifier.size(30.dp)
//                        )
//                        Column {
//                            Text(text = "시작하기")
//                            Text(text = "20분-포도알-0/3포도씨")
//                        }
//                    }
//                }
//            }
            var isExpanded by remember { mutableStateOf(false) }
            val boxHeight by animateDpAsState(targetValue = if (isExpanded) 200.dp else 60.dp)

            Box(
                modifier = Modifier.padding(horizontal = 10.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(20.dp))
                        .clickable { isExpanded = !isExpanded } // 클릭 시 확장 상태 토글
                        .background(Color.White)
                        .padding(10.dp)
                        .height(boxHeight) // 높이를 애니메이션으로 적용
                ) {
                    Row {
                        Spacer(modifier = Modifier.weight(0.1f))
                        Box(
                            modifier = Modifier
                                .width(50.dp)
                                .padding(horizontal = 3.dp)
                                .clip(CircleShape)
                                .background(Color(0xFF5DC067))
                        ) {
                            Text(
                                text = "300xp",
                                fontSize = 10.sp,
                                fontFamily = pretendard_bold,
                                color = Color.White,
                                modifier = Modifier.align(Alignment.Center)
                            )
                        }
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.o),
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Column {
                            Text(text = "시작하기")
                            Text(text = "20분-포도알-0/3포도씨")
                        }
                    }
                    // Box가 확장되었을 때만 보이는 내용
                    if (isExpanded) {
                        Spacer(modifier = Modifier.height(10.dp))
                        Column {
                            Text(text = "소개 (2분)")
                            Text(text = "10대 - 주니어투자상품(청소년 주식 ...) (6분)")
                            Text(text = "10대 - 경제 관련 서비스 (2분)")
                            Text(text = "20대 - 청년 내일 채움 공제 (7분)")
                            Text(text = "20대 - NH투자증권 (5분)")
                            Text(text = "20대 - 신한은행 청년전용 금융 교육 ... (7분)")
                        }
                    }
                }
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Grape()
}