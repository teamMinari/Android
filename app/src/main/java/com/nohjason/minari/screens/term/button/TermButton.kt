package com.nohjason.minari.screens.term.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun TermButton(
    title: String,
    viewModel: TermButtonViewModel,
    initialState: Boolean = true
) {
    val stateMap by viewModel.stateMap.collectAsState()
    val state = stateMap[title] ?: true

    LaunchedEffect(initialState) {
        if (!stateMap.containsKey(title)) {
            viewModel.setState(title, initialState)
        }
    }

    Box(
        modifier = Modifier
            .clip(CircleShape)
            .background(if (state) Color.White else MinariBlue)
            .padding(horizontal = 15.dp, vertical = 5.dp)
            .clickable { viewModel.toggleState(title) }
    ) {
        MinariText(text = title, size = 15, color = if (state) Color.LightGray else Color.White)
    }
}

@Preview
@Composable
fun Test() {
    TermButton(1, title = "전체")
}