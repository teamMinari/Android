package com.nohjason.minari.screens.rout

import android.annotation.SuppressLint
import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_extra_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold
import kotlinx.coroutines.delay

@SuppressLint("CommitPrefEdits")
@Composable
fun Rout(
    navController: NavController,
    viewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val preferences = getPreferences()
    val editor = preferences.edit()
    val token = getFromPreferences(preferences, "token")
    val route by viewModel.getGpsCategory.collectAsState()
    val gps by viewModel.gpsDetail.collectAsState()
    var selectedAge by remember { mutableStateOf("") }
    var selectedWork by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getGpsCategory(token, selectedAge, selectedWork)
            delay(1000) // 1초마다 새로 고침
        }
    }

//    LaunchedEffect(Unit, selectedAge, selectedWork) {
//        viewModel.getGpsCategory(token = token, selectedAge, selectedWork)
//        if (gps != null && gps!!.data.gpCnt == gps!!.data.gpCntMax) {
//            Log.d("TAG", "Rout: 학습완료")
//        }
//    }
    BackHandler(onBack = {
        navController.popBackStack(BottomScreen.Home.rout, inclusive = false)
    })
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .padding(horizontal = 20.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        item {
            Column {
                Spacer(modifier = Modifier.height(50.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        painter = painterResource(id = R.drawable.rout),
                        contentDescription = null,
                        tint = MinariBlue,
                        modifier = Modifier.size(30.dp)
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Text(
                        text = "튜토리얼",
                        fontSize = 30.sp,
                        fontFamily = pretendard_extra_bold,
                        color = MinariBlue
                    )
                }
                Text(
                    text = "자신에게 맞는 포도송이를 획득할 수 있어요.",
                    fontFamily = pretendard_medium,
                    fontSize = 13.sp
                )
            }
        }
        item {
            val age = listOf("TEENS", "TWENTIES", "THIRTIES", "FORTIES", "FIFTIES")
            val work = listOf("MEMBEROFSOCIETY", "OFFICIAL", "EMPLOYEE", "BUSINESSMAN")
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                CategoryButtons(categories = age,
                    selectedCategory = selectedAge,
                    onCategorySelected = { selectedAge = it })

                Spacer(modifier = Modifier.height(16.dp)) // 구분을 위한 여백

//     Work 카테고리 버튼
                CategoryButtons(categories = work,
                    selectedCategory = selectedWork,
                    onCategorySelected = { selectedWork = it })
            }
        }
        if (route != null) {
            items(route!!.data) { item ->
                var qwer = item.gpsLike
                Gps(
                    onClick = {
                        editor.putInt("gpsId", item.gpsId)
                        editor.apply()
                        navController.navigate(Screens.Grapes.rout + "/${item.gpsId}")
                    },
                    iconClick = {
                        viewModel.likes(token, "GRAPES", item.gpsId, age = selectedAge, work = selectedWork)
                        qwer = !qwer
                    },
                    like = qwer,
                    name = item.gpsName,
                    time = item.gpsTime,
                    content = item.gpsContent,
                    gpsAgeGroup = item.gpsAgeGroup ?: "",
                    gpsWork = item.gpsWork ?: ""
                )
            }
            item {
                Spacer(modifier = Modifier.height(20.dp))
            }
        } else {
            item {
                Box(modifier = Modifier.fillParentMaxWidth()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}

// 포도송이
@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Gps(
    onClick: () -> Unit,
    iconClick: () -> Unit,
    like: Boolean,
    name: String,
    time: Int,
    content: String,
    gpsAgeGroup: String,
    gpsWork: String,
) {
//    Log.d("TAG", "Gps: $gpsAgeGroup, $gpsWork")
    Card(shape = RoundedCornerShape(16.dp), elevation = 4.dp, onClick = { onClick() }) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(
                    text = name,
                    fontFamily = pretendard_semibold,
                    fontSize = 23.sp,
                    modifier = Modifier.padding(vertical = 10.dp)
                )
                Spacer(modifier = Modifier.width(10.dp))
                Icon(
                    painter = painterResource(id = R.drawable.book_mark),
                    contentDescription = null,
                    modifier = Modifier
                        .size(20.dp)
                        .clickable { iconClick() },
                    tint = if (like) MinariBlue else Color.Gray
                )
            }
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.clock), contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "${time}분", fontFamily = pretendard_medium, fontSize = 12.sp)
            }
            if (gpsAgeGroup != "" && gpsWork != "") {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MinariBlue)
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = gpsAgeGroup,
                            fontFamily = pretendard_medium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(MinariBlue)
                            .padding(horizontal = 10.dp, vertical = 2.dp)
                    ) {
                        Text(
                            text = gpsWork,
                            fontFamily = pretendard_medium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                }
            }
            Text(
                text = content,
                modifier = Modifier.padding(vertical = 5.dp),
                maxLines = 2,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF888888)
            )
        }
    }
}

// 공통 버튼 렌더링 함수
@Composable
fun CategoryButtons(
    categories: List<String>, // 카테고리 리스트
    selectedCategory: String, // 현재 선택된 카테고리
    onCategorySelected: (String) -> Unit // 카테고리 선택 시 동작
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center
    ) {
        items(categories) { category ->
            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(RoundedCornerShape(20.dp)) // 둥근 모서리
                    .background(
                        if (selectedCategory == category) MinariBlue // 선택된 버튼의 배경색
                        else Color.Transparent // 선택되지 않은 버튼의 배경색
                    )
                    .clickable { onCategorySelected(if (selectedCategory == category) "" else category) }
                    .border(
                        1.dp,
                        if (selectedCategory == category) Color.Transparent else Color.LightGray,
                        shape = CircleShape
                    ) // 테두리 설정
            ) {
                Text(
                    text = category,
                    color = if (selectedCategory == category) Color.White else Color.Gray, // 선택된 버튼 텍스트 색상
                    fontSize = 14.sp,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 5.dp)
                )
            }
        }
    }
}