package com.nohjason.minari.screens.quiz.quiz_play


import android.annotation.SuppressLint
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.quiz.QuizPopup
import com.nohjason.minari.screens.quiz.clickOnce
import com.nohjason.minari.screens.quiz.data.QuizViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SeletO(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel
) {
    var showPopup by remember { mutableStateOf(false) }

    BackHandler(enabled = true){}

    val playData = quizViewModel.playData.value

    val qtNum = playData?.qtNum ?: 0

    val qtContents = playData?.qtList?.getOrNull(qtNum)?.qtContents ?: "No content available"
    val qtAnswer = playData?.qtList?.getOrNull(qtNum)?.qtAnswer ?: "No answer available"
    val qtCmt = playData?.qtList?.getOrNull(qtNum)?.qtCmt ?: "No comment available"
    val qtSize = playData?.qtList?.size ?: 9

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.85f),
        ) {

            //문제-------------------------------
            Text(
                modifier = Modifier.padding(top = 77.dp),
                color = Color(0xFF363CD5),
                fontSize = 25.sp,
                fontWeight = FontWeight.SemiBold,
                text = "${qtNum + 1}/10"
            )
            Text(
                modifier = Modifier
                    .padding(top = 10.dp),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                text = qtContents
            )


            //버튼---------------------------------------------------------------------------------------------
            Row(
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                Button(
                    //x버튼
                    modifier = Modifier
                        .weight(1f)
                        .height(227.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFE4E4E4)
                    ),
                    onClick = {
                        //Button바꿔야함
                    },
                ) {
                    Image(
                        modifier = Modifier
                            .width(90.dp)
                            .height(98.dp),
                        painter = painterResource(id = R.drawable.emoji_x),
                        contentDescription = null,
                    )
                }
                Button(//o버튼
                    modifier = Modifier
                        .weight(1f)
                        .width(150.dp)
                        .height(227.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFB0CDF5)
                    ),
                    onClick = {

                        //Button바꿔야함
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .width(90.dp)
                            .height(98.dp),
                        painter = painterResource(id = R.drawable.emoji_o_color),
                        contentDescription = null
                    )
                }
            }

            //해설-------------------------------
            Row(
                modifier = Modifier.padding(top = 20.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.emoji_tip),
                    contentDescription = null, tint = Color.Unspecified
                )
                //함수값
                if (qtAnswer == true) {
                    Text(
                        text = "정답",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                } else {
                    Text(
                        text = "오답",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                }
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = qtCmt
            )
        }

        var isClickable by remember { mutableStateOf(true) }

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .padding(bottom = 52.dp)
                .fillMaxWidth(0.85f)
                .height(45.dp),
            shape = RoundedCornerShape(30.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color(0xFF363CD5)
            ),
            onClick = {
                if (isClickable) {
                    isClickable = false // 클릭 후 다시 클릭하지 못하게 설정
                    if (qtNum + 2 > qtSize) {
                        navHostController.navigate(Screens.QuizEndScreen.rout)
                    } else {
                        quizViewModel.nextQuestion()
                        navHostController.navigate("quizplay")
                    }
                }
            },
            enabled = isClickable
        ) {
            Text(text = "다음")
        }

// 1초 뒤 다시 클릭 가능하도록 설정
        if (!isClickable) {
            LaunchedEffect(Unit) {
                kotlinx.coroutines.delay(1000L)
                isClickable = true
            }
        }

        BackHandler(enabled = true){
            showPopup = true
        }

        if (showPopup) {
            QuizPopup(
                onDismissRequest = {
                    showPopup = false
                }, // 취소
                onConfirmation = {
                    showPopup = false
                    navHostController.navigate(BottomScreen.Quiz.rout)
                },  // 확인
                dialogTitle = "뒤로 돌아가기",
                dialogText = "정말로 퀴즈를 종료시겠습니까?",
                icon = painterResource(id = R.drawable.ic_x)
            )
        }
    }
}