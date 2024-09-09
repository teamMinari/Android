package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun Like(
    onClick: () -> Unit,
    library: String
){
    Row (
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ){
        Text(text = library)
        IconButton(
            onClick = { /*TODO*/ }
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_more),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.padding(start = 168.dp)
            )
        }
    }
}

@Preview
@Composable
fun PreLike(){
    Like(
        onClick = {},
        library = "자주 까먹는 단어 모음"
    )
}