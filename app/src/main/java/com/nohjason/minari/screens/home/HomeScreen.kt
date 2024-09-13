package com.nohjason.minari.screens.home

import android.content.Intent
import android.net.Uri
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
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.AsyncImage
//import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.profile.ProfileViewModel
import com.nohjason.minari.screens.profile.element.RewardBar
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavController,
    viewModel: GrapeViewModel = viewModel(),
    profileViewModel: ProfileViewModel = viewModel()
) {
    var text by remember { mutableStateOf("") }
    val scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior(rememberTopAppBarState())
    val context = LocalContext.current
    var backPressedTime by rememberSaveable { mutableStateOf(0L) }
    val data by profileViewModel.profileData.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")

    LaunchedEffect(Unit) {
        profileViewModel.getProfile(token)
    }

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
                            .background(Color(0xFFF6F6F6))
                            .padding(6.dp),
                        value = text,
                        onValueChange = { text = it },
                        onClick = {
                            viewModel.getTerm(token, text)
                            navController.navigate("test/${text}")
                        }
                    )
                },
                scrollBehavior = scrollBehavior,
            )
        }
    ) { innerPadding ->
        var itemCount by remember { mutableStateOf(5) }
        val allItems = remember { List(20) { index -> "Item ${index + 1}" } }
        val items = allItems.take(itemCount)

        val selectedItems = remember { mutableStateListOf<Int>() }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
                .padding(innerPadding),
            horizontalAlignment = Alignment.CenterHorizontally
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
                Spacer(modifier = Modifier.height(10.dp))
                if (data != null) {
                    val percentage = (50 / 100f)//exp구현 시 변경해야함
                    RewardBar(
                        progress = percentage,
                        xp = data!!.exp,
                        level = data!!.level
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
            items(5) { item ->
                Box(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .background(Color.White)
                        .padding(horizontal = 20.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.padding(horizontal = 20.dp, vertical = 10.dp)
                    ) {
                        Text(
                            text = "가산금리",
                            fontSize = 17.sp,
                            fontFamily = pretendard_medium
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                        for (i in 1..2) {
                            Icon(
                                painter = painterResource(id = R.drawable.star),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(13.dp),
                                tint = Color.Unspecified
                            )
                        }
                        Spacer(modifier = Modifier.weight(0.1f))
                        Icon(
                            painter = painterResource(id = R.drawable.book_mark),
                            contentDescription = null,
                            tint = Color.Unspecified
                        )
                    }
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

data class ShowNews(
    val img: String,
    val time: String,
    val title: String,
    val link: String
)
