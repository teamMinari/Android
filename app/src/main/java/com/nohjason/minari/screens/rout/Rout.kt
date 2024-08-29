package com.nohjason.minari.screens.rout

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun Rout() {
    LazyColumn(
        modifier = Modifier.fillMaxSize()
    ) {
        item {
            Column {
                Row {
                    Icon(
                        painter = painterResource(id = R.drawable.rout),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier.size(50.dp)
                    )
                    Text(text = "튜토리얼")
                }
                Text(text = "자신에게 맞는 포도송이를 획득할 수 있어요.")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Rout()
}