package com.nohjason.cheongfordo.screens.ui.text

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.screens.home.HomeScreen
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.MinariWhite

@Composable
fun MinariTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    onClick: () -> Unit,
    hint: String = "검색어 입력",
    fontSize: TextUnit = 15.sp
) {
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    var isFocused by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically, // Row의 콘텐츠를 수직 중앙 정렬
        modifier = Modifier
            .clip(CircleShape)
            .background(MinariWhite)
            .padding(start = 20.dp, end = 10.dp, top = 7.dp, bottom = 7.dp)
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = modifier
                .weight(1f)
                .focusRequester(focusRequester)
                .align(Alignment.CenterVertically) // 커서를 수직 중앙에 정렬
                .onFocusChanged { focusState ->
                    isFocused = focusState.isFocused // 포커스 상태 저장
                },
            textStyle = TextStyle(
                fontSize = fontSize,
                color = Color.Black
            ),
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Search // 엔터를 Search로 처리
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    keyboardController?.hide() // 키보드를 숨기고
                    onClick() // 엔터 키 액션 처리
                }
            ),
            maxLines = 1, // 한 줄로 제한
            singleLine = true, // 줄바꿈을 막기 위해 설정
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier.fillMaxWidth()
                ) {
                    if (value.isEmpty()) {
                        Text(
                            text = hint,
                            color = Color(0xFF8C8C8C),
                            fontSize = fontSize,
                            modifier = Modifier.align(Alignment.CenterStart), // 힌트 텍스트도 중앙 정렬
                        )
                    }
                    innerTextField() // 텍스트 필드 표시
                }
            },
        )
        Icon(
            imageVector = Icons.Default.Search,
            contentDescription = null,
            modifier = Modifier
                .size(30.dp)
                .clickable { onClick() }, // 아이콘의 크기를 고정
            tint = MinariBlue
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    HomeScreen(navController = rememberNavController())
}