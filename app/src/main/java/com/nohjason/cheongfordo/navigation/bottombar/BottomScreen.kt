package com.nohjason.cheongfordo.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.cheongfordo.R

sealed class BottomScreen(
    val rout: String,
    val icon: @Composable () -> ImageVector,
    val label: String
) {
    data object Home : BottomScreen(
        rout = "home",
        icon = { Icons.Default.Home },
        label = "홈"
    )

    data object Profile : BottomScreen(
        rout = "profile",
        icon = { Icons.Default.Person },
        label = "프로필"
    )

    data object News : BottomScreen(
        rout = "news",
        icon = { ImageVector.vectorResource(id = R.drawable.news) },
        label = "뉴스"
    )

    data object Quiz : BottomScreen(
        rout = "quiz",
        icon = { ImageVector.vectorResource(id = R.drawable.quiz) },
        label = "퀴즈"
    )

    data object Rout : BottomScreen(
        rout = "rout",
        icon = { ImageVector.vectorResource(id = R.drawable.rout) },
        label = "포도송이"
    )
}