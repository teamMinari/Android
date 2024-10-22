package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.common.io.Resources
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen.Home.icon

@Composable
fun QuizPopup(
    onDismissRequest: () -> Unit,
    onConfirmation: () -> Unit,
    dialogTitle: String,
    dialogText: String,
    icon: Painter,
) {
    AlertDialog(
        containerColor = Color.White,
        icon = {
            Icon(
                icon,
                contentDescription = "Example Icon",
                tint = Color.Unspecified
            )
        },
        title = {
            Text(
                text = dialogTitle,
                fontWeight = FontWeight.SemiBold
            )
        },
        text = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = dialogText,
                    color = Color(0xFF0C21C1)
                )
            }
        },
        onDismissRequest = {
            onDismissRequest()
        },


        confirmButton = {
            Row {
                TextButton(
                    onClick = {
                        onConfirmation()
                    }
                ) {
                    Text(
                        "확인",
                        color = Color(0xFF0C21C1),
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(25.dp))
            }
        },
        dismissButton = {
            Row {
                TextButton(
                    onClick = {
                        onDismissRequest()
                    },
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    Text(
                        "취소",
                        color = Color.Black,
                        fontWeight = FontWeight.SemiBold
                    )
                }
                Spacer(modifier = Modifier.width(100.dp)) // 버튼 사이에 여백 추가
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
fun QuizPopupPreview() {
    // 프리뷰에서 사용할 디폴트 값을 설정합니다.
    QuizPopup(
        onDismissRequest = { /* Do nothing for preview */ }, //닫을 때 호출
        onConfirmation = { /* Do nothing for preview */ },
        dialogTitle = "뒤로 돌아가기",
        dialogText = "정말로 퀴즈를 종료시겠습니까?",
        icon = painterResource(id = R.drawable.ic_x)
    )
}