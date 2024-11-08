package com.nohjason.cheongfordo.screens.quiz.quiz_end_screen

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.navigation.bottombar.BottomScreen
import com.nohjason.cheongfordo.screens.quiz.data.PointRequest
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.screens.ui.titlebar.TitleBar
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.pretendard_bold
import com.nohjason.cheongfordo.ui.theme.pretendard_medium
import com.nohjason.cheongfordo.ui.theme.pretendard_semibold

@Composable
fun QuizEndScreen(
    quizViewModel: QuizViewModel,
    navController: NavController,
    token: String
){
    val playDataState = quizViewModel.playData.collectAsState()
    val playData = playDataState.value
    val point = playData?.point ?: 0
    val current = playData?.userCurrent ?: 0
    val reqPoint = PointRequest( pointToAdd = point)

    BackHandler(enabled = true){
        navController.navigate(BottomScreen.Quiz.rout)
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ){
        quizViewModel.postPoint(token=token, point=reqPoint)
        println("quizScreen"+reqPoint)

        TitleBar(onClick = { navController.navigate(BottomScreen.Quiz.rout) })
        Box(modifier = Modifier.padding(top = 50.dp)) {
            Image(
                modifier = Modifier
                    .width(330.dp)
                    .height(125.dp),
                painter = painterResource(id = R.drawable.emoji_suprise),
                contentDescription = null,
            )
        }
        Text(
            text = "정말 대단해요!",
            fontFamily = pretendard_semibold,
            fontSize = 30.sp
        )

        val currentText = buildAnnotatedString {
            // "총 " 부분
            withStyle(style = SpanStyle(fontFamily = pretendard_medium, fontSize = 20.sp)) {
                append("총 ")
            }

            // correctAnswer.toString() 부분
            withStyle(style = SpanStyle(fontFamily = pretendard_bold, fontSize = 20.sp, color = Color(0xFF373DD6))){ // 여기서 color는 0xFF37으로 설정합니다.
                append(current.toString())
            }

            // "문제를 맞췄어요!" 부분
            withStyle(style = SpanStyle(fontFamily = pretendard_medium, fontSize = 20.sp)) {
                append("문제를 맞췄어요!")
            }
        }
        Text(
            text = currentText,
            fontFamily = pretendard_medium,
            fontSize = 20.sp
        )


        //포인트 박스
        Row (
            modifier = Modifier
                .width(330.dp)
                .height(125.dp)
                .clip(RoundedCornerShape(30.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(MinariBlue, Color(0xFFCC00FF)),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                ),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = "획득한 포인트",
                color = Color.White,
                fontFamily = pretendard_semibold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(38.dp))
            Text(
                text = point.toString()+"P",
                color = Color.White,
                fontFamily = pretendard_bold,
                fontSize = 40.sp
            )
        }
    }
}