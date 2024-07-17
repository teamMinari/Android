package com.nohjason.minari.screens.quiz.udmin_ui

import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.nohjason.minari.screens.quiz.PointBox

@Composable
fun EditScreen(){
    PointBox()
    var search by remember { mutableStateOf("") }
    TextField(value = search, onValueChange = {search = it})

    lazy {
        //퀴즈 보여주기
    }
    
    Button(onClick = { /*플레이 UI로 이동하면 될 듯*/ }) {
        Text(text = "테스트하기")
    }
}

