package com.nohjason.minari.screens.home.news.button

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.ui.graphics.vector.ImageVector

data class CategoryButton(
    val title: String,
    val icon: ImageVector
)

fun getDummyNewsButton(): List<CategoryButton> {
    return listOf(
        CategoryButton("부동산", Icons.Default.Home),
        CategoryButton("증권", Icons.Default.AccountBox),
        CategoryButton("금융", Icons.Default.CheckCircle),
        CategoryButton("채권", Icons.Default.Person),
    )
}
