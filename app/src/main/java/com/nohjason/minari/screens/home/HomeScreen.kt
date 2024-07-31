package com.nohjason.minari.screens.home

import android.annotation.SuppressLint
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.FractionalThreshold
import androidx.compose.material.rememberSwipeableState
import androidx.compose.material.swipeable
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
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
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
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_semibold
import java.text.SimpleDateFormat
import java.util.Date
import kotlin.math.roundToInt

@OptIn(ExperimentalMaterial3Api::class, ExperimentalMaterialApi::class, ExperimentalPagerApi::class)
@Composable
fun HomeScreen(
    navController: NavController,
) {
//    val now = System.currentTimeMillis()
//    val date = Date(now)
//    val sdf = SimpleDateFormat("yyyy-MM-dd")
//    val getTime = sdf.format(date)
//
//    LaunchedEffect(key1 = Unit) {
//        Log.d("TAG", "HomeScreen: $getTime")
//    }


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
                Box(
                    modifier = Modifier.height(200.dp)
                ) {
                    Column {
                        Box(
                            modifier = Modifier
                                .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                                .weight(0.3f)
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
                                    contentDescription = null
                                )
                                Column(
                                    horizontalAlignment = Alignment.CenterHorizontally
                                ) {
                                    Text(
                                        modifier = Modifier.padding(5.dp),
                                        text = "경제의 시작"
                                    )
                                    Box(
                                        modifier = Modifier
                                            .size(70.dp)
                                            .clip(CircleShape)
                                            .border(4.dp, MinariBlue, CircleShape)
                                    ) {
                                        Text(
                                            modifier = Modifier
                                                .align(Alignment.Center),
                                            text = "100%"
                                        )
                                    }
                                }
                            }
                        }
                        Spacer(modifier = Modifier.height((55 / 2).dp))
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
                            .height(55.dp)
                            .background(
                                brush = Brush.linearGradient(
                                    colors = MinariGradation,
                                    start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                                    end = androidx.compose.ui.geometry.Offset(0f, 0f),
                                )
                            )
                            .align(Alignment.BottomCenter)
                            .padding(horizontal = 70.dp)
                            .clickable { }
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
                    modifier = Modifier.padding(25.dp)
                ) {
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
                            LazyRow(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalArrangement = Arrangement.SpaceBetween
                            ) {
                                items(7) { item ->
                                    Text(
                                        text = "$item",
                                        fontSize = 17.sp,
                                        fontFamily = pretendard_bold
                                    )
                                }
                            }
                        }
                    }
                }
            }
            item {
                val pagerState = rememberPagerState()

                HorizontalPager(
                    count = 10, // 아이템 개수
                    state = pagerState
                ) { page ->
                    // 페이지 내용
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 25.dp)
                            .clip(RoundedCornerShape(20.dp))
                            .fillMaxWidth()
                            .height(150.dp)
                            .background(Color.LightGray)
                    ) {
                        Text(text = "$page", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
            item {
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
//                        LazyColumn(verticalArrangement = Arrangement.spacedBy(20.dp)) {
//                            items(20) {
//                                Box(
//                                    modifier = Modifier
//                                        .height(30.dp)
//                                        .fillMaxWidth()
//                                        .background(Color.Black)
//                                )
//                            }
//                        }
                    }
                }
            }
        }
    }
}

//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(Color.White)
//    ) {
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//            Row(
//                verticalAlignment = Alignment.CenterVertically,
//                modifier = Modifier.padding(10.dp)
//            ) {
//                Icon(
//                    painter = painterResource(id = R.drawable.grape),
//                    contentDescription = null,
//                    tint = Color.Unspecified,
//                    modifier = Modifier.size(35.dp)
//                )
//                MinariText(text = "청For도", size = 20)
//                Spacer(modifier = Modifier.fillMaxWidth())
//            }
//
//            MinariTextField(
//                modifier = Modifier
//                    .height(30.dp)
//                    .padding(horizontal = 30.dp)
//                    .fillMaxWidth()
//                    .background(Color.Transparent, shape = CircleShape),
//                value = text,
//                onValueChange = { text = it },
//                onClick = { if (text.isNotEmpty()) navController.navigate("test/${text}") }
//            )
//
//            Box(
//                modifier = Modifier
//                    .padding(top = 10.dp)
//                    .fillMaxSize()
//                    .background(MinariWhite),
//            ) {
//                Column(
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp, vertical = 10.dp)
//                        .fillMaxSize(),
//                    horizontalAlignment = Alignment.CenterHorizontally
//                ) {
//                    RecommendWords(navController = navController)
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                    Row(
//                        modifier = Modifier.fillMaxWidth(),
//                        horizontalArrangement = Arrangement.SpaceBetween
//                    ) {
//                        NewsCategoryButton(
//                            image = painterResource(id = R.drawable.img_house),
//                            onClick = {},
//                            title = "부동산"
//                        )
//
//                        NewsCategoryButton(
//                            image = painterResource(id = R.drawable.img_chart),
//                            onClick = {},
//                            title = "증권"
//                        )
//
//                        NewsCategoryButton(
//                            image = painterResource(id = R.drawable.img_coin),
//                            onClick = {},
//                            title = "금융"
//                        )
//
//                        NewsCategoryButton(
//                            image = painterResource(id = R.drawable.img_folder),
//                            onClick = {},
//                            title = "채권"
//                        )
//                    }
//
//                    Spacer(modifier = Modifier.height(20.dp))
//                    News()
//                }
//            }
//        }
//    }

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