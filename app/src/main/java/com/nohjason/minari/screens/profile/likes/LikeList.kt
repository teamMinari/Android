package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ButtonColors
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.likes.Dummy.dummyLikeList

@Composable
fun LikeList(
    likeList: LikeList
) {
    val nameList = likeList.name
    Box(
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight()
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp)) // Box에 clip 적용
    ) {
        Column(
            modifier = Modifier.background(color = Color.White),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp) // 상단 패딩 추가
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_list),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(text = "저장 목록")
            }

            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
            ) {
                items(nameList) { data ->
                    Like(onClick = { /*TODO*/ }, library = data)
                }
            }

            Spacer(modifier = Modifier.height(16.dp)) // Spacer 추가하여 버튼과 리스트 사이에 공간 추가

            Button(
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(15.dp))
                    .padding(bottom = 14.dp), // 버튼에 clip 적용
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF585EEA)),
                onClick = { /*TODO*/ }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp) // 아이콘 크기 조정
                )
            }
        }
    }
}


@Preview
@Composable
fun PreLikeList() {
    LikeList(likeList = dummyLikeList)
}
