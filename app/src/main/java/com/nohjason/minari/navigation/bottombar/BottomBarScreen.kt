package com.nohjason.minari.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.minari.R

sealed class BottomBarScreen(
    val rout: String,
    val title: String,
    val icon: @Composable () -> ImageVector
) {
    data object Login : BottomBarScreen(
        rout = "login",
        title = "Login",
        icon = { Icons.Default.Lock }
    )

    data object Home : BottomBarScreen(
        rout = "home",
        title = "Home",
        icon = { Icons.Default.Home }
    )

    data object Profile : BottomBarScreen(
        rout = "profile",
        title = "Profile",
        icon = { Icons.Default.Person }
    )

    data object Term : BottomBarScreen(
        rout = "term",
        title = "Term",
        icon = { ImageVector.vectorResource(id = R.drawable.term) }
    )

    data object Quiz : BottomBarScreen(
        rout = "quiz",
        title = "Quiz",
        icon = { ImageVector.vectorResource(id = R.drawable.quiz) }
    )
}