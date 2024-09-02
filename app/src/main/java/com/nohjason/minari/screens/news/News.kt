package com.nohjason.minari.screens.news

import androidx.activity.compose.BackHandler
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.nohjason.minari.navigation.bottombar.Screen

@Composable
fun News(navController: NavController) {
    BackHandler(onBack = {
        navController.popBackStack(Screen.Home.rout, inclusive = false)
    })
    Text(text = "wow")
}