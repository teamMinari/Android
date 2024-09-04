package com.nohjason.minari.screens.quiz.quiz_end

import android.icu.text.CaseMap.Title
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R

@Composable
fun QuizEndScreen(
    point: Int,
    correctAnswer:Int
){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA)),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(14.dp)
    ){

        TitleBar()
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
                append(correctAnswer.toString())
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
        PointBox(point = point)
    }
}

@Preview
@Composable
fun Preend(){
    QuizEndScreen(
        point = 150,
        correctAnswer = 7
    )
}