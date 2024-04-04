package com.nohjason.minari.navigation

sealed class BottonNavItem(
    val screenRout: String
) {
    object Main : BottonNavItem(com.nohjason.minari.navigation.Main)
    object Profile : BottonNavItem(com.nohjason.minari.navigation.Profile)
    object Dictionary : BottonNavItem(com.nohjason.minari.navigation.Dictionary)
    object Quiz : BottonNavItem(com.nohjason.minari.navigation.Quiz)

    val items = listOf<BottonNavItem>(
        BottonNavItem.Main,
        BottonNavItem.Profile,
        BottonNavItem.Dictionary,
        BottonNavItem.Quiz
    )
}