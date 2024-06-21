package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.ui.theme.MinariBlue


@Composable
fun QuizEndScreen(
    navController: NavHostController,
    user: TemporaryPoint,
){
    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally,){
        IconButton(onClick = {
            navController.navigate(BottomBarScreen.Home.rout)
            user.correct = 0
            },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }
        Image(painterResource(R.drawable.suprise_emoji), contentDescription = "suprise",
            modifier = Modifier
                .height(150.dp).width(150.dp)
                .padding(top = 90.dp)
        )
        Text(text = "대단해요!", fontFamily = com.nohjason.minari.screens.QizeScreen.pretendardFamily,)
        Text(text = "총 " + user.correct + "문제를 맞췄어요!", fontFamily = pretendardFamily)

        Box(
            modifier = Modifier.background(
                    brush = Brush.linearGradient(
                    colors = listOf(MinariBlue, MinariBlue),
                    start = Offset(1300f, 800f),
                    end = Offset(300f, 0f)),
                    shape = RoundedCornerShape(
                    topStart = CornerSize(30.dp), topEnd = CornerSize(30.dp),
                    bottomEnd = CornerSize(30.dp), bottomStart = CornerSize(30.dp)
                )
            ).width(330.dp).height(120.dp)
//            , horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Text(text = "My 포인트" + user.point.toString()
                , color = Color.White
                , fontFamily = pretendardFamily)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun QuizEndPrevirw(){
    QuizEndScreen(navController = rememberNavController(), user = Temporary_pointData.getPoint())
}