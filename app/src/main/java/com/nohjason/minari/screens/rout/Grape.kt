package com.nohjason.minari.screens.rout

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.IconButton
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Done
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.network.response.Quize
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.quiz.QuizeViewModel
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.myapplication.network.MainViewModel
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

@Composable
fun Grape(
    grapeViewModel: GrapeViewModel = viewModel(),
    quizeViewModel: QuizeViewModel = viewModel(),
    mainViewModel: MainViewModel = viewModel(),
    navController: NavController,
    gpseId: Int,
    title: String,
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val gpse by grapeViewModel.gpse.collectAsState()
    LaunchedEffect(key1 = Unit) {
        grapeViewModel.getGpse(token, gpseId)
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
        if (gpse != null) {
            val listState = rememberLazyListState()
            val coroutineScope = rememberCoroutineScope()
            val quize by quizeViewModel.quize.collectAsState()
            LaunchedEffect(key1 = Unit) {
                quizeViewModel.getQuize(token, gpse!!.data.gpseQtId)
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .background(MinariWhite)
                    .padding(innerPadding)
                    .padding(horizontal = 10.dp),
                state = listState
            ) {
                item {
                    Value(gpse = gpse!!, token = token, gpseId = gpseId)
                }
                item {
                    ChangeText(token, navController, gpse!!.data.gpseContent)
                }
                item {
                    GrapeSeedQuize(quize)
                }
            }

            LaunchedEffect(listState) {
                snapshotFlow {
                    val layoutInfo = listState.layoutInfo
                    val visibleItemsInfo = layoutInfo.visibleItemsInfo
                    if (visibleItemsInfo.isNotEmpty()) {
                        val lastVisibleItem = visibleItemsInfo.last()
                        val lastIndex = lastVisibleItem.index
                        lastIndex == layoutInfo.totalItemsCount - 1 && lastVisibleItem.offset + lastVisibleItem.size <= layoutInfo.viewportEndOffset
                    } else {
                        false
                    }
                }
                    .distinctUntilChanged()
                    .collect { isLastItemVisible ->
                        if (isLastItemVisible) {
                            coroutineScope.launch {
                                if (!(gpse!!.data.gpseTF)) {
                                    mainViewModel.finishLearn(token, "GRAPESEED", gpseId)
                                }
                                Log.d("TAG", "Grape: 끝\n${gpseId}")
                            }
                        }
                    }
            }
        } else {
            CircularProgressIndicator(modifier = Modifier.size(50.dp))
        }
    }
}

@Composable
fun ClickableReferenceText(
    text: String,
    modifier: Modifier = Modifier,
    linkColor: Color = MaterialTheme.colorScheme.primary,
    onClick: (String) -> Unit = {}
) {
    val TAG = "TAG"

    // 정규 표현식: [[링크 텍스트]](URL) 패턴 정의
    val pattern = "\\[\\[(.*?)]]\\(https?://[^)]+\\)".toRegex()

    // 링크 텍스트를 추출하고 나머지 텍스트를 변환
    val extractedTexts = mutableListOf<String>()
    val resultText = pattern.replace(text) { matchResult ->
        val linkText = matchResult.groups[1]?.value.orEmpty()
        extractedTexts.add(linkText)
        linkText // [[링크 텍스트]]를 링크 텍스트로 변환
    }

    // 텍스트 나누기 및 AnnotatedString 생성
    val annotatedText = buildAnnotatedString {
        var currentIndex = 0

        extractedTexts.forEach { keyword ->
            val keywordIndex = resultText.indexOf(keyword, currentIndex)
            if (keywordIndex >= 0) {
                // 키워드 이전의 텍스트 추가
                append(resultText.substring(currentIndex, keywordIndex))
                // 키워드에 스타일 추가 및 클릭 이벤트 처리
                pushStringAnnotation(tag = "ClickText", annotation = keyword)
                withStyle(style = SpanStyle(color = linkColor)) {
                    append(keyword)
                }
                pop()
                currentIndex = keywordIndex + keyword.length
            }
        }

        // 남은 텍스트 추가
        if (currentIndex < resultText.length) {
            append(resultText.substring(currentIndex))
        }
    }

    // ClickableText 사용하여 텍스트와 클릭 이벤트 처리
    ClickableText(
        text = annotatedText,
        modifier = modifier,
        onClick = { offset ->
            annotatedText.getStringAnnotations(tag = "ClickText", start = offset, end = offset)
                .firstOrNull()?.let { annotation ->
                    onClick(annotation.item) // 클릭된 텍스트에 대해 onClick 처리
                }
        }
    )
}

