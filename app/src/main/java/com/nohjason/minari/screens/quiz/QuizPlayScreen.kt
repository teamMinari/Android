package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.google.gson.Gson
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.data.PlayData
import com.nohjason.minari.screens.quiz.data.QuestionData

@Composable
fun QuizPlayScreen(
    qestion: PlayData
){
    val qtAnswer = qestion.qtList[qestion.qtNum].qtAnswer
    val qtCmt = qestion.qtList[qestion.qtNum].qtCmt
    val qtTip = qestion.qtList[qestion.qtNum].qtTip
    val qtContents = qestion.qtList[qestion.qtNum].qtContents

//    var qtNum by remember { mutableStateOf(qestion.qtNum) }
//    var nowpoint by remember { mutableStateOf(qestion.point) }
    val currentQuestion = qestion.qtList.getOrNull(qestion.qtNum)


    Box(modifier = Modifier
        .fillMaxSize(),
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
                text = "${qestion.qtNum + 1}/10"
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
                Button( //x버튼
                    modifier = Modifier
                        .weight(1f)
                        .height(227.dp),
                    shape = RoundedCornerShape(10.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color(0xFFFC7C7C)
                    ),
                    onClick = {
//                        val qtList =electPlayData(qtAll = qtAll.data)
//                        val playDataJson = Gson().toJson(qtList)
                        //Gson으로 데이터 묶어서 화면이동
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
                        containerColor = Color(0xFFB0CDF5)
                    ),
                    onClick = {

                        //Gson으로 데이터 묶어서 화면이동
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
            Row (
                modifier = Modifier.padding(top = 20.dp)
            ){
                Icon(painter = painterResource(id = R.drawable.emoji_tip),
                    contentDescription = null, tint = Color.Unspecified)
                Text(
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    text = qtCmt
                )
            }
            Text(
                modifier = Modifier.padding(4.dp),
                text = qtTip
            )
        }
    }
    
}

fun handleAnswer(
    userAnswer:Boolean,
    currentAnswer: Boolean,
    point: Int,
    userCurrent: Int,
    qtNum: Int,
    qtList: List<QuestionData>
) {
    if (userAnswer == currentAnswer) {
        //정답 처리 로직(포인트, 정답 수, num 추가)
        return //PlayData(
//            userCurrent = 0,
//            point = 0,
//            qtNum = 0,
//            qtList = qtSelected
//        )
    } else {
        // 오답 처리 로직(num 추가)
        return//PlayData(
//            userCurrent = 0,
//            point = 0,
//            qtNum = 0,
//            qtList = qtSelected
//        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreQuizPlay(){
//    QuizPlayScreen(
//        qtArray = dummyQuestionDataList
//    )
//}
