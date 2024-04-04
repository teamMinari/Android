package com.nohjason.minari.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nohjason.minari.R
import com.nohjason.minari.firebase.rememberFirebaseAuthLauncher
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
    val auth = Firebase.auth
    var user by remember { mutableStateOf(auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
        },
        onAuthError = {
            user = null
        }
    )
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current

    val gso =
        GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(token)
            .requestEmail()
            .build()
    val googleSignInClient = GoogleSignIn.getClient(context, gso)

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("login") {
            LoginScreen(navController, launcher, googleSignInClient)
        }
        composable(BottonNavItem.Main.screenRout) {
            MainScreen(navController)
        }
        composable(BottonNavItem.Profile.screenRout) {
            ProfileScreen()
        }
        composable(BottonNavItem.Dictionary.screenRout) {
            DictionaryScreen()
        }
        composable(BottonNavItem.Quiz.screenRout) {
            QuizScreen()
        }
    }

}