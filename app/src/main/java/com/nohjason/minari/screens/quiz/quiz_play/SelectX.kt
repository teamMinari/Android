package com.nohjason.minari.screens.quiz.quiz_play


import android.annotation.SuppressLint
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.quiz.clickOnce
import com.nohjason.minari.screens.quiz.data.QuizViewModel

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SeletX(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel = viewModel()
) {
    BackHandler(enabled = true){}

    val playData = quizViewModel.playData.value

    val qtNum = playData?.qtNum ?: 0

    val qtContents = playData?.qtList?.getOrNull(qtNum)?.qtContents ?: "No content available"
    val qtAnswer = playData?.qtList?.getOrNull(qtNum)?.qtAnswer ?: "No answer available"
    val qtCmt = playData?.qtList?.getOrNull(qtNum)?.qtCmt ?: "No comment available"
    val qtSize = playData?.qtList?.size ?: 9

    val context = LocalContext.current

    println(quizViewModel.playData.value)

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
                        containerColor = Color(0xFFFC7C7C)
                    ),
                    onClick = {
                        //Button바꿔야함
                    },
                ) {
                    Image(
                        modifier = Modifier
                            .width(90.dp)
                            .height(98.dp),
                        painter = painterResource(id = R.drawable.emoji_x_color),
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
                        containerColor = Color(0xFFEAEAEA)
                    ),
                    onClick = {
                        //Button바꿔야함
                    }
                ) {
                    Image(
                        modifier = Modifier
                            .width(90.dp)
                            .height(98.dp),
                        painter = painterResource(id = R.drawable.emoji_o),
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
                if (qtAnswer == true) {
                    Text(
                        text = "오답",
                        fontWeight = FontWeight.Bold,
                        fontSize = 20.sp
                    )
                } else {
                    Text(
                        text = "정답",
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
    }
}

@Preview
@Composable
fun PreSelectX(){
    val nav = rememberNavController()
    SeletX(navHostController = nav)
}