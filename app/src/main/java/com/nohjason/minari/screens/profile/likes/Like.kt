package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun Like(
    onClick: () -> Unit,
    library: String
){
    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = library,
                maxLines = 1, // 텍스트가 한 줄로 제한됨
                overflow = TextOverflow.Ellipsis, // 텍스트가 넘칠 때 ...으로 처리
                modifier = Modifier
                    .weight(0.4f)
                    .clickable{ onClick }// 텍스트가 가능한 모든 여유 공간을 차지하게 함
            )
            IconButton(
                onClick = {

                }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_more),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }

        }
        Divider(
            color = Color(0xFFECEFFB),
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
//                    .padding(top = 14.dp, bottom = 14.dp),
        )
    }
}


@Preview
@Composable
fun PreLike(){
    Like(
        onClick = {},
        library = "자주 까먹는 단어 모음"
    )
}