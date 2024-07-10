package com.nohjason.minari.screens.term.button

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun TermButton(
    title: String,
    category: MutableState<String>,
    viewModel: TermButtonViewModel = viewModel(),
) {
    val selectedButton by viewModel.selectedButton.collectAsState()

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .clickable(onClick = {
                viewModel.selectButton(title)
                category.value = title
                Log.d("TAG", "TermButton: $title")
            })
            .background(if (selectedButton == title) MinariBlue else Color.White)
            .padding(horizontal = 15.dp, vertical = 5.dp)
    ) {
        MinariText(text = title, size = 15, color = if (selectedButton == title) Color.White else Color.LightGray)
    }
}