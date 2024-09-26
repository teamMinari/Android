package com.nohjason.minari.screens.profile.alias_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nohjason.minari.screens.profile.profile_data.WebLinkData

@Composable
fun AliasMainCard(
    level: Int,
    exp: Int,
){
    val webLink = WebLinkData()
    val getWebLink = webLink.getTitleAndUrlForLevel(level)
    val progress = (exp / 100.toFloat()).coerceIn(0f, 1f)
    Box(
        modifier = Modifier
            .background(Color.White)
            .width(280.dp)
            .height(65.dp),
    ){
        Row (
            modifier = Modifier.padding(start = 32.dp),
        ){
            AsyncImage(
                modifier = Modifier
                    .size(65.dp),
                model = getWebLink.url,
                contentDescription = null)
            Column (
                modifier = Modifier.padding(start = 12.dp)
            ){
                Row {
                    Text(
                        text = "'${getWebLink.title}'획득하기",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )
                }
                Box(
                    modifier = Modifier
                        .width(160.dp)
                        .height(20.dp) // 이 높이와 일치하게 프로그레스 바의 높이를 설정
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFE0E7FF)), // 배경색 설정
                    contentAlignment = Alignment.Center
                ) {
                    LinearProgressIndicator(
                        progress = progress,
                        color = Color(0xFF5DC067), // 프로그레스 바 색상
                        backgroundColor = Color(0xFFDADAFD), // 프로그레스 배경색
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(20.dp)
                    )

                    Text(
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

@Preview
@Composable
fun PreAliaMainCard(){
    AliasMainCard(
        level = 3,        // 레벨 3을 예시로 설정
        exp = 50,        // 경험치 700을 예시로 설정
    )
}