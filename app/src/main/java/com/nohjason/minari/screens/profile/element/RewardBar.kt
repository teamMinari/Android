package com.nohjason.minari.screens.profile.element

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
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
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
fun RewardBar(
    progress: Float, // 0f to 1f
    xp: Int,
    level:Int
) {
    val webLink = WebLink()
    val getWebLink = webLink.getUrlForLevel(level)


    Box(
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight()
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
    ) {
        Column (
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 10.dp)
                .background(color = Color.White)
        ){
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_present), // 보상 아이콘 리소스
                    contentDescription = null,
                    tint = Color(0xFF585EEA), // 파란색
                    modifier = Modifier.size(24.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))

                // 텍스트
                Text(
                    text = "보상",
                    color = Color.Black,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                //아이콘
                // Progress Bar
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

                    Text(
                        text = "${xp}xp",
                        color = Color.White,
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier.padding(horizontal = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.width(16.dp))

                // 우측 아이콘 (보상 이미지)
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(CircleShape)
                        .background(Color(0xFFE0E7FF)),
                    contentAlignment = Alignment.Center
                ) {

                    AsyncImage(
                        model = getWebLink,
                        contentDescription =null
                    )
                }
            }
        }
    }
}


//@Preview(showBackground = true)
//@Composable
//fun RewardBarPreview() {
//    RewardBar(
//        progress = 0.7f, // 70% 채워진 프로그레스 바
//        rewardIcon = "ㅇㅇㅇ", // 보상 아이콘 리소스 (실제 프로젝트에서 리소스 경로 맞춰주세요)
//        xp = 35 // 35 XP
//    )
//}