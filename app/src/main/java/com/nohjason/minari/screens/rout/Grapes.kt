package com.nohjason.minari.screens.rout

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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Grapes(
    navController: NavController,
    viewModel: GrapeViewModel = viewModel(),
    id: Int,
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val gps by viewModel.gpsDetail.collectAsState()
    LaunchedEffect(key1 = Unit ) {
        viewModel.getGps(
            token = token,
            gpsId = id
        )
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
                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(0.dp, 0.dp, 20.dp, 20.dp))
                            .background(Color.White)
                            .padding(horizontal = 30.dp, vertical = 15.dp)
                    ) {
                        AsyncImage(
                            model = "https://intelligent-passenger-537.notion.site/image/https%3A%2F%2Fprod-files-secure.s3.us-west-2.amazonaws.com%2F01de7400-998d-4948-aa79-591ed35a24f0%2Fde288815-7c10-40b7-86c6-adba37bca831%2F9ea1cb2e-d402-4462-b2a7-dadc452ed360.png?table=block&id=9c2e8e76-1ff5-4b42-aafc-c52b93305967&spaceId=01de7400-998d-4948-aa79-591ed35a24f0&width=2000&userId=&cache=v2",
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
                        val tag = gps!!.data.gpTpList
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
                        navController,
                        gpImg = item.gpImg,
                        title = gps!!.data.gpsName,
                        gpId = item.gpId,
                        gpNm = item.gpNm,
                        exp = item.gpExp,
                        gpTm = item.gpTm,
                        gpLike = item.gpLike,
                        gpseCnt = item.gpseCnt,
                        gpseCntMax = item.gpseCntMax,
                        token = token,
                        likesClick = { viewModel.likes(token, "GRAPE", item.gpId) }
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
    viewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    token: String,
    likesClick: () -> Unit
) {
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
//                Image(
//                    painter = painterResource(id = R.drawable.ellipes),
//                    contentDescription = null,
//                    modifier = Modifier.size(45.dp)
//                )
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
//                        text = "20분-포도알-0/3포도씨",
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

            val grape by viewModel.allGp.collectAsState()
            LaunchedEffect(key1 = Unit) {
                viewModel.getAllGrape(
                    token = token,
                    gpId = gpId
                )
            }
            // Box가 확장되었을 때만 보이는 내용
            if (isExpanded) {
                Spacer(modifier = Modifier.height(10.dp))
                if (grape != null) {
                    grape!!.data.forEach {
                        Row(
                            modifier = Modifier.clickable { navController.navigate(Test.Grape.rout + "/${it.gpseId}/$title") }
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
                    CircularProgressIndicator(modifier = Modifier.size(50.dp))
                }
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

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Grapes(rememberNavController(), viewModel = viewModel(), id = 0)
}