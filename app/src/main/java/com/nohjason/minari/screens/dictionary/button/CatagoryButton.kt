package com.nohjason.minari.screens.dictionary.button

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CatagoryButton() {
    LazyRow(
        modifier = Modifier.padding(5.dp)
    ) {
        items(10) {
            DictionaryButton()
            Spacer(modifier = Modifier.width(10.dp))
        }
    }
}