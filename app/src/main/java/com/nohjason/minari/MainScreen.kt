package com.nohjason.minari

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.navigation.BottomBar
import com.nohjason.minari.navigation.BottomBarScreen
import com.nohjason.minari.navigation.BottomNavGraph

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var isBottomBarVisible = true

    navBackStackEntry?.destination?.route.let { route->
        isBottomBarVisible = when(route) {
            BottomBarScreen.Login.rout -> false
            else -> true
        }
    }

    Scaffold(
        bottomBar = {
            if (isBottomBarVisible) BottomBar(
                navController = navController,
                navBackStackEntry = navBackStackEntry
            )
        }
    ) {
        BottomNavGraph(navController = navController)
    }
}