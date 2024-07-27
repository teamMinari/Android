package com.nohjason.minari.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGray

@Composable
fun LoginTextField(
    modifier: Modifier,
    value: String,
    icon_name: String,
    text: String,
    onValueChange: (String) -> Unit,
    visibility: Boolean,
    onClick: () -> Unit,
) {
    val imageVector = when (icon_name) {
        "이메일" -> R.drawable.email
        "비밀번호" -> R.drawable.password
        "비밀번호 재확인" -> R.drawable.password
        "아이디" -> R.drawable.user
        // 필요한 다른 아이콘들에 대해서도 추가 가능
        else -> R.drawable.o_not // 기본값으로 설정할 아이콘 지정
    }
    var isVisiblePassword by remember { mutableStateOf(visibility) } // visibility 초기화
    Column (
        modifier = Modifier.width(320.dp)
    ){
//        Text(text = icon_name, color = Color(0xFF999999), fontWeight = FontWeight.Bold)
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painterResource(id = imageVector),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = MinariBlue,
            )
            Spacer(modifier = Modifier.width(15.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(0.5f)
                    .padding(top = 10.dp), // Fill remaining space
                visualTransformation = if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    Box {
                        innerTextField()
                        if (value.isEmpty()) {
                            Text(text = text, color = Color(0xFF000842), modifier = Modifier.padding(bottom =10.dp))
                        }
                    }
                }
            )
            Spacer(modifier = Modifier.weight(0.1f))
            if (icon_name == "비밀번호" || icon_name == "비밀번호 재확인") { // password인 경우에만 보이기/숨기기 아이콘 표시
                IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                    if (isVisiblePassword) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "비밀번호 숨기기"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = "비밀번호 보이기"
                        )
                    }
                }
            }
        }
        Divider(
            color = Color(0xFF999999),
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun PreText() {
//    var email by remember { mutableStateOf("") }
//    LoginTextField(
//        modifier = Modifier,
//        value = email,
//        icon_name = "비밀번호",
//        text = "비밀번호",
//        onValueChange = { email = it },
//        visibility = true,
//        onClick = {}
//    )
//}
