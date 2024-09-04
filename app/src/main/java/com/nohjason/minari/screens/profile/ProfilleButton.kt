package com.nohjason.minari.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun ProfileButton(
    what:String,
    icon: Int,
    onClick: () -> Unit
) {
        Column(modifier = Modifier
            .width(170.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
            .padding(start = 24.dp)
        ){
            Icon(
                painter = painterResource(id = icon),
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                text = "내 ${what}",
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "확인하러 가기>",
                fontWeight = FontWeight.SemiBold
            )
        }
}

@Preview
@Composable
fun PreProfikeButton(){
    ProfileButton(what = "칭호", icon = R.drawable.my_words, onClick = {})
}