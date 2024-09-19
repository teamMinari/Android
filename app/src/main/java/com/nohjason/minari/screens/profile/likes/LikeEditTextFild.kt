package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun LikeEditTextFild(
    value: String,
    onValueChange: (String) -> Unit, // 변경된 값을 처리하는 람다 추가
    modifier: Modifier = Modifier,
    visualTransformation: VisualTransformation = VisualTransformation.None,
    isPassword: Boolean = false
){
    Row(
        modifier = modifier
            .padding(16.dp)

    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            visualTransformation = if (isPassword) PasswordVisualTransformation() else visualTransformation,
            modifier = Modifier
                .padding(16.dp) // 텍스트 필드의 안쪽 여백
                .width(260.dp)
        ){
            Row {
                Icon(painter = painterResource(id = R.drawable.ic_edit), contentDescription = null)
                Column (
                    modifier = Modifier.padding(start = 10.dp)
                ){
                    if (value.isEmpty()) {
                        Text(
                            text = "입력해주세요",
                            color = Color(0xFFD7D8E0),
                            modifier = Modifier
                                .padding(bottom = 10.dp)
                        )
                    } else {
                        Text(
                            text = value,
                            color = Color.Black, // 입력된 텍스트가 있을 때 검은색
                            modifier = Modifier.padding(bottom = 10.dp)
                        )
                    }
                    Divider(
                        color = Color(0xFFECEFFB), modifier = Modifier
                            .fillMaxWidth()
                            .height(1.dp)
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun PreText(){
    var text by remember { mutableStateOf("") }
    LikeEditTextFild(
        value = text,
        onValueChange = { newText -> text = newText },
        isPassword = false // 비밀번호 입력 시 true로 설정
    )
}