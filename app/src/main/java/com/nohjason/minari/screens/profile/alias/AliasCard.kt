package com.nohjason.minari.screens.profile.alias

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.WebLink

@Composable
fun AliasCard(
    progress: Float,
    level: Int,
    exp: Int,
    myLevel: Int
){
    val webLink = WebLink()
    val getWebLink = webLink.getTitleAndUrlForLevel(level)
//    AsyncImage(model = getWebLink.url, contentDescription = null)
    Box(
        modifier = Modifier
            .background(Color.White)
    ){
        Row (
            modifier = Modifier.padding(),
            horizontalArrangement = Arrangement.Center
        ){
            Icon(painter = painterResource(id = R.drawable.default_profile), contentDescription = null)
            Column {
                Row (
                    horizontalArrangement = Arrangement.Center
                ){
                    Text(
                        text = "${level}Lv",
                        color = Color(0xFF363CD5),
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 13.sp
                    )
                    Text(
                        text = getWebLink.title,
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                if (myLevel < level){
                    Text(
                        text = "획득",
                        color = Color(0xFF363CD5),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                } else{
                    Box(
                        modifier = Modifier
                            .weight(1f)
                            .height(20.dp) // 이 높이와 일치하게 프로그레스 바의 높이를 설정
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE0E7FF)), // 배경색 설정
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator(
                            progress = progress,
                            color = Color(0xFF585EEA), // 프로그레스 바 색상
                            backgroundColor = Color(0xFFE0E7FF), // 프로그레스 배경색
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
                        )

                        androidx.compose.material3.Text(
                            text = "${exp}xp",
                            color = Color.White,
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }
        }

    }
}

@Preview(showBackground = true)
@Composable
fun PreAliasCard() {
    AliasCard(
        progress = 0.7f,  // 70%의 진행도를 예시로 설정
        level = 3,        // 레벨 3을 예시로 설정
        exp = 700,        // 경험치 700을 예시로 설정
        myLevel = 2       // 사용자의 현재 레벨을 2로 설정
    )
}