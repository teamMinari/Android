package com.nohjason.minari.screens.term

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular

// home스크린에서 받은 글자를 표시하는 테스트 화면
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TermScreen(
    title: String,
    navController: NavController,
    grapeViewModel: GrapeViewModel = androidx.lifecycle.viewmodel.compose.viewModel()
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
                            .background(Color.White)
                            .padding(6.dp),
                        value = text,
                        onValueChange = { text = it },
                        onClick = {
                            grapeViewModel.getTerm(token, text)
                            navController.navigate(Screens.Term.rout + "/${text}")
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
//                                text = "가계부실위험지수(HDRI)",
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
//                            text = "가구의 소득 흐름은 물론 금융 및 실물 자산까지 종합적으로 고려하여 가계부채의 부실위험을 평가하는 지표로, 가계의 채무상환능력을 소득 측면에서 평가하는 원리금상 환비율(DSR; Debt Service Ratio)과 자산 측면에서 평가하는 부채/자산비율(DTA; Debt To Asset Ratio)을 결합하여 산출한 지수이다. 가계부실위험지수는 가구의 DSR과 DTA가 각각 40%, 100%일 때 100의 값을 갖도록 설정되어 있으며, 동 지수가 100을 초과하는 가구를 ‘위험가구’로 분류한다. 위험가구는 소득 및 자산 측면에서 모두 취약한 ‘고위험가구’, 자산 측면에서 취약한 ‘고DTA가구’, 소득 측면에서 취약한 ‘고DSR가구’로 구분할 수 있다. 다만 위험 및 고위험 가구는 가구의 채무상환능력 취약성 정도를 평가하기 위한 것이며 이들 가구가 당장 채무상환 불이행, 즉 임계상황에 직면한 것을 의미하지 않는다.",
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
}

@Preview
@Composable
fun TermTest() {
    TermScreen("몰라", rememberNavController())
}