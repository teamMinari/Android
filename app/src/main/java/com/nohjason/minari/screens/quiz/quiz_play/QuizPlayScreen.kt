package com.nohjason.minari.screens.quiz.quiz_play

import android.widget.Toast
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
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.quiz.clickOnce
import com.nohjason.minari.screens.quiz.data.QuizViewModel

@Composable
fun QuizPlayScreen(
    navHostController: NavHostController,
    quizViewModel: QuizViewModel = viewModel()
){

    val playData by quizViewModel.playData.collectAsState()
    println("플레이화면 데이터확인 "+ playData)

    val qtNum = playData?.qtNum ?: 0

    val qtContents = playData?.qtList?.getOrNull(qtNum)?.qtContents ?: "No content available"
    val qtTip = playData?.qtList?.getOrNull(qtNum)?.qtTip ?: "No tip available"
    val qtAnswer = playData?.qtList?.getOrNull(qtNum)?.qtAnswer ?: false


    val context = LocalContext.current

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(),
        contentAlignment = Alignment.Center
    ){
        Column (
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth(0.85f),
        ){

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
            Row (
                modifier = Modifier.padding(top = 20.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ){
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
                        try {
                            quizViewModel.submitAnswer(userAnswer = false, correctAnswer = qtAnswer)
                            navHostController.navigate("Select_X")
                        } catch (e: Exception){
                            Toast.makeText(context, "오류가 생겼습니다! 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                            navHostController.navigate(BottomBarScreen.Home.rout)
                        }
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
                        containerColor = Color(0xFFE4E4E4)
                    ),
                    onClick = {
                        try {
                            quizViewModel.submitAnswer(userAnswer = true, correctAnswer = qtAnswer)
                            navHostController.navigate("Select_O")
                        } catch (e: Exception){
                            Toast.makeText(context, "오류가 생겼습니다! 다시 시도해주세요.", Toast.LENGTH_SHORT).show()
                            navHostController.navigate(BottomBarScreen.Home.rout)
                        }
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
            Row (
                modifier = Modifier.padding(top = 20.dp)
            ){
                Icon(painter = painterResource(id = R.drawable.emoji_tip),
                    contentDescription = null, tint = Color.Unspecified)
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    text = "Tip"
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = qtTip
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreQuizPlay(){
//    QuizPlayScreen(
//        qtArray = dummyQuestionDataList
//    )
//}
