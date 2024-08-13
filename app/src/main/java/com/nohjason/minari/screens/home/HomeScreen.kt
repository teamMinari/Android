package com.nohjason.minari.screens.home

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.Locale

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
                val animatedValue = remember { Animatable(0f) }

                // 특정 값으로 색을 채우는 Animation
                LaunchedEffect(Unit) {
                    animatedValue.animateTo(
                        targetValue = 360 * 0.5f,
                        animationSpec = tween(durationMillis = 2000, easing = LinearEasing),
                    )
                }
                Column {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
//                        .weight(0.3f)
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
                                Canvas(
                                    modifier = Modifier.size(130.dp),
                                ) {
                                    val sizeArc = size / 1.75F
                                    drawArc(
                                        color = Color(0xFFE1E2E9),
                                        startAngle = 0f,
                                        sweepAngle = 360f,
                                        useCenter = false,
                                        topLeft = Offset(
                                            (size.width - sizeArc.width) / 2f,
                                            (size.height - sizeArc.height) / 2f
                                        ),
                                        size = sizeArc,
                                        style = Stroke(width = 25f)
                                    )

                                    drawArc(
                                        color = MinariBlue,
                                        startAngle = 100f,
                                        sweepAngle = animatedValue.value,
                                        useCenter = false,
                                        topLeft = Offset(
                                            (size.width - sizeArc.width) / 2f,
                                            (size.height - sizeArc.height) / 2f
                                        ),
                                        size = sizeArc,
                                        style = Stroke(width = 25f, cap = StrokeCap.Round)
                                    )
                                }
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
                    AttendanceCalendar()
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
private fun AttendanceCalendar() {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Color.White)
            .padding(vertical = 15.dp, horizontal = 20.dp)
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.mage_calendar),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(10.dp))
                Text(
                    text = "출석체크",
                    fontSize = 15.sp,
                    fontFamily = pretendard_semibold
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Icon(
                    modifier = Modifier
                        .rotate(180f)
                        .size(15.dp),
                    painter = painterResource(id = R.drawable.befor_arrow),
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            val currentWeek = remember { getCurrentWeek() }
            val today = LocalDate.now()
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                currentWeek.forEach { date ->
                    val backgroundColor = if (date == today) Color.Blue else Color.Transparent
                    Box(
                        modifier = Modifier
                            .size(40.dp)
                            .clip(CircleShape)
                            .background(color = backgroundColor),
                    ) {
                        Text(
                            text = date.dayOfMonth.toString(),
                            modifier = Modifier.align(Alignment.Center),
                            fontFamily = pretendard_semibold,
                            fontSize = 17.sp,
                            color = if (date == today) Color.White else Color.Black
                        )
                    }
                }
            }

        }
    }
}

fun getCurrentWeek(): List<LocalDate> {
    val today = LocalDate.now()
    val weekFields = WeekFields.of(Locale.getDefault())
    val firstDayOfWeek = today.with(weekFields.dayOfWeek(), 1)
    return List(7) { i -> firstDayOfWeek.plusDays(i.toLong()) }
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