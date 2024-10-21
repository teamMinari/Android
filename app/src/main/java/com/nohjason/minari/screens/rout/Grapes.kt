package com.nohjason.minari.screens.rout

import android.util.Log
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
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.traceEventEnd
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.network.response.rout.Grape
import com.nohjason.minari.network.response.rout.GrapeData
import com.nohjason.minari.network.response.rout.GrapeSeedLessData
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.preferences.saveToPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold
import com.nohjason.myapplication.network.MainViewModel

@Composable
fun Grapes(
    navController: NavController,
    viewModel: GrapeViewModel = viewModel(),
    id: Int,
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val gps by viewModel.gpsDetail.collectAsState()
    LaunchedEffect(key1 = Unit, gps) {
        val editor = preferences.edit()
        editor.putInt("gpsId", id)
        editor.apply() // 데이터를 비동기적으로 저장
        viewModel.getGps(token = token, gpsId = id)
    }

    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = if (gps != null) gps!!.data.gpsName else "",
                        fontFamily = pretendard_bold,
                        fontSize = 23.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_back),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        if (gps != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MinariWhite)
                    .padding(innerPadding),
                verticalArrangement = Arrangement.spacedBy(20.dp)
            ) {
//                Log.d("TAG", "Grapes: ${gps!!.data.gpList.first()}\n${gps!!.data.gpList.last()}")
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                            .background(Color.White)
                            .padding(horizontal = 30.dp, vertical = 15.dp)
                    ) {
                        AsyncImage(
                            model = gps!!.data.gpsImg,
                            contentDescription = null,
                            modifier = Modifier.size(100.dp)
                        )
                        Row(
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = gps!!.data.gpsName,
                                fontFamily = pretendard_bold,
                                fontSize = 23.sp,
                                modifier = Modifier.padding(vertical = 5.dp)
                            )
                        }
                        Text(
                            text = "${gps!!.data.gpsTime}분 - 포도송이 - ${gps!!.data.gpCnt}/${gps!!.data.gpCntMax}포도알",
                            fontFamily = pretendard_regular
                        )
                        Text(
                            text = gps!!.data.gpsContent,
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
                items(gps!!.data.gpList) { item ->
                    Gpse(
                        navController = navController,
                        token = token,
                        title = gps!!.data.gpsName,
                        gpId = item.gpId,
                        gpNm = item.gpNm,
                        gpImg = item.gpImg,
                        exp = item.gpExp,
                        gpTm = item.gpTm,
                        gpLike = item.gpLike,
                        gpseCnt = item.gpseCnt,
                        gpseCntMax = item.gpseCntMax,
                        viewModel = viewModel,
                        likesClick = { viewModel.likes(token, "GRAPE", item.gpId) },
                        state = if (item == gps!!.data.gpList.first()) true else false
                    )
                }
            }
        } else {
            Box(modifier = Modifier.fillMaxSize()) {
                CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            }
        }
    }
}

@Composable
fun Gpse(
    navController: NavController,
    title: String,
    gpImg: String,
    gpId: Int,
    gpNm: String,
    exp: Int,
    gpTm: Int,
    gpLike: Boolean,
    gpseCnt: Int,
    gpseCntMax: Int,
    viewModel: GrapeViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
    token: String,
    likesClick: () -> Unit,
    state: Boolean
) {
    var isExpanded by remember { mutableStateOf(state) }
    val grape = viewModel.allGpMap.collectAsState().value[gpId] // 해당 gpId에 대한 데이터만 참조

    LaunchedEffect(key1 = isExpanded) {
        if (isExpanded && grape == null) {
            viewModel.getAllGrape(token = token, gpId = gpId)
        }
    }

    LaunchedEffect(Unit) {
        if (gpseCnt == gpseCntMax) {
            mainViewModel.finishLearn(token, "GRAPE", gpId)
        }
    }

    Box(
        modifier = Modifier
            .padding(horizontal = 10.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .clickable {
                    isExpanded = !isExpanded
                    Log.d("TAG", "Gpse: $gpId")
                } // 클릭 시 확장 상태 토글
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
                        text = "${exp}xp",
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
                Column(
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .weight(0.9f),
                    verticalArrangement = Arrangement.Center,
                ) {
                    Text(
                        text = gpNm,
                        fontFamily = pretendard_bold,
                        fontSize = 17.sp,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis,
                    )
                    Text(
                        text = "${gpTm}분 - 포도알 - $gpseCnt/${gpseCntMax}포도씨",
                        fontFamily = pretendard_regular,
                        fontSize = 12.sp,
                        modifier = Modifier.padding(vertical = 5.dp)
                    )
                }
                AsyncImage(
                    model = gpImg,
                    contentDescription = null
                )
                Icon(
                    painter = painterResource(id = R.drawable.book_mark),
                    contentDescription = null,
                    tint = if (gpLike) MinariBlue else Color.Gray,
                    modifier = Modifier
                        .padding(top = 10.dp)
                        .size(20.dp)
                        .clickable { likesClick() }
                )
            }

            // 확장 시 보여줄 추가 정보
            if (isExpanded) {
                Spacer(modifier = Modifier.height(16.dp))

                if (grape != null) {
                    grape.data.forEach {
                        Row(
                            modifier = Modifier
                                .clickable {
                                    navController.navigate(Screens.Grape.rout + "/${it.gpseId}/$title")
                                }
                        ) {
                            Text(
                                text = it.gpseNm,
                                modifier = Modifier.weight(0.1f),
                                overflow = TextOverflow.Ellipsis
                            )
                            Text(text = "${it.gpseTm}분")
                        }
                    }
                } else {
                    CircularProgressIndicator(modifier = Modifier.size(24.dp))
                }

                Spacer(modifier = Modifier.height(8.dp))
            }

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 5.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_back),
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