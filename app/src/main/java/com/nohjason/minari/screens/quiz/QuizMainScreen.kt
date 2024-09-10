package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.Dummy.easyQuestionResponse
import com.nohjason.minari.screens.quiz.data.Dummy.hardQuestionResponse
import com.nohjason.minari.screens.quiz.data.Dummy.nomalQuestionResponse
import com.nohjason.minari.screens.quiz.data.PlayData
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun QuizMainScreen(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel = viewModel()
){
    val scrollState = rememberScrollState()
    val coroutineScope = rememberCoroutineScope()//코루틴
    //val qtAll서버 끌고와서 넣기

    Column (
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA))
            .verticalScroll(scrollState)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //트로피
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(start = 300.dp, top = 5.dp)
            ) {
                Icon(
                    modifier = Modifier
                        .width(40.dp)
                        .height(40.dp),
                    painter = painterResource(id = R.drawable.ic_noun),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            //퀴즈
            Column (
                modifier = Modifier
                    .padding(top = 40.dp, start = 28.dp)
            ){
                Row (verticalAlignment = Alignment.CenterVertically){
                    Icon(
                        modifier = Modifier
                            .width(25.dp)
                            .height(25.dp),
                        painter = painterResource(id = R.drawable.quiz),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Text(
                        text = "Quiz",
                        fontWeight = FontWeight.ExtraBold,
                        color = MinariBlue,
                        fontSize = 30.sp
                    )
                }
                Text(
                    text = "총 3개의 난이도",
                    fontWeight = FontWeight.Medium,
                    fontSize = 13.sp
                )
            }

        }



        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_easy),
            imgResId = R.drawable.img_easy,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFFFF64F5),
            lavel ="LV_1",
            coment = "제일 쉬운 난이도",
            onClick = {
                val dataList = selectPlayData(qestionAll = easyQuestionResponse)
                quizViewModel.initializePlayData(data = dataList)
                navHostController.navigate("quizplay")

//                coroutineScope.launch {
//                    val qtAll = getQuestion(1)
//
//                    val dataList = selectPlayData(qestionAll = qtAll)
//                    quizViewModel.initializePlayData(data = dataList)
//                    navHostController.navigate("quizplay")
//                }
            },
        )
        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_nomal),
            imgResId = R.drawable.img_nomal,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFF23FF9C),
            lavel ="LV_2",
            coment = "공부 좀 했다면 이건 어떤가요?",
            onClick = {
                val dataList = selectPlayData(qestionAll = nomalQuestionResponse)
                quizViewModel.initializePlayData(data = dataList)
                navHostController.navigate("quizplay")
//                coroutineScope.launch {
//                    val qtAll = getQuestion(2)
//
//                    val dataList = selectPlayData(qestionAll = qtAll)
//                    quizViewModel.initializePlayData(data = dataList)
//                    navHostController.navigate("quizplay")
//                }
            },
        )

        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_hard),
            imgResId = R.drawable.img_hard,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFFFF3C52),
            lavel ="LV_3",
            coment = "이건 모를걸요!",
            onClick = {
                val dataList = selectPlayData(qestionAll = hardQuestionResponse)
                quizViewModel.initializePlayData(data = dataList)
                navHostController.navigate("quizplay")

//                coroutineScope.launch {
//                    val qtAll = getQuestion(3)
//
//                    val dataList = selectPlayData(qestionAll = qtAll)
//                    quizViewModel.initializePlayData(data = dataList)
//                    navHostController.navigate("quizplay")
//                }
            },
        )
    }
}

//데이터 초기화 값
fun selectPlayData(qestionAll: QuestionResponse): PlayData {
    val qtSelected = qestionAll.data.shuffled().take(10)
    println(qtSelected)
    return PlayData(
        userCurrent = 0,         // 현재 유저 진행 상황, 0으로 초기화
        point = 0,               // 초기 포인트, 0으로 초기화
        qtNum = 0,               // 첫 번째 문제부터 시작, 0으로 초기화
        qtList = qtSelected // 10개의 질문을 담은 리스트
    )
}


suspend fun getQuestion(level: Int): QuestionResponse {
    // Retrofit 인스턴스를 가져옴
    val apiService = RetrofitInstance.api

    return withContext(Dispatchers.IO) {
        try {
            // GET 요청을 보내고 응답을 받아옴
            val response = apiService.getQuestion(
                level =  level// 요청할 레벨
            )
            response // 서버 응답 반환
        } catch (e: Exception) {
            // 기타 예외 처리
            println("Error: ${e.message}")
            throw e // 필요에 따라 다시 던질 수 있음
        }
    }
}