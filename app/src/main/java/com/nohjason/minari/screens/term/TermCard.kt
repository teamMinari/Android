package com.nohjason.minari.screens.term

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun TermCard(
    title: String,
    value: String,
    starCount: Int,
    dummyTermSimilarButton: List<DummyTermSimilarButton>
) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp, vertical = 5.dp)
    ) {

        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            MinariText(text = title, size = 20)

            Spacer(modifier = Modifier.width(5.dp))

            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(3.dp)
            ) {
                items(starCount) {
                    Icon(
                        painter = painterResource(R.drawable.star),
                        contentDescription = "star",
                        tint = Color.Unspecified,
                        modifier = Modifier.size(15.dp)
                    )
                }
            }
        }


        Spacer(modifier = Modifier.height(10.dp))

        MinariText(
            text = value,
            size = 11,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            color = Color.Gray
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
            items(dummyTermSimilarButton) { item ->
                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(1.dp, Color.Black, shape = CircleShape) // CircleShape을 명시적으로 설정
                        .padding(vertical = 3.dp, horizontal = 10.dp)
                        .clickable { },
                ) {
                    MinariText(text = item.title, size = 10)
                }
            }
        }
    }
}