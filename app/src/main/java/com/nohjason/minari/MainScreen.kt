package com.nohjason.minari

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.navigation.bottombar.BottomBar
import com.nohjason.minari.navigation.NavGraph
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.myapplication.network.MainViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    viewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(
                    Screen.FirstScreen.rout,
                    Screen.Login.rout,
                    Screen.Signup.rout
            )) {
                BottomBar(
                    navController = navController,
                    navBackStackEntry = navBackStackEntry
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            NavGraph(
                navController = navController,
                viewModel = viewModel,
                loginViewModel = loginViewModel
            )
        }
    }
}
