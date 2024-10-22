package com.nohjason.minari.screens.profile.alias_screen

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
import com.nohjason.minari.screens.profile.profile_data.WebLinkData
import com.nohjason.minari.ui.theme.pretendard_semibold

@Composable
fun AliasCard(
    level: Int,
    exp: Int,
    myLevel: Int
){
    val webLink = WebLinkData()
    val getWebLink = webLink.getTitleAndUrlForLevel(level)
    val progress = (exp / 100.toFloat()).coerceIn(0f, 1f)

    Box(
        modifier = Modifier
            .background(Color.White)
            .width(280.dp)
            .height(65.dp)
    ){
        Row{
            //내 레벨 >= 조건 레벨 / 칭호 이미지 보여주기 여부
            if (myLevel >= level) {
                AsyncImage(
                    modifier = Modifier
                        .size(65.dp),
                    model = getWebLink.url,
                    contentDescription = null
                )
            }
            Column (
                modifier = Modifier.padding(start = 12.dp)
            ){

                //내 레벨 >= 조건 레벨 / 자기 이름 까기
                if (myLevel >= level) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = "${level}Lv",
                            color = Color(0xFF363CD5),
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = getWebLink.title,
                            fontWeight = FontWeight.SemiBold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                }

                //내 레벨 > 조건 레벨 => 획득
                if (myLevel > level){
                    Text(
                        text = "획득",
                        color = Color(0xFF363CD5),
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                    )
                    //내 레벨 = 조건 레벨 => 그래프
                } else if (myLevel == level){
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
                            color = Color(0xFF407C46),
                            fontSize = 12.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier.padding(horizontal = 8.dp)
                        )
                    }
                }
            }

            //내 레벨 < 조건 레벨 => 몰?루
            if(myLevel < level){
                Box(
                    modifier = Modifier
                            .size(65.dp)
                    ,
                    contentAlignment = Alignment.Center
                ) {

                    Icon(
                        painter = painterResource(id = R.drawable.ic_mystery_box),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                }

                Column(
                    modifier = Modifier
                        .padding(start = 12.dp)
                        .fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(top = 4.dp)
                    ) {
                        Text(
                            text = "${level}Lv",
                            color = Color(0xFF363CD5),
                            fontFamily = pretendard_semibold,
                            fontSize = 13.sp
                        )
                        Text(
                            text = "???",
                            fontFamily = pretendard_semibold,
                            fontSize = 15.sp,
                            modifier = Modifier.padding(start = 4.dp)
                        )
                    }
                    Spacer(modifier = Modifier.height(8.dp))
                    Box(
                        modifier = Modifier
                            .width(160.dp)
                            .height(20.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color(0xFFE0E7FF)),
                        contentAlignment = Alignment.Center
                    ) {
                        LinearProgressIndicator(
                            progress = 0f,
                            color = Color(0xFF5DC067),
                            backgroundColor = Color(0xFFDADAFD),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(20.dp)
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
        level = 3,        // 레벨 3을 예시로 설정
        exp = 50,        // 경험치 700을 예시로 설정
        myLevel = 2     // 사용자의 현재 레벨을 2로 설정
    )
}