package com.nohjason.minari.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
//import com.google.android.gms.auth.api.signin.GoogleSignIn
//import com.google.android.gms.auth.api.signin.GoogleSignInOptions
//import com.google.firebase.auth.ktx.auth
//import com.google.firebase.ktx.Firebase
import com.nohjason.minari.R
//import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
//import com.nohjason.minari.screens.login.UI.SelfLoginScreen
import com.nohjason.minari.screens.login.screen.LoginScreen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.screens.login.screen.login.SelfLoginScreen
import com.nohjason.minari.screens.login.screen.signup.Questionnaire
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpLastScreen
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpScreen
import com.nohjason.minari.screens.term.Test
import com.nohjason.minari.screens.news.News
import com.nohjason.minari.screens.profile.DirecScreen
import com.nohjason.minari.screens.profile.DummyGpStatusResponse.gpStatusResponse
import com.nohjason.minari.screens.profile.DummyGpsStatusResponse.gpsStatusResponse
import com.nohjason.minari.screens.profile.DummyGpseStatusResponse.gpseStatusResponse
import com.nohjason.minari.screens.profile.DummyTermStatusResponse.termStatusResponse
import com.nohjason.minari.screens.profile.ProfileViewModel
import com.nohjason.minari.screens.profile.name_style.Style
import com.nohjason.minari.screens.rout.Grape
import com.nohjason.minari.screens.rout.Grapes
import com.nohjason.minari.screens.rout.Rout
import com.nohjason.myapplication.network.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.minari.TestScreen
import com.nohjason.minari.screens.quiz.QuizeViewModel
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.quiz_end.QuizEndScreen
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen
import com.nohjason.minari.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.minari.screens.quiz.quiz_play.SeletO
import com.nohjason.minari.screens.quiz.quiz_play.SeletX

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel = viewModel(),
    quizViewModel: QuizViewModel = viewModel()
) {
    NavHost(
        navController = navController,
        startDestination = Test.FirstScreen.rout,
    ) {

        composable(Test.FirstScreen.rout) {
            LoginScreen(
                navController = navController,
            )
        }

        composable(
            route = Test.LastSignup.rout,
        ) {
            SelfSignUpLastScreen(
                navController = navController
            )
        }

        composable(Test.Login.rout) {
            SelfLoginScreen(navController = navController, loginViewModel = loginViewModel)
        }

        // 튜토리얼
        composable(BottomScreen.Rout.rout) {
            Rout(navController = navController)
        }

        // 뉴스
        composable(BottomScreen.News.rout) {
            News(navController = navController)
        }

        // 홈
        composable(BottomScreen.Home.rout) {
            HomeScreen(
                navController = navController,
            )
        }

        composable(BottomScreen.Quiz.rout) {
            QuizMainScreen(navHostController = navController)
        }

        // 프로필
        composable(BottomScreen.Profile.rout) {
            ProfileMAinScreen(
//                profileData = data,
                navController = navController
            )
        }

        composable("myDirectory") {
            DirecScreen(
                term = termStatusResponse,
                gpse = gpseStatusResponse,
                gps = gpsStatusResponse,
                gp = gpStatusResponse
            )
        }

        // 포도알
        composable(Test.Grapes.rout + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            Grapes(
                id = id.toInt(),
                navController = navController,
            )
        }

        // 포도씨
        composable(Test.Grape.rout + "/{id}/{title}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            val title = backStackEntry.arguments?.getString("title") ?: ""
            Grape(
                navController = navController,
                gpseId = id.toInt(),
                title = title,
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
        ) {
            SeletX(navHostController = navController, quizViewModel = quizViewModel)
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
        ) {
            SelfSignUpScreen(
                navController = navController
            )

//        composable(
//            route = "quizplay/{playDataJson}",  // 경로 정의
//            arguments = listOf(navArgument("playDataJson") { type = NavType.StringType })  // 인자 설정
//        ) { backStackEntry ->
//            val playDataJson = backStackEntry.arguments?.getString("playDataJson")  // 전달된 JSON 데이터를 추출
//            val playData = Gson().fromJson(playDataJson, PlayData::class.java)  // JSON 데이터를 PlayData 객체로 변환
//            QuizPlayScreen(question = playData)  // QuizPlayScreen에 변환된 데이터를 전달
//        }

//            composable(BottomScreen.Quiz.rout) {
//                TestScreen()
//            }

            composable(
                route = Test.Question.rout,
            ) {
                Questionnaire(
                    navController = navController
                )
            }
        }
    }
}