@Composable
fun Value(
    token: String,
    gpseId: Int,
    gpse: GrapeSeed,
    grapeViewModel: GrapeViewModel = viewModel(),
) {
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
                    text = "20xp",
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
                text = gpse.data.gpseName,
                fontFamily = pretendard_bold,
                fontSize = 23.sp
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(text = "${gpse.data.gpseTime}분")
            Spacer(modifier = Modifier.weight(0.1f))
            Icon(
                painter = painterResource(id = R.drawable.book_mark),
                contentDescription = null,
                modifier = Modifier
                    .size(20.dp)
                    .clickable {
                        grapeViewModel.likes(token, "GRAPESEED", gpseId)
                    },
                tint = if (gpse.data.gpseLike) MinariBlue else Color.Gray,
            )
        }
    }
}

@Composable
fun ChangeText(
    token: String,
    navController: NavController,
    text: String,
    grapeViewModel: GrapeViewModel = viewModel(),
) {
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(25.dp)
    ) {
//                        val richTextState = rememberRichTextState()
//                        val markdown = "경제란 무엇일까요?\n" +
//                                "\n" +
//                                "경제란 인간의 공동생활을 위한 물적 기초가 되는 재화와 용역을 생산 · 분배 · 소비하는 활동과 그것을 통하여 형성되는 사회관계의 총체를 가리키는 용어입니다. [[1]](https://encykorea.aks.ac.kr/Article/E0002781)\n" +
//                                "\n" +
//                                "이렇게 많은 제도들을 활용하여 돈을 저축하기만 하는것이 아니라\n" +
//                                "\n" +
//                                "현명하게 불릴 수 있는 능력이 있어야 합니다.\n" +
//                                "\n" +
//                                "이번 포도송이 에서는 경제에 대한 기본지식도 배우지만, 뱡금 같은 예시처럼 스스로 자산을 운용할 수 있는 기초지식을 중점으로 다룹니다."
//
//                        RichText(
//                            state = richTextState.setMarkdown(markdown),
//                        )

        ClickableReferenceText(
            text = text,
            onClick = { clickedText ->
                grapeViewModel.getTerm(token, clickedText)
                navController.navigate(Screens.Term.rout + "/${clickedText}")
            }
        )
    }
}

@Composable
fun GrapeSeedQuize(
    quize: Quize?
) {
    if (quize != null) {
        var state by remember { mutableStateOf(false) }
        var selectedAnswer by remember { mutableStateOf<String?>(null) }

        Column(
            modifier = Modifier
                .background(Color.White)
        ) {
            Box(
                modifier = Modifier
                    .padding(horizontal = 25.dp, vertical = 10.dp)
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
                        text = quize.data.qtContents,
                        fontSize = 20.sp
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                    if (state) {
                        Text(
                            text = "Tip ⭐️",
                            fontFamily = pretendard_bold,
                            fontSize = 18.sp
                        )
                        Text(
                            text = quize.data.qtTip
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))
                    Row {
                        val correctAnswer = quize.data.qtCmt == "x"
                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (selectedAnswer == "x") Color(0xFFFC7C7C) else Color(
                                        0xFFF6D0D1
                                    )
                                )
                                .clickable {
                                    if (selectedAnswer != "x") {
                                        selectedAnswer = "x"
                                        state = true
                                        if (correctAnswer) {
                                            Log.d("TAG", "GrapeSeedQuize: 정답")
                                        } else {
                                            Log.d("TAG", "GrapeSeedQuize: 오답")
                                        }
                                    }
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                tint = if(selectedAnswer == "x") Color.White else Color(0xFFDDBBBC)
                            )
                        }

                        Spacer(modifier = Modifier.width(10.dp))

                        Box(
                            modifier = Modifier
                                .weight(0.5f)
                                .height(40.dp)
                                .clip(RoundedCornerShape(10.dp))
                                .background(
                                    if (selectedAnswer == "o") Color(0xFF7CB3FC) else Color(
                                        0xFFDFE8F6
                                    )
                                )
                                .clickable {
                                    if (selectedAnswer != "o") {
                                        selectedAnswer = "o"
                                        state = true
                                        if (!correctAnswer) {
                                            Log.d("TAG", "GrapeSeedQuize: 정답")
                                        } else {
                                            Log.d("TAG", "GrapeSeedQuize: 오답")
                                        }
                                    }
                                }
                        ) {
                            Icon(
                                imageVector = Icons.Default.Done,
                                contentDescription = null,
                                modifier = Modifier
                                    .fillMaxSize()
                                    .align(Alignment.Center),
                                tint = if(selectedAnswer == "o") Color.White else Color(0xFFC9D1DD)
                            )
                        }
                    }
                }
            }
        }
    }
}