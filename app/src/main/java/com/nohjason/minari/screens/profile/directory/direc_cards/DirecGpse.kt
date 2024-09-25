package com.nohjason.minari.screens.profile.directory.direc_cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.directory.DirecGpse

@Composable
fun DirecGpse(
    data: DirecGpse
){
    val isBookmarked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .width(260.dp) // Row가 부모의 가로 전체를 차지하도록 설정
    ){
        Text(
            text = data.gpseName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "(${data.gpseTime}분)",
            modifier = Modifier
                .weight(1f),
            color = Color(0xFFB2B2B2)
        )
        Icon(
            painter = painterResource(
                id = if (isBookmarked.value) R.drawable.ic_book_mark_deactivate
                else R.drawable.minari_book_mark
            ),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable {
                    isBookmarked.value = !isBookmarked.value
                    //서버에 아이디 전송
                }
        )
    }
}