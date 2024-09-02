package com.nohjason.minari.screens.rout

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Grape(navController: NavController) {
    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = "모르겠구여",
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
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            item {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                ) {
                    Spacer(modifier = Modifier.height(20.dp))
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
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = "소개",
                            fontFamily = pretendard_bold,
                            fontSize = 23.sp
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        Text(text = "2분")
                        Spacer(modifier = Modifier.weight(0.1f))
                        Icon(
                            painter = painterResource(id = R.drawable.my_words),
                            contentDescription = null,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                ) {
                    Text(
                        text = "청소년 경제를 알아야하는 이유를 알아봅시다.",
                        fontSize = 15.sp,
                        fontFamily = pretendard_semibold
                    )
                    Text(
                        text = "경제란 무엇일까요?\n\n" +
                                "경제란 인간의 공동생활을 위한 물적 기초가 되는 재화와 용역을 생산 · 분배 · 소비하는 활동과 그것을 통하여 형성되는 사회관계의 총체를 가리키는 용어입니다. [1](링크 테그 시키기)\n" +
                                "\n" +
                                "쉽게 말하면 경제란 돈의 흐름 그 자체라고 할 수 있습니다.\n\n자본주의 사회의 정부는  정부가 지원하는 나이별 혜택을 제공합니다.\n\n" +
                                "청년 도약 계좌 \n" +
                                "ISA 계좌\n" +
                                "부모 급여 / ISA계좌 ..etc\n" +
                                "\n" +
                                "이렇게 많은 제도들을 활용하여 돈을 저축하기만 하는것이 아니라\n" +
                                "현명하게 불릴 수 있는 능력이 있어야 합니다.\n\n" +
                                "이번 포도송이 에서는 경제에 대한 기본지식도 배우지만, 뱡금 같은 예시처럼 스스로 자산을 운용할 수 있는 기초지식을 중점으로 다룹니다.",
                        fontSize = 13.sp,
                        fontFamily = pretendard_medium
                    )
                }
            }
            item {
                Column(
                    modifier = Modifier
                        .background(Color.White)
                        .fillMaxWidth()
                        .padding(horizontal = 25.dp, vertical = 10.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(Color(0xFFF3F4F6))
                            .padding(vertical = 10.dp, horizontal = 23.dp)
                    ) {
                        Column {
                            Text(
                                text = "Quiz.",
                                fontFamily = pretendard_bold,
                                fontSize = 20.sp
                            )
                            Text(
                                text = "제목 제목제목 제 제목 제목 제목제",
                                fontFamily = pretendard_semibold,
                                fontSize = 13.sp
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Row {
                                Box(
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .height(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFFF6D0D1))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Close,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .align(Alignment.Center),
                                    )
                                }

                                Spacer(modifier = Modifier.width(10.dp))

                                Box(
                                    modifier = Modifier
                                        .weight(0.5f)
                                        .height(40.dp)
                                        .clip(RoundedCornerShape(10.dp))
                                        .background(Color(0xFFDFE8F6))
                                ) {
                                    Icon(
                                        imageVector = Icons.Default.Done,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxSize()
                                            .align(Alignment.Center)
                                    )
                                }
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(100.dp))
                }
            }
        }
    }
}

@Preview
@Composable
private fun Test() {
    Grape(navController = rememberNavController())
}