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
import androidx.compose.material.ModalBottomSheetLayout
import androidx.compose.material.ModalBottomSheetValue
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Like(
    onClick: () -> Unit,
    library: String
){
    var sheetVisible by remember { mutableStateOf(false) }
//    val bottomSheetState = rememberModalBottomSheetState(ModalBottomSheetValue.Hidden)

    Column (
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                        .clickable{ onClick }// 텍스트가 가능한 모든 여유 공간을 차지하게 함
                )
                IconButton(
                    onClick = {
                        sheetVisible = true
//                        bottomSheetState.show()
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
}


@Preview
@Composable
fun PreLike(){
    Like(
        onClick = {},
        library = "자주 까먹는 단어 모음"
    )
}