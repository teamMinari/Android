package com.nohjason.minari.screens.ui.news

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun NewsCard() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .clickable {  }
    ) {
        Row {
            Image(
                painter = painterResource(R.drawable.ic_launcher_background),
                contentDescription = null,
                modifier = Modifier
                    .size(100.dp, 70.dp)
                    .border(1.dp, Color.Black)
            )

            Spacer(modifier = Modifier.width(10.dp))

            Column {
                MinariText(text = "제목", size = 13)
                MinariText(text = "간단한 내용", size = 7)
            }
        }
    }
}