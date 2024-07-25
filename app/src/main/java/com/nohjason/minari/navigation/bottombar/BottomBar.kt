package com.nohjason.minari.navigation.bottombar

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

@Composable
fun BottomBar(
    navController: NavHostController,
    navBackStackEntry: NavBackStackEntry?
) {
    val screens = listOf(
        Screen.Term,
        Screen.Home,
        Screen.Quiz,
        Screen.Profile,
    )

//    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    BottomNavigation {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
                currentDestination = currentDestination,
                navController = navController
            )
        }
    }
}

@Composable
fun RowScope.AddItem(
    screen: Screen,
    currentDestination: NavDestination?,
    navController: NavHostController
) {
    BottomNavigationItem(
        label = {
            Text(text = screen.title)
//            Text(text = "")
        },
        icon = {
            Icon(
                imageVector = screen.icon(),
                contentDescription = null
            )
        },
        selected = currentDestination?.hierarchy?.any {
            it.route == screen.rout
        } == true,
        onClick = {
            navController.navigate(screen.rout)
        },
        selectedContentColor = MinariBlue,
        unselectedContentColor = Color.LightGray,
        modifier = Modifier.background(Color.White)
    )
}