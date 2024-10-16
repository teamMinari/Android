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
    val icon: @Composable () -> ImageVector
) {
    data object Home : BottomScreen(
        rout = "home",
        icon = { Icons.Default.Home }
    )

    data object Profile : BottomScreen(
        rout = "profile",
        icon = { Icons.Default.Person }
    )

    data object News : BottomScreen(
        rout = "news",
        icon = { ImageVector.vectorResource(id = R.drawable.news) }
    )

    data object Quiz : BottomScreen(
        rout = "quiz",
        icon = { ImageVector.vectorResource(id = R.drawable.quiz) }
    )

    data object Rout : BottomScreen(
        rout = "rout",
        icon = { ImageVector.vectorResource(id = R.drawable.rout) }
    )
}