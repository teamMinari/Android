package com.nohjason.minari.screens.login.screen.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_medium

@Composable
fun SelfSignUpLastScreen(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.weight(0.15f))
        Text(
            text = "환영합니다🔥",
            fontFamily = pretendard_bold,
            fontSize = 30.sp
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Text(
            text = "이제부터 청For도와 함께\n경제 용어를 배워가요.",
            color = MinariBlue,
            fontFamily = pretendard_medium,
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
        Spacer(modifier = Modifier.weight(0.1f))
        Icon(
            painter = painterResource(R.drawable.grape),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.size(130.dp)
        )
        Spacer(modifier = Modifier.weight(0.5f))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MinariBlue)
                .clickable {
                    navController.navigate(Screen.Home.rout) {

                    }
                }
        ) {
            androidx.compose.material.Text(
                text = "시작하기",
                color = Color.White,
                fontFamily = pretendard_bold,
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center)
            )
        }
        Spacer(modifier = Modifier.weight(0.4f))
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    SelfSignUpLastScreen(rememberNavController())
}