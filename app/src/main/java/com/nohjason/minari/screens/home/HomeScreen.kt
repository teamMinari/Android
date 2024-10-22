package com.nohjason.minari.screens.home

import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableLongStateOf
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
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
//import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.home.todayterm.TodayTerm
import com.nohjason.minari.screens.home.todayterm.getRandomItems
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.news.SwipeNews
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.profile.profile_element.RewardBar
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.MinariGray
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold
import com.nohjason.myapplication.network.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    grapeViewModel: GrapeViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
) {
    val context = LocalContext.current
    var backPressedTime by rememberSaveable { mutableLongStateOf(0L) }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val data by profileViewModel.profileData.collectAsState()
    val getAllTerms by mainViewModel.getAllTerms.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val gps by grapeViewModel.gpsDetail.collectAsState()

    LaunchedEffect(Unit) {
        profileViewModel.getProfile(token)
        mainViewModel.getAllTerms(token, 0, 1000)
    }

    BackHandler(onBack = {
        val currentTime = System.currentTimeMillis()
        if (currentTime - backPressedTime < 2000) {
            (context as ComponentActivity).finish()
        } else {
            Toast.makeText(context, "한 번 더 누르면 앱을 끌 수 있어요'", Toast.LENGTH_SHORT).show()
            backPressedTime = currentTime
        }
    })

    Scaffold(modifier = Modifier.nestedScroll(scrollBehavior.nestedScrollConnection), topBar = {
        CenterAlignedTopAppBar(
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = Color.White,
            ),
            title = {
                SearchBar(navController = navController)
            },
            scrollBehavior = scrollBehavior,
        )
    }) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                val gpsId = preferences.getInt("gpsId", 0)
                LaunchedEffect(key1 = Unit, gps) {
                    grapeViewModel.getGps(token = token, gpsId = gpsId)
                }
//                var size = 0.0f
//                if (gps != null) {
////                    Log.d("TAG", "HomeScreen: ${(gps!!.data.gpCnt / gps!!.data.gpCntMax)}")
//                    size = gps!!.data.gpCnt / gps!!.data.gpCntMax.toFloat()
//                    Log.d("TAG", "HomeScreen: $size")
//                }

                Box {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(0.dp, 0.dp, 30.dp, 30.dp))
                            .fillMaxWidth()
                            .background(Color.White)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Row(
                                modifier = Modifier.align(Alignment.CenterHorizontally),
                                verticalAlignment = Alignment.CenterVertically,
                                horizontalArrangement = Arrangement.Center
                            ) {
                                Image(
                                    modifier = Modifier.size(140.dp),
                                    painter = painterResource(R.drawable.image_27),
                                    contentDescription = null,
                                )
                                Spacer(modifier = Modifier.width(10.dp))
//                            if (gpsId != 0) {
                                CircularProgressIndicator(
                                    percentage = 0f,
                                    title = if (gps != null) gps!!.data.gpsName else "튜토리얼\n보기",
                                    status = if (gps != null) {
                                        if (gps!!.data.gpCnt == gps!!.data.gpCntMax) {
                                            "학습완료"
                                        } else {
                                            "학습중"
                                        }
                                    } else {
                                        ""
                                    }
                                )
                            }
                            Spacer(modifier = Modifier.height(20.dp))
                        }
                    }
                    Column(
                        modifier = Modifier.align(Alignment.BottomCenter)
                    ) {
                        Spacer(modifier = Modifier.height(130.dp))
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
                                .clickable {
                                    Log.d("TAG", "HomeScreen: $gpsId")
                                    if (gps != null) {
                                        navController.navigate(Screens.Grapes.rout + "/${gpsId}")
                                    } else {
                                        navController.navigate(BottomScreen.Rout.rout)
                                    }
                                }
                                .height(55.dp)
                                .background(
                                    brush = Brush.linearGradient(
                                        colors = MinariGradation,
                                        start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                                        end = androidx.compose.ui.geometry.Offset(0f, 0f),
                                    )
                                )
//                                .align(Alignment.BottomCenter)
                                .padding(horizontal = 70.dp)
//                            .align(Alignment.CenterHorizontally)
                        ) {
                            Text(
                                modifier = Modifier.align(Alignment.Center),
                                text = if (gps != null) "학습하러 가기" else "튜토리얼 보기",
                                color = Color.White,
                                fontSize = 20.sp,
                                fontFamily = pretendard_semibold
                            )
                        }
                    }
                }
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                if (data != null) {
                    val percentage = (50 / 100f)//exp구현 시 변경해야함
                    RewardBar(
                        xp = data!!.exp, level = data!!.level
                    )
                }
                Spacer(modifier = Modifier.height(10.dp))
            }
            item {
                SwipeNews()
            }
            item {
                Spacer(modifier = Modifier.height(10.dp))
                Box(
                    modifier = Modifier
                        .padding(horizontal = 20.dp)
                        .clip(RoundedCornerShape(20.dp, 20.dp, 0.dp, 0.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(20.dp)
                ) {
                    Column {
                        Row {
                            Icon(
                                painter = painterResource(id = R.drawable.mdi_cards),
                                contentDescription = null,
                                tint = Color.Unspecified
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(
                                text = "오늘의 경제 단어",
                                fontFamily = pretendard_semibold,
                                fontSize = 19.sp
                            )
                        }
                    }
                }
            }
            if (getAllTerms != null) {
                val randomItems = getRandomItems(context, getAllTerms!!)
                items(randomItems) { item ->
                    TodayTerm(navController, item = item)
                }
            }
        }
    }
}

