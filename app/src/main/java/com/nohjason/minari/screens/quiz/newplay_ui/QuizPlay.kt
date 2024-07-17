package com.nohjason.minari.screens.quiz.newplay_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.play_ui.pretendardFamily
import com.nohjason.minari.screens.quiz.play_ui.queIDnum
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun PlayScreen(

){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        IconButton(onClick = {

        },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }

        Row (modifier = Modifier.padding(end = 272.dp, top = 60.dp)){
            Text(text = "유저 대답 수",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
            Text(text = "/10",
                fontFamily = pretendardFamily,
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3B88FB)
            )
        }


        Text(text = "퀴즈질문",
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(top = 5.dp, end = 110.dp, start = 45.dp),
        )


        Row (modifier = Modifier.padding(top = 35.dp)) {
            TextButton(
                onClick = {
                    //정답 확인 및 포인트 증가

                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(
                    painterResource(
                    id = R.drawable.o_not),
                    contentDescription = null,
                )
            }
            var ans by remember { mutableStateOf(false) }
            var queIDnum by remember { mutableStateOf(0) }

            //버튼X------------------------------
            TextButton(
                onClick = {
                          ans = !ans
                    //정답 확인 및 포인트 증가
                },
                modifier = Modifier
                    .wrapContentSize()
                    .width(150.dp)
                    .height(180.dp)
                    .padding(start = 5.dp)
                    .background(color = Color(0xFFEAEAEA), shape = RoundedCornerShape(10.dp))
            ) {
                Image(painterResource(id = R.drawable.x_not), contentDescription = null)

            }
        }


        Button(
            onClick = {
                //화면 넘기기
                queIDnum += 1 },
            modifier = Modifier
                .width(305.dp)
                .padding(top = 100.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ) {
            Text(text = "모르겠어요.")
        }


        if (ans){
            Text(text = "해설")
        }
    }
}

@Composable
fun QuestionCmt(
    //값 받아서 정답/오답 여부 체크
    //해설 글 받기
){
    Text(text = "해설 글")
}

@Preview
@Composable
fun PrePlay(){
    PlayScreen()
}