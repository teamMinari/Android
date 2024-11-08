package com.nohjason.cheongfordo

import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.nohjason.cheongfordo.navigation.bottombar.BottomBar
import com.nohjason.cheongfordo.navigation.NavGraph
import com.nohjason.cheongfordo.screens.login.LoginViewModel
import com.nohjason.cheongfordo.screens.login.Screens

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen(
    loginViewModel: LoginViewModel = viewModel(),
    lifecycleScope: LifecycleCoroutineScope,
    applicationContext: Context
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()

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
                applicationContext = applicationContext,
            )
        }
    }
}