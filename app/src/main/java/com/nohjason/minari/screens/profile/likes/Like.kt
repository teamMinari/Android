package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Like(
    onClick: () -> Unit,
    library: String
) {
    var showBottomSheet by remember { mutableStateOf(false) }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // 전체 화면을 채우면서 패딩을 줄 수 있습니다
        ) {
            Column(
                modifier = Modifier
                    .background(Color.White, shape = RoundedCornerShape(16.dp))
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                Row(
                    modifier = Modifier
                        .clickable { /* 삭제 처리 */ }
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_delete), contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "삭제하기",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
                }
                Row(
                    modifier = Modifier
                        .clickable { /* 수정 처리 */ }
                        .padding(vertical = 8.dp)
                        .fillMaxWidth()
                ) {
                    Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = null)
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = "수정하기",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium
                    )
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
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            modifier = Modifier
                .weight(0.4f)
                .clickable { onClick() } // 텍스트가 가능한 모든 여유 공간을 차지하게 함
        )
        IconButton(
            onClick = {
                showBottomSheet = true
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
fun PreLike() {
    Like(onClick = { /*TODO*/ }, library = "Sample Library")
}