@Composable
fun SearchBar(
    placeholderText: String = "검색어를 입력해 주세요",
    modifier: Modifier = Modifier,
    navController: NavController,
    onNavigate: () -> Unit = { navController.navigate(Screens.Search.rout) }
) {
    Row(
        modifier = modifier
            .padding(horizontal = 10.dp)
            .clip(CircleShape)
            .fillMaxWidth()
            .background(Color(0xFFF6F6F6))
            .clickable { onNavigate() }
            .padding(horizontal = 13.dp, vertical = 7.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = placeholderText,
            fontFamily = pretendard_bold,
            fontSize = 15.sp,
            color = Color(0xFF8C8C8C),
        )
        Spacer(modifier = Modifier.weight(0.1f))
        IconButton(
            onClick = {},
            modifier = Modifier.size(30.dp)
        ) {
            Icon(
                imageVector = Icons.Default.Search,
                contentDescription = null,
                modifier = Modifier
                    .clip(CircleShape)
                    .size(25.dp),
                tint = MinariBlue,
            )
        }
    }
}

@Composable
fun CircularProgressIndicator(
    percentage: Float, title: String, status: String = ""
) {
    Box(
        contentAlignment = Alignment.Center,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = title,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Center,
                modifier = Modifier.width(90.dp)
            )
            Spacer(modifier = Modifier.height(10.dp))
            if (status.isNotEmpty()) {
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(MinariBlue)
                ) {
                    Text(
                        text = status,
                        color = Color.White,
                        fontSize = 8.sp,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }
            }
//            Row(
//                verticalAlignment = Alignment.CenterVertically
//            ) {
//                Text(
//                    text = title,
//                    fontSize = 17.sp,
//                    fontWeight = FontWeight.Bold,
//                    textAlign = TextAlign.Center
//                )
//                Spacer(modifier = Modifier.width(10.dp))
//                Box(
//                    modifier = Modifier
//                        .clip(CircleShape)
//                        .background(MinariBlue)
//                ) {
//                    Text(
//                        text = status,
//                        color = Color.White,
//                        fontSize = 8.sp,
//                        modifier = Modifier.padding(horizontal = 8.dp)
//                    )
//                }
//            }
//            Box(
//                contentAlignment = Alignment.Center, modifier = Modifier
//                    .size(100.dp)
//                    .padding(16.dp)
//            ) {
//                Canvas(modifier = Modifier.size(100.dp)) {
//                    drawArc(
//                        color = Color.LightGray,
//                        startAngle = -90f,
//                        sweepAngle = 360f,
//                        useCenter = false,
//                        style = Stroke(width = 15f, cap = StrokeCap.Round)
//                    )
//                    drawArc(
//                        color = Color.Blue,
//                        startAngle = -90f,
//                        sweepAngle = 360f * percentage,
//                        useCenter = false,
//                        style = Stroke(width = 15f, cap = StrokeCap.Round)
//                    )
//                }
//                Text(
//                    text = "${(percentage * 100).toInt()}%",
//                    fontSize = 18.sp,
//                    fontWeight = FontWeight.Bold,
//                    color = MinariBlue
//                )
//            }
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
            shadowRadius.toPx(), offsetX.toPx(), offsetY.toPx(), shadowColor
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
private fun Test() {
    HomeScreen(navController = rememberNavController())
}