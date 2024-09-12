package com.nohjason.minari.screens.term

import android.util.Log
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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
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
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.news.NewsCard
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular

// home스크린에서 받은 글자를 표시하는 테스트 화면
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Test(
    title: String,
    navController: NavController,
//    viewModel: TermViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    grapeViewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
//    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
) {
    val getTerm by grapeViewModel.getTerm.collectAsState()
    var text by remember { mutableStateOf("") }
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")

    LaunchedEffect(key1 = Unit) {
        grapeViewModel.getTerm(token, title)
        grapeViewModel.getAllLikesTerm(token)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    MinariTextField(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color(0xFFF6F6F6))
                            .padding(6.dp),
                        value = text,
                        onValueChange = { text = it },
                        onClick = {
//                            termViewModel.getTerm(token, text)
//                            navController.navigate("test/${text}")
                        }
                    )
                },
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .background(MinariWhite)
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 20.dp)
        ) {
            if (getTerm != null) {
                item {
                    val item = getTerm!!.data
                    Column(
                        modifier = Modifier
                            .fillParentMaxSize()
                            .background(Color.White)
                            .padding(horizontal = 20.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(vertical = 20.dp)
                        ) {
                            LazyRow {
                                val difficulty = item.termDifficulty[3].toString().toInt()
                                items(difficulty) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.star),
                                        contentDescription = null,
                                        modifier = Modifier.size(15.dp),
                                        tint = Color.Unspecified
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.weight(0.1f))
                            Icon(
                                painter = painterResource(R.drawable.book_mark),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(20.dp)
                                    .clickable {
                                        grapeViewModel.likes(token, "TERM", item.termId, item.termNm)
                                    },
                                tint = if (getTerm!!.data.termLike) Color.Unspecified else Color.Gray
                            )
                        }
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(
                                text = item.termNm,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.weight(0.1f))
                            Box(
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .background(Color(0xFFFF9900))
                                    .padding(horizontal = 10.dp)
                            ) {
                                Text(
                                    text = "쉬운 용어 풀이",
                                    fontSize = 10.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = Color.White
                                )
                            }
                        }
                        Divider(modifier = Modifier.padding(vertical = 20.dp))
                        Text(
                            text = item.termExplain,
                            fontSize = 13.sp,
                            fontFamily = pretendard_regular
                        )
                        Row {
                            Spacer(modifier = Modifier.weight(0.1f))
                            Text(
                                text = "출처 : 경제금융용어 700선",
                                fontSize = 10.sp,
                                fontFamily = pretendard_medium
                            )
                        }
                    }
                }
            } else {
                item {
                    CircularProgressIndicator()
                }
            }
        }
    }

//    if (getTerm != null) {
//        var text by remember { mutableStateOf("") }
//        Box(modifier = Modifier
//            .fillMaxSize()
//            .background(MinariWhite)
//        ) {
//            Column {
//                TopAppBar(
//                    title = {
//                        MinariTextField(
//                            modifier = Modifier
//                                .height(25.dp)
//                                .padding(end = 30.dp)
//                                .fillMaxWidth()
//                                .background(Color.Transparent, shape = CircleShape),
//                            value = text,
//                            onValueChange = {text = it},
//                            onClick = {navController.navigate("test/${title}")},
//                        )
//                    },
//                    backgroundColor =  Color.White,
//                    navigationIcon = {
//                        IconButton(onClick = { navController.popBackStack() }) {
//                            androidx.compose.material3.Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
//                        }
//                    }
//                )
//
//                Spacer(modifier = Modifier.height(10.dp))
//
//                Box(
//                    modifier = Modifier
//                        .padding(horizontal = 20.dp)
//                        .background(Color.White)
//                        .weight(0.6f)
//                ) {
//                    LazyColumn(
//                        modifier = Modifier
//                            .padding(10.dp)
//                    ) {
//                        item {
//                            LazyRow {
//                                val starCount = getTerm!!.data[2].toString().toInt()
////                                val starCount = term!!.termDifficulty[3].toString().toInt()
//                                items(starCount) { item ->
//                                    Icon(
//                                        painter = painterResource(R.drawable.star),
//                                        contentDescription = null,
//                                        tint = Color.Unspecified,
//                                        modifier = Modifier.size(20.dp)
//                                    )
//                                }
//                            }
//                            Spacer(modifier = Modifier.height(10.dp))
//                            MinariText(text = getTerm!!.data[0].termNm)
//                            MinariLine(horizontalPadding = 0.dp)
//                            MinariText(
//                                text = getTerm!!.data[1].termExplain,
//                                size = 13,
//                                textAlign = TextAlign.Left
//                            )
//                        }
//                    }
//                }
//
//                Spacer(modifier = Modifier.height(20.dp))
//
//                Box(
//                    modifier = Modifier
//                        .padding(horizontal = 10.dp)
//                        .weight(0.4f)
//                ) {
//                    Column {
//                        MinariText(text = "관련 용어", size = 15)
////                        LazyRow(modifier = Modifier.padding(vertical = 5.dp)) {
////                            itemsIndexed(word.dummyTermSimilarButton) { index, item ->
////                                Box(
////                                    modifier = Modifier
////                                        .clip(CircleShape)
////                                        .background(Color.White)
////                                        .border(
////                                            1.dp,
////                                            Color.Black,
////                                            shape = CircleShape
////                                        )
////                                        .padding(vertical = 3.dp, horizontal = 10.dp)
////                                        .clickable {
////                                            navController.navigate("test/${item.title}")
//////                                            Log.d("TAG", "Test: ${item.title}")
////                                        }
////                                ) {
////                                    MinariText(text = item.title, size = 10)
////                                }
////                                if (index != word.dummyTermSimilarButton.size - 1) {
////                                    Spacer(modifier = Modifier.width(5.dp))
////                                }
////                            }
////                        }
//
//                        // news
//                        LazyColumn(
//                            modifier = Modifier
//                                .padding(10.dp)
//                                .background(Color.White)
//                        ) {
//                            items(10) { index ->
//                                NewsCard()
//                                if (index != 9) {
//                                    Spacer(modifier = Modifier.height(10.dp))
//                                }
//                            }
//                        }
//                    }
//                }
//
//            }
//        }
//    } else {
//        TopAppBar(
//            title = {  },
//            backgroundColor =  Color.White,
//            navigationIcon = {
//                IconButton(
//                    onClick = {
//
//                    },
//                ) {
//                    androidx.compose.material3.Icon(
//                        Icons.Filled.ArrowBack,
//                        null,
//                        tint = Color.Black
//                    )
//                }
//            }
//        )
//        Column(
//            modifier = Modifier.fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally,
//            verticalArrangement = Arrangement.Center
//        ) {
////            InProduction(title = "아직 추가되지 않은 용어 입니다", value = "서비스 이용에 불편을 드려서 죄송합니다")
//            MinariText(text = "아직 추가되지 않은 용어 입니다")
//        }
//    }
}

@Preview
@Composable
fun TermTest() {
    Test("몰라", rememberNavController())
}