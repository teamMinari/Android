package com.nohjason.minari.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
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
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectO
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectX
import com.nohjason.minari.screens.QizeScreen.QuizScreen_play
import com.nohjason.minari.screens.profile.my_dictionary.MyDictionaryScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.login.LoginScreen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.UI.SelfLoginScreen
import com.nohjason.minari.screens.login.UI.SelfSingUpScreen
import com.nohjason.minari.screens.profile.ProfileScreen
import com.nohjason.minari.screens.quiz.QuizEndScreen
import com.nohjason.minari.screens.quiz.QuizScreen
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.screens.term.Term
import com.nohjason.minari.screens.term.Test
import com.nohjason.myapplication.network.MainViewModel

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    loginViewModel: LoginViewModel
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

    val question = QuestionData.getQuestion()
    val dummyUser = Temporary_pointData.getPoint()


    NavHost(
        navController = navController,
        startDestination = Screen.FirstScreen.rout
    ) {

        composable(Screen.FirstScreen.rout) {
            LoginScreen(
                navController = navController,
//                launcher = launcher,
//                googleSignInClient = googleSignInClient
            )
        }
        composable(Screen.Home.rout) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Quiz.rout, ) {
            QuizScreen(navController = navController, user = dummyUser)
        }
        composable(Screen.Profile.rout) {
            ProfileScreen(navController = navController)
        }
        composable(Screen.Term.rout) {
            Term(
                context = context,
                navController = navController,
                viewModel = viewModel,
            )
        }
        composable(Screen.MyDictionary.rout) {
            MyDictionaryScreen(
                context,
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
        composable("quizStartRoute") {
            QuizScreen(navController = navController, user = dummyUser)
        }
        composable("quizQuestionRoute") {
            QuizScreen_play(que = question, navController = navController, user = dummyUser, )
        }
        composable("quizQuestionORoute") { backStackEntry ->
            Commentary_CorrectO(que = question, navController = navController, user = dummyUser)
            // 퀴즈 O질문 결과 화면
        }
        composable("quizQuestionXRoute") { backStackEntry ->
            Commentary_CorrectX(que = question, navController = navController, user = dummyUser)
            // 퀴즈 X질문 결과 화면
        }
        composable("quizComentoryRoute") { backStackEntry ->
            QuizEndScreen(navController = navController, user = dummyUser)
        }
        
//        로그인
        composable(
            route = Screen.Signup.rout,
        ){
            SelfSingUpScreen(
                navController = navController
            )
        }
        composable(
            route = Screen.Login.rout,
            enterTransition = {
                fadeIn(animationSpec = tween(700))
            },
            exitTransition = {
                fadeOut(animationSpec = tween(700))
            }
        ){
            SelfLoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
    }
}