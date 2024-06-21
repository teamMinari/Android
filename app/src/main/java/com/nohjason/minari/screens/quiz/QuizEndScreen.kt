package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Text
import androidx.compose.material3.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.quiz.data.TemporaryPoint


@Composable
fun QuizEndScreen(
    navController: NavHostController,
    user: TemporaryPoint,
){
    Column {
        IconButton(onClick = {
            navController.navigate(BottomBarScreen.Home.rout)
            user.correct = 0
            },
            modifier = Modifier
                .padding(end = 361.dp)
        ) {
            Image(painterResource(id = R.drawable.befor_arrow), contentDescription = null)
        }
    Image(painterResource(R.drawable.suprise_emoji), contentDescription = "suprise")
        Text(text = "대단해요!", fontFamily = com.nohjason.minari.screens.QizeScreen.pretendardFamily,)
        Text(text = "총 " + user.correct + "문제를 맞췄어요!", fontFamily = pretendardFamily)
        
        Box(modifier = Modifier
            .height(330.dp)
            .width(125.dp)){
            Text(text = "My 포인트" + user.point.toString())
        }
    }
    
}