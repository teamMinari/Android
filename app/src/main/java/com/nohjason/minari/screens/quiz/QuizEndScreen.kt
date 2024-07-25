package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariPurple


@Composable
fun QuizEndScreen(
    navController: NavHostController,
    user: TemporaryPoint,
){
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,){
        IconButton(onClick = {
            navController.navigate(Screen.Home.rout)
            user.correct = 0
            },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }
        Image(painterResource(R.drawable.suprise_emoji), contentDescription = "suprise",
            modifier = Modifier
                .size(150.dp)
                .offset(y = 90.dp)

        )
        Text(text = "대단해요!",
            fontFamily = com.nohjason.minari.screens.QizeScreen.pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            fontSize = 30.sp,
            modifier = Modifier.padding(top = 100.dp)
        )
        Text(
            text = buildAnnotatedString {
                append("총 ")
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold, color = Color(0xFF373DD6))) {
                    append("${user.correct}")
                }
                append("문제를 맞췄어요!")
            },
            fontFamily = pretendardFamily,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(bottom = 10.dp, top = 5.dp)
        )

        Box(
            modifier = Modifier
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(MinariPurple, MinariBlue),
                        start = Offset(1300f, 800f),
                        end = Offset(300f, 0f)
                    ),
                    shape = RoundedCornerShape(
                        topStart = CornerSize(30.dp), topEnd = CornerSize(30.dp),
                        bottomEnd = CornerSize(30.dp), bottomStart = CornerSize(30.dp)
                    )
                )
                .width(330.dp)
                .height(120.dp),
            contentAlignment = Alignment.Center
        ){
            Row (verticalAlignment = Alignment.CenterVertically){
                Text(text = "My 포인트"
                    , color = Color.White
                    , fontFamily = pretendardFamily
                    , fontWeight = FontWeight.SemiBold
                    , fontSize = 20.sp
                )
                Text(text = user.point.toString()
                    , color = Color.White
                    , fontFamily = pretendardFamily
                    , fontWeight = FontWeight.Bold
                    , fontSize = 30.sp
                    , modifier = Modifier.padding(start = 20.dp)
                    )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizEndPrevirw(){
    QuizEndScreen(navController = rememberNavController(), user = Temporary_pointData.getPoint())
}