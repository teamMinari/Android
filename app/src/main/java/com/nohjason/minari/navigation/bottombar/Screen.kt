package com.nohjason.minari.navigation.bottombar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.minari.R

sealed class Screen(
    val rout: String,
    val title: String,
    val icon: @Composable () -> ImageVector
) {
    data object Home : Screen(
        rout = "home",
        title = "Home",
        icon = { Icons.Default.Home }
    )

    data object Profile : Screen(
        rout = "profile",
        title = "Profile",
        icon = { Icons.Default.Person }
    )

    data object News : Screen(
        rout = "news",
        title = "News",
        icon = { ImageVector.vectorResource(id = R.drawable.news) }
    )

    data object MyDictionary : Screen(
        rout = "dictionary",
        title = "Dictionary",
        icon = { Icons.Default.Lock }
    )

    data object Quiz : Screen(
        rout = "quiz",
        title = "Quiz",
        icon = { ImageVector.vectorResource(id = R.drawable.quiz) }
    )

    data object Rout : Screen(
        rout = "rout",
        title = "Rout",
        icon = { ImageVector.vectorResource(id = R.drawable.rout) }
    )
}