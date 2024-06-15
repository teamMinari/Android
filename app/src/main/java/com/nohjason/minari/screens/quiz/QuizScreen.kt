package com.nohjason.minari.screens.quiz

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariLightGray

@Composable
fun QuizScreen() {
    Column {
        TopAppBar(
            title = {  },
            backgroundColor =  Color.White,
            navigationIcon = {
                IconButton(onClick = {
//                    navController.popBackStack()
                }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                }
            }
        )
        InProduction(title = "아직 제작 중인 서비스 입니다", value = "서비스 이용에 불편을 드려서 죄송합니다")
    }
}