package com.nohjason.cheongfordo.screens.ui.titlebar

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.ui.theme.pretendard_semibold

@Composable
fun TitleBar(
    title: String? = null,
    imgResId: Int? = null,
    onClick: (() -> Unit)? = null
){
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 1.dp,
                color = Color(0xFFECEFFB), // Stroke 색상
                shape = RoundedCornerShape(
                    bottomStart = 0.dp,
                    bottomEnd = 0.dp
                ) // Bottom만 stroke 적용
            )
            .height(60.dp)
            .background(color = Color.White),
        verticalAlignment = Alignment.CenterVertically
    ){
        Row(
            modifier = Modifier
                .padding(start = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                modifier = Modifier
                    .width(12.dp)
                    .height(18.dp)
                    .clickable(
                        indication = null,
                        interactionSource = remember { MutableInteractionSource() }
                    )  { onClick?.invoke() },
                painter = painterResource(id = R.drawable.ic_back),
                contentDescription = null,
                tint = Color.Unspecified
            )
            if (imgResId != null){
                Icon(
                    tint = Color.Unspecified,
                    painter = painterResource(id = imgResId),
                    contentDescription = null,
                    modifier = Modifier
                        .padding(start = 25.dp)
                        .size(24.dp)
                )
            }
            if (title != null){
                Text(
                    text = title,
                    fontSize = 17.sp,
                    fontFamily = pretendard_semibold,
                    modifier = Modifier
                        .padding(start = 10.dp)
                )
            }
        }


    }
}

//@Preview
//@Composable
//fun PreTitle(){
//    TitleBar( title = "ㅎㅇㅎㅇ", imgResId = R.drawable.grape)
//}