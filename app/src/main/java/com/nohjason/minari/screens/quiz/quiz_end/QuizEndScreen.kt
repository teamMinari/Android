package com.nohjason.minari.screens.quiz.quiz_end

import android.icu.text.CaseMap.Title
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun QuizEndScreen(
    quizViewModel: QuizViewModel,
    navController: NavController
){
    val playDataState = quizViewModel.playData.collectAsState()
    val playData = playDataState.value
    val point = playData?.point ?: 0
    val current = playData?.userCurrent ?: 0
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ){

        TitleBar(onClick = { navController.navigate(BottomBarScreen.Quiz.rout) })
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
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp
        )

        val currentText = buildAnnotatedString {
            // "총 " 부분
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 20.sp)) {
                append("총 ")
            }

            // correctAnswer.toString() 부분
            withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, fontSize = 20.sp, color = Color(0xFF373DD6))){ // 여기서 color는 0xFF37으로 설정합니다.
                append(current.toString())
            }

            // "문제를 맞췄어요!" 부분
            withStyle(style = SpanStyle(fontWeight = FontWeight.Medium, fontSize = 20.sp)) {
                append("문제를 맞췄어요!")
            }
        }
        Text(
            text = currentText,
            fontWeight = FontWeight.Medium,
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
                text = "My 포인트",
                color = Color.White,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            Spacer(modifier = Modifier.width(38.dp))
            Text(
                text = point.toString()+"P",
                color = Color.White,
                fontWeight = FontWeight.Bold,
                fontSize = 40.sp
            )
        }
    }
}