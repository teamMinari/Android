package com.nohjason.minari.screens.rout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
//import androidx.compose.foundation.layout.FlowRowScopeInstance.align
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
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
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Grapes(navController: NavController) {
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = "경제 시작하기",
                        fontFamily = pretendard_bold,
                        fontSize = 23.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.befor_arrow),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MinariWhite)
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                        .background(Color.White)
                        .padding(horizontal = 30.dp, vertical = 15.dp)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.first_grape),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "경제 시작하기",
                            fontFamily = pretendard_bold,
                            fontSize = 23.sp,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Icon(
                            painter = painterResource(id = R.drawable.my_words),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp).clickable {  },
                            tint = Color.Gray,
                        )
                    }
                    Text(
                        text = "00분 - 포도송이 - 0/3포도알",
                        fontFamily = pretendard_regular
                    )
                    val tag = listOf("초급", "첫 시작", "고등학생", "경제 제도")
                    LazyRow(
                        horizontalArrangement = Arrangement.spacedBy(3.dp)
                    ) {
                        items(tag) { item ->
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(MinariBlue)
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = item,
                                    color = Color.White,
                                    fontFamily = pretendard_medium,
                                    fontSize = 12.sp
                                )
                            }
                        }
                    }
                    Text(
                        text = "\"1%의 고등학생만 배우는 과목\", 경제를 실용적으로 배우기 위해 이론 보단 실습으로 적용 할 수 있는 경제 지식을 알아 봅니다",
                        fontFamily = pretendard_regular,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(vertical = 30.dp)
                    )
                    Column(modifier = Modifier.padding(vertical = 5.dp)) {
                        Text(
                            text = "시작하기전 알면 좋은 배경지식",
                            fontFamily = pretendard_semibold,
                            fontSize = 17.sp
                        )
                        Text(
                            text = "없음",
                            fontFamily = pretendard_regular,
                            fontSize = 13.sp
                        )
                    }
                }
            }

            items(5) {
                Gpse(navController)
            }
        }
    }
}

@Composable
fun Gpse(navController: NavController) {
    var isExpanded by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable { isExpanded = !isExpanded } // 클릭 시 확장 상태 토글
                .background(Color.White)
                .padding(vertical = 10.dp, horizontal = 20.dp)
        ) {
            Row {
                Spacer(modifier = Modifier.weight(0.1f))
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color(0xFF5DC067))
                ) {
                    Text(
                        text = "300xp",
                        fontSize = 10.sp,
                        fontFamily = pretendard_bold,
                        color = Color.White,
                        modifier = Modifier
                            .align(Alignment.Center)
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    )
                }
            }
            Row {
                Image(
                    painter = painterResource(id = R.drawable.ellipes),
                    contentDescription = null,
                    modifier = Modifier.size(45.dp)
                )
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = "시작하기: 우리가 경제를 배워야 하는 이유는 그딴거 없다",
                        fontFamily = pretendard_bold,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "20분-포도알-0/3포도씨",
                        fontFamily = pretendard_regular,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.my_words),
                    contentDescription = null,
                    tint = Color.Gray,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(20.dp)
                        .clickable {  }
                )
            }

            // Box가 확장되었을 때만 보이는 내용
            if (isExpanded) {
                Spacer(modifier = Modifier.height(10.dp))
                Column {
                    Text(text = "소개 (2분)")
                    Text(
                        text = "10대 - 주니어투자상품(청소년 주식 ...) (6분)",
                        modifier = Modifier.clickable { navController.navigate(Test.Grape.rout) }
                    )
                    Text(text = "10대 - 경제 관련 서비스 (2분)")
                    Text(text = "20대 - 청년 내일 채움 공제 (7분)")
                    Text(text = "20대 - NH투자증권 (5분)")
                    Text(text = "20대 - 신한은행 청년전용 금융 교육 ... (7분)")
                }
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.befor_arrow),
                    contentDescription = null,
                    modifier = Modifier
                        .rotate(if (isExpanded) 90f else -90f)
                        .size(15.dp)
                        .align(Alignment.BottomCenter),
                    tint = Color.Gray
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Grapes(rememberNavController())
}