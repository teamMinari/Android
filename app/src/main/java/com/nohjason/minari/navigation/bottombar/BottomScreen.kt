package com.nohjason.minari.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.minari.R

sealed class BottomScreen(
    val rout: String,
    val title: String,
    val icon: @Composable () -> ImageVector
) {
    data object Home : BottomScreen(
        rout = "home",
        title = "Home",
        icon = { Icons.Default.Home }
    )

    data object Profile : BottomScreen(
        rout = "profile",
        title = "Profile",
        icon = { Icons.Default.Person }
    )

    data object News : BottomScreen(
        rout = "news",
        title = "News",
        icon = { ImageVector.vectorResource(id = R.drawable.news) }
    )

    data object Quiz : BottomScreen(
        rout = "quiz",
        title = "Quiz",
        icon = { ImageVector.vectorResource(id = R.drawable.quiz) }
    )

    data object Rout : BottomScreen(
        rout = "rout",
        title = "Rout",
        icon = { ImageVector.vectorResource(id = R.drawable.rout) }
    )
}