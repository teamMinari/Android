package com.nohjason.minari.screens.term

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
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
import com.mohamedrejeb.richeditor.annotation.ExperimentalRichTextApi
import com.mohamedrejeb.richeditor.model.rememberRichTextState
import com.mohamedrejeb.richeditor.ui.material3.RichText
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_regular

// home스크린에서 받은 글자를 표시하는 테스트 화면
@OptIn(ExperimentalMaterial3Api::class, ExperimentalRichTextApi::class)
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
        grapeViewModel.getEasyTerm(token, title)
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
                            if (text.isNotEmpty()) {
                                grapeViewModel.getTerm(token, text)
                                navController.navigate(Screens.Term.rout + "/${text}")
                            }
                        }
                    )
                },
            )
        }
    ) { innerPadding ->
        var showDialog by remember { mutableStateOf(false) }

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
                                        grapeViewModel.likes(
                                            token,
                                            "TERM",
                                            item.termId,
                                            item.termNm
                                        )
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
                                    .clickable {
                                        showDialog = true
                                    }
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
                        if (showDialog) {
                            val termResponse =
                                grapeViewModel.getEasyTerm.collectAsState().value // StateFlow로부터 데이터 수집
                            val richTextState = rememberRichTextState()
                            val markdown = termResponse ?: "AI가 용어의 해설을\n가지고 오고 있어요!"

                            AlertDialog(
                                onDismissRequest = { showDialog = false },  // 팝업 외부를 눌렀을 때 닫힘
                                text = {
                                    Column(
                                        horizontalAlignment = Alignment.CenterHorizontally,

                                    ) {
                                        if (termResponse == null) {
                                            Image(painter = painterResource(id = R.drawable.ai), contentDescription = null)
                                        }
                                        RichText(
                                            state = richTextState.setMarkdown(markdown),
                                            fontSize = 15.sp
                                        )
                                    }
                                },
                                confirmButton = {
//                                    Button(onClick = { showDialog = false }) {
//                                        Text("확인")
//                                    }
                                },
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

@OptIn(ExperimentalRichTextApi::class)
@Preview
@Composable
private fun Test() {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        val richTextState = rememberRichTextState()
        val markdown = "#Test\n\nqwerqwer"
        Image(painter = painterResource(id = R.drawable.ai), contentDescription = null)
        RichText(
            state = richTextState.setMarkdown(markdown),
            fontSize = 15.sp
        )
    }
}