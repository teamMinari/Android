package com.nohjason.minari.screens.profile

import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R

@Composable
fun Access_not(){
    Image(painter = painterResource(id = R.drawable.fix_programing_emoji), contentDescription = "fix programing emoji")
    Text(text = "아직 제작 중인 서비스\n" + "입니다.")
    Text(text = "서비스 이용에 불편을 드려서 죄송합니다. ")
}

@Preview(showBackground = true)
@Composable
fun Access_notScreenPreview() {
    val navController = rememberNavController()
    ProfileScreen(navController = navController)
}