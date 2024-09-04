package com.nohjason.minari.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
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
fun ProfileMainScreen(
    profileData: ProfileData
){
    Box(modifier = Modifier){
        Icon(painter = painterResource(id = R.drawable.star), contentDescription = null)
    }
    //프로필 정보
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA))
    ){
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = null
        )
        Row {
            Text(text = profileData.id)
            Text(text = profileData.title)
        }
        Text(text = profileData.email)
        Text(text = "관심주제: 금융, 글로벌 경제, 채권")


        //이동 버튼
        Row {
            ProfileButton(
                what = "칭호",
                icon = R.drawable.my_words,
                onClick = {
                }
            )
            ProfileButton(
                what = "괸심사",
                icon = R.drawable.my_words,
                onClick = {
                }
            )
        }


        //칭호 박스
        Box(
            modifier = Modifier
                .width(320.dp)
                .height(85.dp)
        ){
            Row {
                Icon(
                    painter = painterResource(id = R.drawable.star),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Text(text = "보상")
            }
            //그래프 필요
        }


        //저장목록
        //디렉토리리스트뷰(저장버튼)

    }
}

@Preview(showBackground = true)
@Composable
fun PreProfileMain(){
    val testData = ProfileData(
            idx = 0,
    id = "wlals",
    email = "wlals6691@gmail.com",
    vocaBook = "",
    point = 50,
    exp =  30,
    authority = "",
    title = "",
    level = 2,
    totalExp = 1566,
    )
    ProfileMainScreen(profileData = testData)
}