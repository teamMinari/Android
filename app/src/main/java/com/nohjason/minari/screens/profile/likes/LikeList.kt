package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun LikeList(){
    Box (
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight()
    ){
        Row{
            Icon(
                painter = painterResource(id = R.drawable.ic_list),
                contentDescription = null
            )
            Text(text = "저장 목록")
        }
        LazyColumn {
            items() { shareData ->
                ShareCard(
                    shareData = shareData,
                    onClick = {
                        navController.navigate("Recipe/${shareData.titl}")
                    }
                )
            }
        }
        Button(
            modifier = Modifier
                .width(198.dp)
                .height(30.dp),
            onClick = { /*TODO*/ }
        ) {
            Icon(painter = painterResource(id = R.drawable.ic_plus), contentDescription = null)

        }
    }
}

@Preview
@Composable
fun PreLikes(){

}