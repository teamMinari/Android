package com.nohjason.minari.screens.home.words.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun RecommedWordsCard(
    title: String,
    starCount: Int
) {
    Box(
        modifier = Modifier
            .height(35.dp)
            .width(150.dp)
            .clip(RoundedCornerShape(5.dp))
            .background(Color.LightGray)
            .padding(5.dp)
    ) {
        Column {
            MinariText(text = title, size = 10)
            LazyRow {
                items(starCount) {
                    Icon(
                        painter = painterResource(R.drawable.star),
                        contentDescription = null,
                        modifier = Modifier.size(7.dp),
                        tint = Color.Unspecified
                    )
                }
            }
        }

    }
}