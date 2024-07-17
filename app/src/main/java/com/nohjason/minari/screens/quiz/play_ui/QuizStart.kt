@file:JvmName("QuizStartScreenKt")

package com.nohjason.minari.screens.quiz.play_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.minari.screens.quiz.PointBox
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.ui.theme.MinariBlue


@Composable
fun QuizScreen(
    navController: NavHostController,
    user: TemporaryPoint,
) {
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        PointBox()

        Text(
            text = "퀴즈를 시작하겠습니다!",
            modifier = Modifier
                .padding(top = 200.dp)
                .align(Alignment.CenterHorizontally),
            textAlign = TextAlign.Center,
            fontSize = 30.sp, fontWeight = FontWeight.SemiBold, fontFamily = pretendardFamily,
            color = Color.Black
        )

        Text(
            text = "총 10문제이며 O/X 형식으로 이루어져있습니다.",
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 6.dp)
                .align(Alignment.CenterHorizontally),
            fontWeight = FontWeight.Medium, fontFamily = pretendardFamily,
            color = MinariBlue
        )

        Button(  onClick = {
            user.replies = 0
            navController.navigate("quizQuestionRoute")
            },
            modifier = Modifier
                .padding(top = 30.dp)
                .align(Alignment.CenterHorizontally)
                .width(310.dp)
                .height(46.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = MinariBlue
            )
        ) {
            Text(
                text = "도전하기",
                color = Color.White,
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold
            )
        }
    }
}
