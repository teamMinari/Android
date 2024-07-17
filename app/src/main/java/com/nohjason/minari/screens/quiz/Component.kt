package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun PointBox(){
    Column {
        Box(modifier = Modifier.fillMaxWidth())
    }
}

//참고
//@Composable
//fun QuizPoint_box(
//    user: TemporaryPoint
//) {
//    Box(
//        modifier = Modifier
//            .fillMaxWidth()
//            .fillMaxHeight(0.1475f)
//            .background(
//                brush = Brush.linearGradient(
//                    colors = listOf(MinariPurple, MinariBlue),
//                    start = Offset(1300f, 800f),
//                    end = Offset(300f, 0f),
//                ),
//                shape = RoundedCornerShape(
//                    topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp),
//                    bottomEnd = CornerSize(50.dp), bottomStart = CornerSize(50.dp)
//                )
//            ),
//        contentAlignment = Alignment.Center
//    ) {
//        Row (verticalAlignment = Alignment.CenterVertically){
//            Text(text = "My 포인트",
//                fontSize = 20.sp, fontFamily = pretendardFamily, fontWeight = FontWeight.SemiBold,
//                color = Color.White
//            )
//            Text(text = user.point.toString(),
//                modifier = Modifier.padding(start = 38.dp),
//                fontSize = 40.sp,fontFamily = pretendardFamily, fontWeight = FontWeight.Bold,
//                color = Color.White
//            )
//        }
//    }
//}

@Composable
fun starBar(){
    //별점
}