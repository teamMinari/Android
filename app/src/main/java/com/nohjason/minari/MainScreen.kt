package com.nohjason.minari

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.navigation.bottombar.BottomBar
import com.nohjason.minari.navigation.NavGraph
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    loginViewModel: LoginViewModel,
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    var showBottomBar by remember { mutableStateOf(true) }

    val currentDestination = navBackStackEntry?.destination

    Scaffold(
        bottomBar = {
            if (currentDestination?.route !in listOf(
                    Screens.FirstScreen.rout,
                    Screens.Login.rout,
                    Screens.Signup.rout,
                    Screens.Question.rout,
                    Screens.QuizPlaycreen.rout,
                    Screens.QuizSelectO.rout,
                    Screens.QuizSelectX.rout,
                    Screens.LastSignup.rout,
                )
            ) {
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
                loginViewModel = loginViewModel,
                lifecycleScope = lifecycleScope,
                applicationContext = applicationContext
            )
        }
    }
}