package com.nohjason.minari.screens.quiz.quiz_end

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun TitleBar(
    title: String? = null,
    imgResId: Int? = null
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFECEFFB), // Stroke 색상
                shape = RoundedCornerShape(bottomStart = 0.dp, bottomEnd = 0.dp) // Bottom만 stroke 적용
            )
            .height(60.dp)
            .background(color = Color.White)
            .padding(top = 28.dp)
    ){
        Box(modifier = Modifier.padding(start = 16.dp),){
            Icon(
                modifier = Modifier
                    .width(12.dp)
                    .height(18.dp),
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }


    }
}

@Preview
@Composable
fun PreTitle(){
    TitleBar()
}