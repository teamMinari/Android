package com.nohjason.minari.screens.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
    var text by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    var backPressedTime by rememberSaveable { mutableStateOf(0L) }

    BackHandler(onBack = {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000) {
            (context as ComponentActivity).finish()
        } else {
            Toast.makeText(context, "Press back again to exit", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    })

    Scaffold(
        modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = Color.White,
//                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    MinariTextField(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.LightGray)
                            .padding(6.dp),
                        value = text,
                        onValueChange = { text = it }
                    ) {

                    }
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .background(Color(0xFFF5F6FA))
        ) {
            item {
                Column {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Row(
                            modifier = Modifier
                                .align(Alignment.Center),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                modifier = Modifier.size(140.dp),
                                painter = painterResource(R.drawable.image_27),
                                contentDescription = null,
                            )
                            Column(
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "경제의 시작"
                                )
                            }
                        }
                    }
                    Box(
                        modifier = Modifier
                            .drawColoredShadow(
                                color = Color.Black,
                                alpha = 0.3f,
                                borderRadius = 100.dp,
                                shadowRadius = 8.dp,
                                offsetX = 5.dp,
                                offsetY = 5.dp
                            )
                            .clip(CircleShape)
                            .clickable { }
                            .height(55.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = MinariGradation,
                                    start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                                    end = androidx.compose.ui.geometry.Offset(0f, 0f),
                                )
                            )
//                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 70.dp)
                            .align(Alignment.CenterHorizontally)
                    ) {
                        Text(
                            modifier = Modifier
                                .align(Alignment.Center),
                            text = "학습하러 가기",
                            color = Color.White,
                            fontSize = 20.sp,
                            fontFamily = pretendard_semibold
                        )
                    }
                }
            }
            item {
                Column(
                    modifier = Modifier.padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    XpBar(
                        currentXp = 100,
                        maxXp = 400,
                        modifier = Modifier.padding(10.dp)
                    )
                }
            }
            item {
                SwipeNews()
            }
            item {
                Column(
                    modifier = Modifier.padding(25.dp),
                    verticalArrangement = Arrangement.spacedBy(20.dp)
                ) {
                    TodayWords()
                }
            }
        }
    }
}

@Composable
fun XpBar(
    currentXp: Int,
    maxXp: Int,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(15.dp))
            .background(Color.White)
            .padding(10.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.present),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text("보상", fontFamily = pretendard_semibold, fontSize = 15.sp)
            }
            Row(
                modifier = modifier
                    .height(40.dp)
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .border(1.dp, Color(0xFFECEFFB), shape = RoundedCornerShape(20.dp)),
                verticalAlignment = Alignment.CenterVertically
            ) {
                val safeCurrentScore = currentXp.coerceAtMost(maxXp)

                // 백분율 계산 (0.0f ~ 1.0f 사이의 값)
                val percentage = if (maxXp > 0) safeCurrentScore.toFloat() / maxXp.toFloat() else 0f

                // 나머지 비율 계산
                val remainingPercentage = 1f - percentage
                Box(
                    modifier = Modifier
                        .weight(percentage)
                        .width(50.dp)
                        .fillMaxHeight()
                        .clip(CircleShape)
                        .background(Color(0xFF4169E1)),  // Royal Blue
                    contentAlignment = Alignment.Center
                ) {
                    Text(
                        text = "${percentage * 100}%",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Spacer(modifier = Modifier.weight(remainingPercentage))

//                Spacer(modifier = Modifier.weight()
                Box(
                    modifier = Modifier
                        .padding(4.dp)
                        .size(32.dp)
                        .clip(CircleShape)
                        .border(2.dp, Color(0xFF4169E1), CircleShape)
                        .background(Color.White),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.grape),
                        contentDescription = "test",
                        modifier = Modifier.size(20.dp),
                        tint = Color.Unspecified
                    )

                }
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
            Spacer(modifier = Modifier.width(25.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .weight(0.1f)
                    .height(150.dp)
                    .background(Color.LightGray),
            ) {
                Text(text = "$page", modifier = Modifier.align(Alignment.Center))
            }
            Spacer(modifier = Modifier.width(25.dp))
        }
    }
}

@Composable
private fun TodayWords() {
    var array by remember {
        mutableStateOf(
            mutableListOf(
                "가산금리",
                "추가경정예산",
                "금리",
                "추정손실"
            )
        )
    }
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.mdi_cards),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(
                    text = "오늘의 경제 단어",
                    fontFamily = pretendard_semibold,
                    fontSize = 17.sp
                )
            }
            EconomyTerm(list = array)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clickable {
                        array = array
                            .toMutableList()
                            .apply {
                                repeat(3) {
                                    add("test${size + 1}")
                                }
                            }
                    }
            ) {
                Text(
                    text = "더보기+",
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun EconomyTerm(list: MutableList<String>) {
    list.forEach {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(vertical = 10.dp),
                    fontFamily = pretendard_medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Icon(
                    painter = painterResource(id = R.drawable.minari_hart),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

fun Modifier.drawColoredShadow(
    color: Color,
    alpha: Float = 0.2f,
    borderRadius: Dp = 0.dp,
    shadowRadius: Dp = 20.dp,
    offsetY: Dp = 0.dp,
    offsetX: Dp = 0.dp
) = this.drawBehind {
    val transparentColor = android.graphics.Color.toArgb(color.copy(alpha = 0.0f).value.toLong())
    val shadowColor = android.graphics.Color.toArgb(color.copy(alpha = alpha).value.toLong())
    this.drawIntoCanvas {
        val paint = Paint()
        val frameworkPaint = paint.asFrameworkPaint()
        frameworkPaint.color = transparentColor
        frameworkPaint.setShadowLayer(
            shadowRadius.toPx(),
            offsetX.toPx(),
            offsetY.toPx(),
            shadowColor
        )
        it.drawRoundRect(
            0f,
            0f,
            this.size.width,
            this.size.height,
            borderRadius.toPx(),
            borderRadius.toPx(),
            paint
        )
    }
}


@Preview(showSystemUi = true)
@Composable
fun PreviewHome() {
    HomeScreen(navController = rememberNavController())
}