package com.nohjason.minari.screens.dictionary.card

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun LikeWord() {
    LazyColumn {
        items(20) {
            WordCard()
            Spacer(modifier = Modifier.height(10.dp))
        }
    }
}