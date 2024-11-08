package com.nohjason.cheongfordo.screens.quiz.quiz_main

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.screens.login.Screens
import com.nohjason.cheongfordo.screens.quiz.QuizButton
import com.nohjason.cheongfordo.screens.quiz.data.PlayData
import com.nohjason.cheongfordo.screens.quiz.data.QuestionResponse
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.pretendard_extra_bold
import com.nohjason.cheongfordo.ui.theme.pretendard_medium

@Composable
fun QuizMainScreen(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel,
    token: String
) {

    val scrollState = rememberScrollState()
    var selectedLevel by remember { mutableStateOf(1) }

    Column(
        Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA))
            .verticalScroll(scrollState)
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceAround,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            //트로피
            IconButton(
                onClick = { navHostController.navigate("myAlias") },
                modifier = Modifier
                    .padding(start = 300.dp, top = 5.dp)
                    .size(40.dp)
                    .clip(CircleShape)
                    .border(color = Color(0xFFC8CACF), width = 1.dp, shape = CircleShape) // 외곽선 추가
            ) {
                Icon(
                    modifier = Modifier
                        .padding(8.dp)
                        .size(24.dp),
                    painter = painterResource(id = R.drawable.ic_noun),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

            //퀴즈
            Column(
                modifier = Modifier
                    .padding(top = 40.dp, start = 28.dp)
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
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
                        fontFamily = pretendard_extra_bold,
                        color = MinariBlue,
                        fontSize = 30.sp
                    )
                }
                Text(
                    text = "총 3개의 난이도",
                    fontFamily = pretendard_medium,
                    fontSize = 13.sp
                )
            }

        }
        Spacer(modifier = Modifier.height(30.dp))
        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_easy),
            imgResId = R.drawable.img_easy,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFFFF64F5),
            lavel = "LV_1",
            coment = "제일 쉬운 난이도",
            onClick = {
                //더미코드
//                val dataList = selectPlayData(qestionAll = easyQuestionResponse, level = 2)
//                quizViewModel.initializePlayData(data = dataList)
//                navHostController.navigate("quizplay")

//                // 서버 코드
                selectedLevel = 1
                quizViewModel.getQuestion(selectedLevel, token)
                navHostController.navigate(Screens.QuizPlaycreen.rout)
            }
        )
        Spacer(modifier = Modifier.height(30.dp))
        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_nomal),
            imgResId = R.drawable.img_nomal,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFF23FF9C),
            lavel = "LV_2",
            coment = "공부 좀 했다면 이건 어떤가요?",
            onClick = {
                //더미코드
//                val dataList = selectPlayData(qestionAll = nomalQuestionResponse, level = 2)
//                quizViewModel.initializePlayData(data = dataList)
//                navHostController.navigate("quizplay")

                //서버코드
                selectedLevel = 2
                quizViewModel.getQuestion(selectedLevel, token)
                navHostController.navigate(Screens.QuizPlaycreen.rout)
            }
        )

        Spacer(modifier = Modifier.height(30.dp))
        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_hard),
            imgResId = R.drawable.img_hard,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFFFF3C52),
            lavel = "LV_3",
            coment = "이건 모를걸요!",
            onClick = {
//                더미코드
//                val dataList = selectPlayData(qestionAll = hardQuestionResponse, level = 3)
//                quizViewModel.initializePlayData(data = dataList)
//                navHostController.navigate("quizplay")


                //서버코드
                selectedLevel = 3
                quizViewModel.getQuestion(selectedLevel, token)
                navHostController.navigate(Screens.QuizPlaycreen.rout)
            }
        )

        LaunchedEffect(key1 = quizViewModel.questionData.collectAsState().value, key2 = selectedLevel) {
            val quizData = quizViewModel.questionData.value
            if (quizData != null) {
                val dataList = selectPlayData(qestionAll = quizData, level = selectedLevel)  // 선택된 레벨 사용
                quizViewModel.initializePlayData(data = dataList)
            }
        }
    }
}

private fun selectPlayData(qestionAll: QuestionResponse, level: Int): PlayData {
    val qtSelected = qestionAll.data.shuffled().take(10)
    return PlayData(
        userCurrent = 0,         // 현재 유저 진행 상황
        point = 0,               // 초기 포인트
        qtNum = 0,               // 첫 번째 문제부터 시작
        qtList = qtSelected,
        qtLevel = level// 10개의 질문을 담은 리스트
    )
}

