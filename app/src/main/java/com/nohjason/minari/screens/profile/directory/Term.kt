package com.nohjason.minari.screens.profile.directory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun Term(){
    val difficulty = "LV_1"
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
    ){
        Column (
            modifier = Modifier
                .background(color = Color.White)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(text = "등기의 시작")
                if (difficulty == "LV_1"){
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp)
                    )
                } else if (difficulty == "LV_2"){
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(10.dp)
                        )
                        Icon(
                            painter = painterResource(id = R.drawable.star),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier
                                .padding(start = 3.dp)
                                .size(10.dp)
                        )
                } else{
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp)
                    )
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp)
                    )
                }
                Icon(
                    painter = painterResource(id = R.drawable.minari_book_mark),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier

                        .clickable { //tur, fal구분, 데이터 모델 만드ㄹ어서 만약 변경 사항 있으면 patch시켜야함
                    }
                )
            }
            Text(
                text = "가구의 소",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }

    }

}

@Preview
@Composable
fun PreTerm(){
    Term()
}