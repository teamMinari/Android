package com.nohjason.minari.screens.QizeScreen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.screens.quiz.data.Question
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.ui.theme.MinariBlue
import kotlin.random.Random

@Composable
fun QuizScreen_play(
    que: Question,
    navController: NavHostController,
    user: TemporaryPoint
) {


    Column (
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ){
        IconButton(onClick = { navController.popBackStack() },
            modifier = Modifier.padding(end = 361.dp).background(color = Color.Black)
        ) {
//            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }

        Row (modifier = Modifier.padding(end = 272.dp, top = 60.dp)){
            Text(text = user.replies.toString(),
//                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(text = "/10",
//                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB))
        }

        Text(text = que.question,
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp))


        Row (modifier = Modifier.padding(top = 35.dp)) {
            //버튼O------------------------------
            TextButton(
                onClick = {
                    user.user_answer = "O"
                    if(que.answer == "O"){
                        user.Correct+=1
                        navController.navigate("quizQuestionORoute/${que.id}")
                    } else{
                        navController.navigate("quizQuestionXRoute/${que.id}")
                    }
                    user.replies += 1
                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Text(text = "O")
//                Image(painterResource(id = R.drawable.qize_o), contentDescription = null)
            }

            //버튼X------------------------------
            TextButton(
                onClick = {
                    user.user_answer = "X"
                    if(que.answer == "O"){
                        navController.navigate("quizQuestionORoute/${que.id}")
                    } else{
                        user.Correct+=1
                        navController.navigate("quizQuestionXRoute/${que.id}")
                    }
                    user.replies += 1
                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Text(text = "X")
//                Image(painterResource(id = R.drawable.qize_x), contentDescription = null)

            }
        }


        Button(
            onClick = {
                que.id += 1
                navController.navigate("quizQuestionRoute")
                      },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ) {
            Text(text = "모르겠어요.")
        }
    }
}



@Composable
fun Commentary_CorrectO(
    navController: NavHostController,
    que: Question,
    user: TemporaryPoint
){
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        val correct_answer = if(que.answer == user.user_answer) "정답!" else "오답"
        user.point = user.point+user.Correct//포인트 얼마씩 추가할지 지정해야함
        Text(
            text = correct_answer,
            Modifier.padding(end = 250.dp, top = 65.dp),
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MinariBlue)

        Text(text = que.Commentary,
            Modifier.padding(end = 40.dp),
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
       )

        Button(
            onClick = {
                navController.navigate("quizQuestionRoute")
            },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ) {
            Text(text = "다음")
        }
    }
}

@Composable
fun Commentary_CorrectX(
    navController: NavHostController,
    que: Question,
    user: TemporaryPoint
){
    val correct_answer = if(que.answer == user.user_answer) "정답!" else "오답"
    Column (horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()) {
        Text(text = correct_answer,
            Modifier.padding(end = 250.dp, top = 65.dp),
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MinariBlue)

        Text(text = que.answer,
            Modifier.padding(end = 40.dp),
//            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )

        Button(
            onClick = {
                if (user.replies > 11){
                    navController.navigate("quizStartRoute")
                } else{
                    navController.navigate("quizQuestionRoute")
                }
            },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ) {
            Text(text = "다음")
        }
    }
}



//@Preview(showBackground = true)
//@Composable
//fun QuizPlayPreview() {
//
//    val question = QuestionData.getQuestion().getOrNull(1) // 첫 번째 질문을 사용
//    if (question != null) {
//        QuizScreen_play() // 질문을 QuizScreen_play 함수에 전달
//    } else {
//        QuizEndScreen()
//    }
//}
