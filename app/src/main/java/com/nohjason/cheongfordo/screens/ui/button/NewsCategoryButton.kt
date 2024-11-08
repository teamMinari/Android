@file:Suppress("UNUSED_EXPRESSION")

package com.nohjason.cheongfordo.screens.ui.button

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.nohjason.cheongfordo.screens.ui.text.MinariText

@Composable
fun NewsCategoryButton(
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(75.dp)
            .background(Color.White)
            .clip(RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxSize()
        ) {
            Icon(
                Icons.Default.Home,
                contentDescription = null,
                modifier = Modifier
                    .size(30.dp)
                    .background(Color.White)
            )
            MinariText(
                text = "쟂",
                size = 12,
                color = Color.Gray
            )
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun TestNewsCategory() {
//    NewsCategoryButton()
//}