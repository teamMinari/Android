package com.nohjason.minari.screens.quiz.udmin_ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Button
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import com.nohjason.minari.R

@Composable
fun QtCreate(){
    Column {
        var qt by remember { mutableStateOf("") }
        OutlinedTextField(value = qt, onValueChange = {qt = it})

        Row {
            Button(onClick = { /*true값 Data넣을 수 있게*/ }) {
                Image(painterResource(id = R.drawable.x), contentDescription = null)
            }

            Button(onClick = { /*true값 Data넣을 수 있게*/ }) {
                Image(painterResource(id = R.drawable.o), contentDescription = null)
            }
        }

        var qtCmt by remember { mutableStateOf("") }
        OutlinedTextField(value = qtCmt, onValueChange = {qtCmt = it})

        androidx.compose.material3.Button(onClick = { /*데이터 전송 -> 여부확인*/ }) {
            Text(text = "완료")
        }
    }
}
