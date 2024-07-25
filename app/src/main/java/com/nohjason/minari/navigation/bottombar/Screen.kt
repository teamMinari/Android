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
    data object FirstScreen : Screen(
        rout = "firstScreen",
        title = "First",
        icon = { Icons.Default.Lock }
    )

    data object Login : Screen(
        rout = "login",
        title = "Login",
        icon = { Icons.Default.Lock }
    )

    data object Signup : Screen(
        rout = "signup",
        title = "Signup",
        icon = { Icons.Default.Lock }
    )

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

    data object Term : Screen(
        rout = "term",
        title = "Term",
        icon = { ImageVector.vectorResource(id = R.drawable.term) }
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
}