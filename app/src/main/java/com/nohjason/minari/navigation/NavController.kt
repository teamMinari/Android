package com.nohjason.minari.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.screens.DictionaryScreen
import com.nohjason.minari.screens.LoginScreen
import com.nohjason.minari.screens.MainScreen
import com.nohjason.minari.screens.ProfileScreen
import com.nohjason.minari.screens.QuizScreen

@Composable
fun NavController(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String = "login"
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(navController)
        }
        composable("main") {
            MainScreen()
        }
        composable("profile") {
            ProfileScreen()
        }
        composable("dictionary") {
            DictionaryScreen()
        }
        composable("quiz") {
            QuizScreen()
        }
    }

}