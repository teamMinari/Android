package com.nohjason.minari.navigation.bottombar

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import com.nohjason.minari.ui.theme.MinariBlue
import kotlin.math.round

@Composable
fun BottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?
) {
    val bottomScreens = listOf(
        BottomScreen.Rout,
        BottomScreen.News,
        BottomScreen.Home,
        BottomScreen.Quiz,
        BottomScreen.Profile,
    )

    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        bottomScreens.forEach { screen ->
            AddItem(
                bottomScreen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }

}

@Composable
fun RowScope.AddItem(
    bottomScreen: BottomScreen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        icon = {
            Icon(
                imageVector = bottomScreen.icon(),
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == bottomScreen.rout
        } == true,
        onClick = {
            Log.d("TAG", "AddItem: ${bottomScreen.rout}")
            navController.navigate(bottomScreen.rout)
        },
        selectedContentColor = MinariBlue,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.background(Color.White)
    )
}