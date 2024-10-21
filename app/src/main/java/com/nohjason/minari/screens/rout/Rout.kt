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
import com.nohjason.minari.preferences.saveToPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_bold
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
    var selectedAge by remember { mutableStateOf("") }
    var selectedWork by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        while (true) {
            viewModel.getGpsCategory(token, selectedAge, selectedWork)
            delay(1000) // 1초마다 새로 고침
        }
    }

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
            val age = mapOf(
                "10대" to "TEENS",
                "20대" to "TWENTIES",
                "30대" to "THIRTIES",
                "40대" to "FORTIES",
                "50대" to "FIFTIES"
            )
            val work = mapOf(
                "사회 구성원" to "MEMBEROFSOCIETY",
                "공무원" to "OFFICIAL",
                "직원" to "EMPLOYEE",
                "실업가" to "BUSINESSMAN"
            )
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                CategoryButtons(
                    categories = age,
                    selectedCategory = selectedAge,
                    onCategorySelected = { selectedAge = it }
                )

                Spacer(modifier = Modifier.height(10.dp)) // 구분을 위한 여백

                CategoryButtons(categories = work,
                    selectedCategory = selectedWork,
                    onCategorySelected = { selectedWork = it }
                )
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

@Composable
fun CategoryButtons(
    categories: Map<String, String>, // 카테고리 리스트
    selectedCategory: String, // 현재 선택된 카테고리
    onCategorySelected: (String) -> Unit // 카테고리 선택 시 동작
) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        items(categories.toList()) { category ->
            val isSelected = selectedCategory == category.second
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .border(
                        1.dp,
                        if (isSelected) Color(0x00000000) else Color(0xFFECEFFB),
                        shape = CircleShape
                    )
                    .background(if (isSelected) Color.Blue else Color.White)
                    .clickable {
                        // 같은 카테고리를 클릭하면 해제 (빈 문자열로 설정)
                        if (isSelected) {
                            onCategorySelected("") // 선택 해제
                        } else {
                            onCategorySelected(category.second) // 새로운 카테고리 선택
                        }
                    }
                    .padding(vertical = 3.dp, horizontal = 20.dp)
            ) {
                Text(
                    text = category.first,
                    color = if (isSelected) Color.White else Color.Black,
                    fontSize = 13.sp,
                    fontFamily = pretendard_bold
                )
            }
        }
    }
}