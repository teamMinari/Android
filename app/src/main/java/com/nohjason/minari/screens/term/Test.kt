package com.nohjason.minari.screens.term

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.screens.ui.text.MinariText

// home스크린에서 받은 글자를 표시하는 테스트 화면
@Composable
fun Test(
    title: String
) {

    val word =
        if (title.isNotEmpty()) allWords.find { it.title == title } else null

    Column {
        if (word != null) {
            MinariText(text = word.title)
            MinariText(text = word.value)
        } else {
            MinariText(text = "정보 없음")
        }
    }

}