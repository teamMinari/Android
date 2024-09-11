package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.nohjason.minari.R

@Composable
fun Like(
    onClick: () -> Unit,
    library: String
) {
    var showPopup by remember { mutableStateOf(false) }

    if (showPopup) {
        Popup(
            onDismissRequest = { showPopup = false },
            properties = PopupProperties(focusable = true)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(Color.White)
                    .clip(RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp))
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            ) {
                Column {
                    Text(text = "삭제하기", modifier = Modifier.clickable { /* 삭제 처리 */ })
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = "수정하기", modifier = Modifier.clickable { /* 수정 처리 */ })
                }
            }
        }
    }


    Row(
        modifier = Modifier
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = library,
            maxLines = 1, // 텍스트가 한 줄로 제한됨
            overflow = TextOverflow.Ellipsis, // 텍스트가 넘칠 때 ...으로 처리
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(0.4f)
                .clickable { onClick() } // 텍스트가 가능한 모든 여유 공간을 차지하게 함
        )
        IconButton(
            onClick = {
                 showPopup = true
            }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreLike(){
    Like(onClick = { /*TODO*/ }, library = "")
}