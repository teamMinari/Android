package com.nohjason.minari.navigation

import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectO
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectX
import com.nohjason.minari.screens.QizeScreen.QuizScreen_play
import com.nohjason.minari.screens.profile.my_dictionary.MyDictionaryScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.login.screen.LoginScreen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.screens.login.screen.login.SelfLoginScreen
import com.nohjason.minari.screens.login.screen.signup.Questionnaire
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpLastScreen
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpScreen
import com.nohjason.minari.screens.profile.ProfileScreen
import com.nohjason.minari.screens.quiz.QuizEndScreen
import com.nohjason.minari.screens.quiz.QuizScreen
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.screens.term.Term
import com.nohjason.minari.screens.term.Test
import com.nohjason.minari.network.MainViewModel
import com.nohjason.minari.screens.news.News
import com.nohjason.minari.screens.profile.name_style.Style
import com.nohjason.minari.screens.rout.Grape
import com.nohjason.minari.screens.rout.Rout

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
//    val auth = Firebase.auth
//    var user by remember { mutableStateOf(auth.currentUser) }
//    val launcher = rememberFirebaseAuthLauncher(onAuthComplete = { result ->
//        user = result.user
//    }, onAuthError = {
//        user = null
//    })
//    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current
//
//    val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestIdToken(token)
//        .requestEmail().build()
//    val googleSignInClient = GoogleSignIn.getClient(context, gso)
//
    val question = QuestionData.getQuestion()
    val dummyUser = Temporary_pointData.getPoint()


    NavHost(
        navController = navController,
//        startDestination = Test.FirstScreen.rout
        startDestination = Screen.Home.rout
    ) {

        composable(Test.FirstScreen.rout) {
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
        composable(Screen.News.rout) {
            News()
        }
        composable(Screen.Rout.rout) {
            Rout(navController = navController)
        }
        composable(Test.Grape.rout) {
            Grape()
        }

        composable(Screen.MyDictionary.rout) {
            MyDictionaryScreen(
                context = context,
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

        // 칭호
        composable(Test.Style.rout) {
            Style(
                navController = navController,
            )
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
        
        // 로그인
        composable(
            route = Test.Signup.rout,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ){
            SelfSignUpScreen(
                navController = navController
            )
        }

        composable(
            route = Test.Question.rout,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ){
            Questionnaire(
                navController = navController
            )
        }

        composable(
            route = Test.LastSignup.rout,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ){
            SelfSignUpLastScreen(
                navController = navController
            )
        }

        composable(
            route = Test.Login.rout,
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { fullWidth -> fullWidth },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { fullWidth -> -fullWidth },
                    animationSpec = tween(700)
                )
            }
        ){
            SelfLoginScreen(
                navController = navController,
                loginViewModel = loginViewModel
            )
        }
    }
}