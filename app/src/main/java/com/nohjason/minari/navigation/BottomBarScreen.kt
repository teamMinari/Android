package com.nohjason.minari.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ThumbUp
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomBarScreen(
    val rout: String,
    val title: String,
    val icon: ImageVector
) {
    object Login: BottomBarScreen(
        rout = "login",
        title = "Login",
        icon = Icons.Default.Lock
    )
    object Home: BottomBarScreen(
        rout = "home",
        title = "Home",
        icon = Icons.Default.Home
    )
    object Profile: BottomBarScreen(
        rout = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )
    object Dictionary: BottomBarScreen(
        rout = "dictionary",
        title = "Diction",
        icon = Icons.Default.AccountBox
    )
    object Quiz: BottomBarScreen(
        rout = "quiz",
        title = "Quiz",
        icon = Icons.Default.ThumbUp
    )
}