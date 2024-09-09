package com.nohjason.minari.screens.rout

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.quiz.QuizeViewModel
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun Grape(
    grapeViewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    quizeViewModel: QuizeViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController,
    gpseId: Int,
    title: String,
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val gpse by grapeViewModel.gpse.collectAsState()
    val likesGpse by grapeViewModel.likes.collectAsState()
    LaunchedEffect(key1 = Unit) {
        grapeViewModel.getGpse(
            token = token,
            gpseId = gpseId
        )
    }

    Scaffold(
        topBar = {
            androidx.compose.material.TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Text(
                        text = title,
                        fontFamily = pretendard_bold,
                        fontSize = 23.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.befor_arrow),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        if (gpse != null) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MinariWhite)
                    .padding(innerPadding)
                    .padding(horizontal = 20.dp)
            ) {
                item {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 10.dp)
                    ) {
                        Spacer(modifier = Modifier.height(20.dp))
                        Row {
                            Spacer(modifier = Modifier.weight(0.1f))
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFF5DC067))
                            ) {
                                Text(
                                    text = "${gpse!!.data.gpseExp}xp",
                                    fontSize = 10.sp,
                                    fontFamily = pretendard_bold,
                                    color = Color.White,
                                    modifier = Modifier
                                        .align(Alignment.Center)
                                        .padding(horizontal = 10.dp, vertical = 2.dp)
                                )
                            }
                        }
                        Row(
                            Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = gpse!!.data.gpseName,
                                fontFamily = pretendard_bold,
                                fontSize = 23.sp
                            )
                            Spacer(modifier = Modifier.width(10.dp))
                            Text(text = "${gpse!!.data.gpseTime}분")
                            Spacer(modifier = Modifier.weight(0.1f))
                            Icon(
                                painter = painterResource(id = R.drawable.my_words),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
//                                        grapeViewModel.alikes(token, "GRAPESEED", gpseId)
                                    }
                            )
                        }
                    }
                }
                item {
                    Column(
                        modifier = Modifier
                            .background(Color.White)
                            .fillMaxWidth()
                            .padding(horizontal = 25.dp, vertical = 10.dp)
                    ) {
//                        Text(
//                            text = "청소년 경제를 알아야하는 이유를 알아봅시다.",
//                            fontSize = 15.sp,
//                            fontFamily = pretendard_semibold
//                        )
                        Text(
                            text = gpse!!.data.gpseContent,
                            fontSize = 13.sp,
                            fontFamily = pretendard_medium
                        )
                    }
                }
                item {
                    val quize by quizeViewModel.quize.collectAsState()
                    LaunchedEffect(key1 = Unit) {
                        quizeViewModel.getQuize(
                            token = token,
                            quizeId = gpse!!.data.gpseQtId
                        )
                        Log.d("TAG", "quize: ${quize}")
                    }
                    if (quize != null) {
                        Column(
                            modifier = Modifier
                                .background(Color.White)
                                .fillMaxWidth()
                                .padding(horizontal = 25.dp, vertical = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(RoundedCornerShape(20.dp))
                                    .background(Color(0xFFF3F4F6))
                                    .padding(vertical = 10.dp, horizontal = 23.dp)
                            ) {
                                Column {
                                    Text(
                                        text = "Quiz.",
                                        fontFamily = pretendard_bold,
                                        fontSize = 20.sp
                                    )
                                    Text(
                                        text = quize!!.data.qtContents,
                                        fontFamily = pretendard_semibold,
                                        fontSize = 13.sp
                                    )
                                    Spacer(modifier = Modifier.height(10.dp))
                                    Row {
                                        Box(
                                            modifier = Modifier
                                                .weight(0.5f)
                                                .height(40.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(Color(0xFFF6D0D1))
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Close,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .align(Alignment.Center),
                                            )
                                        }

                                        Spacer(modifier = Modifier.width(10.dp))

                                        Box(
                                            modifier = Modifier
                                                .weight(0.5f)
                                                .height(40.dp)
                                                .clip(RoundedCornerShape(10.dp))
                                                .background(Color(0xFFDFE8F6))
                                        ) {
                                            Icon(
                                                imageVector = Icons.Default.Done,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .align(Alignment.Center)
                                            )
                                        }
                                    }
                                }
                            }
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
                item {
                    Box(modifier = Modifier
                        .fillMaxWidth()
                        .fillParentMaxHeight()
                        .background(Color.White)) {
                        Text(text = "퀴즈를 준비중 입니다", modifier = Modifier.align(Alignment.Center))
                    }
                }
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}

//@Preview
//@Composable
//private fun Test() {
//    Grape(navController = rememberNavController(), gpseId = 0, title = "test")
//}