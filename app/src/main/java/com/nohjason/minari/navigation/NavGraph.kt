package com.nohjason.minari.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
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
//import com.nohjason.minari.R
import com.nohjason.minari.firebase.rememberFirebaseAuthLauncher
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.profile.my_dictionary.MyDictionaryScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.login.LoginScreen
import com.nohjason.minari.screens.login.UI.SelfLoginScreen
import com.nohjason.minari.screens.login.UI.SelfSingUpScreen
import com.nohjason.minari.screens.profile.ProfileScreen
import com.nohjason.minari.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.dummyResponse
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen
import com.nohjason.minari.screens.term.Term
import com.nohjason.minari.screens.term.Test
import com.nohjason.myapplication.network.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.minari.screens.quiz.quiz_end.QuizEndScreen
import com.nohjason.minari.screens.quiz.quiz_play.SeletO
import com.nohjason.minari.screens.quiz.quiz_play.SeletX

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel
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

    //퀴즈 뷰모델
    val quizViewModel: QuizViewModel = viewModel()



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
            HomeScreen(navController = navController)
        }
        composable(BottomBarScreen.Quiz.rout) {
            //퀴즈
            QuizMainScreen(qtAll= dummyResponse, navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(BottomBarScreen.Profile.rout) {
            ProfileScreen(navController = navController)
        }
        composable(BottomBarScreen.Term.rout) {
            Term(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("myDictionary") {
            MyDictionaryScreen(
                navController = navController,
                viewModel = viewModel
            )
        }
        composable("test/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            Test(text, navController = navController)
        }

        composable("InProduction/{title}/{value}") { backStackEntry ->
            val title = backStackEntry.arguments?.getString("title") ?: ""
            val value = backStackEntry.arguments?.getString("value") ?: ""
            InProduction(title = title, value = value)
        }



        //퀴즈
        composable(
            "quizplay",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            // playData가 null이 아닌지 확인 후 전달
            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            route = "Select_O",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            SeletO(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            "Select_X",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ){
            SeletX(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            "End",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }

        ) { backStackEntry ->
            QuizEndScreen(quizViewModel = quizViewModel)
        }



//        로그인
        composable("Singup"){
            SelfSingUpScreen(navController = navController)
        }
        composable("Login"){
            SelfLoginScreen(navController = navController)
        }
    }
}