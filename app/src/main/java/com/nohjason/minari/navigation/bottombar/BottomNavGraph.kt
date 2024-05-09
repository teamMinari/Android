package com.nohjason.minari.navigation.bottombar

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.window.isPopupLayout
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nohjason.minari.R
import com.nohjason.minari.firebase.rememberFirebaseAuthLauncher
import com.nohjason.minari.screens.dictionary.DictionaryScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.login.LoginScreen
import com.nohjason.minari.screens.profile.ProfileScreen
import com.nohjason.minari.screens.quiz.QuizScreen
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun BottomNavGraph(
    navController: NavHostController,
) {
    val auth = Firebase.auth
    var user by remember { mutableStateOf(auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
        user = result.user
    }, onAuthError = {
        user = null
    })
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current

    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(token)
        .requestEmail().build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)



    NavHost(
        navController = navController,
        startDestination = BottomBarScreen.Login.rout
    ) {

        composable(BottomBarScreen.Login.rout) {
            LoginScreen(
                navController = navController,
                launcher = launcher,
                googleSignInClient = googleSignInClient
            )
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