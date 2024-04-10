package com.nohjason.minari.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nohjason.minari.R
import com.nohjason.minari.screens.DictionaryScreen
import com.nohjason.minari.screens.HomeScreen
import com.nohjason.minari.screens.LoginScreen
import com.nohjason.minari.screens.ProfileScreen
import com.nohjason.minari.screens.QuizScreen

@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Login.rout
    ) {
        composable(BottomBarScreen.Login.rout) {
            LoginScreen(navController = navController)
        }
        composable(BottomBarScreen.Home.rout) {
            HomeScreen()
        }
        composable(BottomBarScreen.Profile.rout) {
            ProfileScreen()
        }
        composable(BottomBarScreen.Dictionary.rout) {
            DictionaryScreen()
        }
        composable(BottomBarScreen.Quiz.rout) {
            QuizScreen()
        }
    }

}