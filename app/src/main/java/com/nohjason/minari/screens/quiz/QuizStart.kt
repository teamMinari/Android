@file:JvmName("QuizStartScreenKt")

package com.nohjason.minari.screens.quiz

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import androidx.room.util.query
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.Question
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariPurple
import kotlin.random.Random
fun queIDList(): List<Int> {
    val numbers = (0..9).toMutableList()  //문제 몇가지 있는지 갯수 필요
    numbers.shuffle(Random(System.currentTimeMillis()))
    return numbers
}

val pretendardFamily = FontFamily(
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Thin),
    Font(R.font.pretendard_bold, FontWeight.Bold),)



@Composable
fun QuizScreen(
    navController: NavHostController,
    user: TemporaryPoint,
) {
    val TestList = arrayListOf(5)
    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        QuizPoint_box(user)

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

@Composable
fun QuizPoint_box(
    user: TemporaryPoint
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.1475f)
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(MinariPurple, MinariBlue),
                    start = Offset(1300f, 800f),
                    end = Offset(300f, 0f),
                ),
                shape = RoundedCornerShape(
                    topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(50.dp), bottomStart = CornerSize(50.dp)
                )
            ),
        contentAlignment = Alignment.Center
    ) {
        Row (verticalAlignment = Alignment.CenterVertically){
            Text(text = "My 포인트",
                fontSize = 20.sp, fontFamily = pretendardFamily, fontWeight = FontWeight.SemiBold,
                color = Color.White
            )
            Text(text = user.point.toString(),
                modifier = Modifier.padding(start = 38.dp),
                fontSize = 40.sp,fontFamily = pretendardFamily, fontWeight = FontWeight.Bold,
                color = Color.White
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizScreenPrevirw(){
    QuizScreen(navController = rememberNavController(), user = Temporary_pointData.getPoint())
}
