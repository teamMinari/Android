package com.nohjason.minari.screens.quiz.quiz_main

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.gson.Gson
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.PlayData
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.minari.screens.quiz.dummyQtList
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun QuizMainScreen(
    qtAll: QuestionResponse,
    navHostController: NavHostController
){
    val scrollState = rememberScrollState()

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
            lavel ="Lavel 1",
            coment = "제일 쉬운 난이도",
            onClick = {
                val qtList = selectPlayData(qtAll = qtAll.data)
                val playDataJson = Gson().toJson(qtList)
                navHostController.navigate("quizplay/${playDataJson}")
                //서버 레벨 별 값 요구

            },
        )
        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_nomal),
            imgResId = R.drawable.img_nomal,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFF23FF9C),
            lavel ="Lavel 2",
            coment = "공부 좀 했다면 이건 어떤가요?",
            onClick = {
                //노말 서버 끌고오기
            },
        )

        QuizButton(
            icResId = ImageVector.vectorResource(id = R.drawable.ic_hard),
            imgResId = R.drawable.img_hard,
            color1 = Color(0xFF6889FF),
            color2 = Color(0xFFFF3C52),
            lavel ="Lavel 3",
            coment = "이건 모를걸요!",
            onClick = {
                //하드 서버 끌고오기
            },
        )
    }
}


fun selectPlayData(qtAll: List<QuestionData>): PlayData {
    val qtSelected = qtAll.shuffled().take(10)
    return PlayData(
        userCurrent = 0,         // 현재 유저 진행 상황, 0으로 초기화
        point = 0,               // 초기 포인트, 0으로 초기화
        qtNum = 0,               // 첫 번째 문제부터 시작, 0으로 초기화
        qtList = qtSelected // 10개의 질문을 담은 리스트
    )
}


//    {
//        "status": 200,
//        "message": "질문 난이도 별 조회 성공!",
//        "data": [
//        {
//            "qtContents": "string",
//            "qtAnswer": true,
//            "qtCmt": "string",
//            "qtTip": "string",
//            "qtDifficulty": "LV_1"
//        }
//        ]
//    }
