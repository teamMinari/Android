package com.nohjason.minari.navigation.bottombar

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
    data object Login : BottomBarScreen(
        rout = "login",
        title = "Login",
        icon = Icons.Default.Lock
    )

    data object Home : BottomBarScreen(
        rout = "home",
        title = "Home",
        icon = Icons.Default.Home
    )

    data object Profile : BottomBarScreen(
        rout = "profile",
        title = "Profile",
        icon = Icons.Default.Person
    )

    data object Dictionary : BottomBarScreen(
        rout = "dictionary",
        title = "Diction",
        icon = Icons.Default.AccountBox
    )

    data object Quiz : BottomBarScreen(
        rout = "quiz",
        title = "Quiz",
        icon = Icons.Default.ThumbUp
    )
}