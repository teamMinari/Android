package com.nohjason.minari.screens.QizeScreen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.Question
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.ui.theme.MinariBlue
import kotlin.math.log
import kotlin.random.Random

fun queIDList(): List<Int> {
    val random = MutableList(QuestionData.getQuestion().size) { 0 }
    for (i in random.indices) {
        random[i] = i
    }
    random.shuffle(Random(System.currentTimeMillis()))
    return random
}
var queIDList = queIDList()
var queIDnum = 0
val pretendardFamily = FontFamily(
    Font(R.font.pretendard_semibold, FontWeight.SemiBold),
    Font(R.font.pretendard_medium, FontWeight.Medium),
    Font(R.font.pretendard_regular, FontWeight.Thin),
    Font(R.font.pretendard_bold, FontWeight.Bold),)



@Composable
fun QuizScreen_play(
    que: List<Question>,
    navController: NavHostController,
    user: TemporaryPoint,
) { Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        IconButton(onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }

        Row (modifier = Modifier.padding(end = 272.dp, top = 60.dp)){
            Text(text = user.replies.toString(),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(text = "/10",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB))
        }

        Text(text = que[queIDList[queIDnum]].question,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp, start = 5.dp),

            )


        Row (modifier = Modifier.padding(top = 35.dp)) {
            //버튼O------------------------------
            TextButton(
                onClick = {
                    user.user_answer = "O"
                    user.replies += 1
                    if (que[queIDList[queIDnum]].answer == "O") {
                        user.point += 1//포인트 값 정해야함
                        user.correct += 1
                    }
                    navController.navigate("quizQuestionORoute")

                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(
                    id = R.drawable.o),
                    contentDescription = null,
                    colorFilter = ColorFilter.tint(Color(0x585858))
                )
            }

            //버튼X------------------------------
            TextButton(
                onClick = {
                    user.user_answer = "X"
                    user.replies += 1
                    navController.navigate("quizQuestionXRoute")
                    if (que[queIDList[queIDnum]].answer == "X") {
                        user.point += 1//포인트 값 정해야함
                        user.correct += 1
                    }
                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.x), contentDescription = null)

            }
        }


        Button(
            onClick = {
                queIDnum += 1
                user.replies += 1
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


//O선택
@Composable
fun Commentary_CorrectO(
    navController: NavHostController,
    que: List<Question>,
    user: TemporaryPoint
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        IconButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }

        Row(modifier = Modifier.padding(end = 272.dp, top = 60.dp)) {
            Text(
                text = user.replies.toString(),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(
                text = "/10",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
        }

        Text(
            text = que[queIDList[queIDnum]].question,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp)
        )


        Row(modifier = Modifier.padding(top = 35.dp)) {
            TextButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(MinariBlue, MinariBlue),
                            start = Offset(1300f, 800f),
                            end = Offset(300f, 0f),
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
                Image(painterResource(id = R.drawable.o), contentDescription = null)
            }

            TextButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.x), contentDescription = null)

            }
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxWidth()
        ) {
            //정답 나누고 포인트
            val correct_answer =
                if (que[queIDList[queIDnum]].answer == user.user_answer) "정답!" else "오답"

            Text(
                text = correct_answer,
                Modifier.padding(end = 250.dp, top = 65.dp),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.SemiBold,
                fontSize = 17.sp,
                color = MinariBlue
            )

            Text(
                text = que[queIDList[queIDnum]].Commentary,
                Modifier.padding(end = 40.dp,start = 5.dp),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Medium,
                fontSize = 15.sp
            )

            Button(
                onClick = {
                    if (user.replies > 9) {
                        queIDnum = 0
                        queIDList = queIDList()
                        navController.navigate("quizComentoryRoute")
                    } else {
                        navController.navigate("quizQuestionRoute")
                    }
                    queIDnum += 1
                },
                modifier = Modifier
                    .width(305.dp)
                    .padding(top = 1.dp),
                colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
            ) {
                Text(text = "다음")
            }
        }
    }
}


//X선택
@Composable
fun Commentary_CorrectX(
    navController: NavHostController,
    que: List<Question>,
    user: TemporaryPoint
) { Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxWidth()
    ) {
        Row(modifier = Modifier.padding(end = 272.dp, top = 60.dp)) {
            Text(
                text = user.replies.toString(),
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(
                text = "/10",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
        }

        Text(
            text = que[queIDList[queIDnum]].question,
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp)
        )


        Row(modifier = Modifier.padding(top = 35.dp)) {
            TextButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.o), contentDescription = null)
            }

            TextButton(
                onClick = {},
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(
                        brush = Brush.linearGradient(
                            colors = listOf(MinariBlue, MinariBlue),
                            start = Offset(1300f, 800f),
                            end = Offset(300f, 0f),
                        ),
                        shape = RoundedCornerShape(10.dp)
                    )
            ) {
               Image(painterResource(id = R.drawable.x), contentDescription = null)
            }
        }

        //정답 나누고 포인트
        val correct_answer = if (que[queIDList[queIDnum]].answer == user.user_answer) "정답!" else "오답"
        if (que[queIDList[queIDnum]].answer == user.user_answer) {
            user.point += 1//포인트 값 정해야함
            user.correct += 1
        }
        Text(
            text = correct_answer,
            Modifier.padding(end = 250.dp, top = 65.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 17.sp,
            color = MinariBlue
        )

        Text(
            text = que[queIDList[queIDnum]].Commentary,
            Modifier.padding(end = 40.dp),
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            fontSize = 15.sp
        )

        Button(
            onClick = {
                if (user.replies > 9) {
                    queIDnum = 0
                    queIDList = queIDList()
                    navController.navigate("quizComentoryRoute")
                } else {
                    navController.navigate("quizQuestionRoute")
                }
                queIDnum += 1
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

@Preview(showBackground = true)
@Composable
fun QuizScreenPrevirw(){
//    QuizScreen()
}
