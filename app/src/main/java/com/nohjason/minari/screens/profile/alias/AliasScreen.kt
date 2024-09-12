package com.nohjason.minari.screens.profile.alias

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.quiz.quiz_end.TitleBar

@Composable
fun AliasScreen(){
    Column (
        modifier = Modifier.fillMaxSize()
    ){
        TitleBar(
            title = "칭호보기",
            imgResId =  R.drawable.ic_target
        )

        Box(
            modifier = Modifier
                .width(337.dp)
                .height(106.dp)
                .clip(RoundedCornerShape(20.dp))
                .back
        )
    }
}

@Preview
@Composable
fun PreAliaScreen(){
    AliasScreen()
}