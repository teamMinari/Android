package com.nohjason.minari.screens.rout

import android.util.Log
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_extra_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Rout(
    navController: NavController,
    viewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val route by viewModel.route.collectAsState()
    val gps by viewModel.gpsDetail.collectAsState()

    LaunchedEffect(key1 = Unit) {
        viewModel.getAllGps(token = token)
        if (gps != null && gps!!.data.gpCnt == gps!!.data.gpCntMax) {
            Log.d("TAG", "Rout: 학습완료")
        }
    }
    BackHandler(onBack = {
        navController.popBackStack(BottomScreen.Home.rout, inclusive = false)
    })
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
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
        if (route != null) {
            Log.d("TAG", "Rout: $route")
            items(route!!.data) { item ->
                Gps(
                    onClick = { navController.navigate(Screens.Grapes.rout + "/${item.gpsId}") },
                    iconClick = { viewModel.likes(token, "GRAPES", item.gpsId) },
                    like = item.gpsLike,
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
    Log.d("TAG", "Gps: $gpsAgeGroup, $gpsWork")
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        onClick = { onClick() }
    ) {
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
                    painter = painterResource(id = R.drawable.clock),
                    contentDescription = null
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(text = "${time}분", fontFamily = pretendard_medium, fontSize = 12.sp)
            }
            if (gpsAgeGroup != "" && gpsWork != "") {
                Row(
                    modifier = Modifier.padding(vertical = 5.dp),
                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                ) {
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(MinariBlue)
                        .padding(horizontal = 10.dp, vertical = 2.dp)) {
                        Text(
                            text = gpsAgeGroup,
                            fontFamily = pretendard_medium,
                            color = Color.White,
                            modifier = Modifier.align(Alignment.Center)
                        )
                    }
                    Box(modifier = Modifier
                        .clip(CircleShape)
                        .background(MinariBlue)
                        .padding(horizontal = 10.dp, vertical = 2.dp)) {
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

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Rout(navController = rememberNavController())
}