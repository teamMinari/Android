package com.nohjason.minari.screens.login

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun LoginTextField(
    value: String,
    icon_name: String,
    text: String,
    onValueChange: (String) -> Unit,
    visibility: Boolean,
) {
    var isFocused by remember { mutableStateOf(false) }
    var isVisiblePassword by remember { mutableStateOf(visibility) }

    val imageVector = when (icon_name) {
        "이메일" -> R.drawable.ic_email
        "비밀번호" -> R.drawable.ic_password
        "비밀번호 재확인" -> R.drawable.ic_password
        "아이디" -> R.drawable.ic_user
        // 필요한 다른 아이콘들에 대해서도 추가 가능
        else -> R.drawable.ic_noun // 기본값으로 설정할 아이콘 지정
    }

    Column (
        modifier = Modifier.width(320.dp)
    ){
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Icon(
                painterResource(id = imageVector),
                contentDescription = "",
                modifier = Modifier.size(20.dp),
                tint = if (isFocused) Color(0xFF000842) else Color(0xFFD7D8E0)
            )
            Spacer(modifier = Modifier.width(15.dp))
            BasicTextField(
                value = value,
                onValueChange = onValueChange,
                modifier = Modifier
                    .weight(1f)
                    .padding(top = 10.dp)
                    .onFocusChanged { focusState ->
                        isFocused = focusState.isFocused
                    },
                visualTransformation = if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
                decorationBox = { innerTextField ->
                    innerTextField()
                    if (value.isEmpty()) {
                        Text(text = text,
                            color = Color(0xFF000842),
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }

                }
            )
            Spacer(modifier = Modifier.weight(0.1f))
            if (icon_name == "비밀번호" || icon_name == "비밀번호 재확인") { // password인 경우에만 보이기/숨기기 아이콘 표시
                IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                    if (isVisiblePassword) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility),
                            contentDescription = "비밀번호 숨기기",
                            tint = Color.Unspecified
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_visibility_off),
                            contentDescription = "비밀번호 보이기",
                            tint = Color.Unspecified
                        )
                    }
                }
            }
        }
        Divider(
            color = if (isFocused) Color(0xFF000842) else Color(0xFFD7D8E0),
            modifier = Modifier
                .fillMaxWidth()
                .height(1.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun PreText() {
    var email by remember { mutableStateOf("") }
    LoginTextField(
        value = email,
        icon_name = "비밀번호",
        text = "비밀번호",
        onValueChange = { email = it },
        visibility = true,
    )
}
