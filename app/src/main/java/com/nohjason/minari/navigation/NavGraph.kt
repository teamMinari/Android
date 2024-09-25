package com.nohjason.minari.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.login.screen.LoginScreen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.login.screen.login.SelfLoginScreen
import com.nohjason.minari.screens.login.screen.signup.Questionnaire
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpLastScreen
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpScreen
import com.nohjason.minari.screens.term.TermScreen
import com.nohjason.minari.screens.news.News
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.profile.name_style.Style
import com.nohjason.minari.screens.rout.Grape
import com.nohjason.minari.screens.rout.Grapes
import com.nohjason.minari.screens.rout.Rout
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.profile.directory.DirecScreen
import com.nohjason.minari.screens.profile.alias.AliasScreen
import com.nohjason.minari.screens.profile.directory.DirecViewModel
import com.nohjason.minari.screens.quiz.quiz_end.QuizEndScreen
import com.nohjason.minari.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.minari.screens.quiz.quiz_play.SeletO
import com.nohjason.minari.screens.quiz.quiz_play.SeletX
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel = viewModel(),
    quizViewModel: QuizViewModel = viewModel()
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    val direcViewModel: DirecViewModel = viewModel()
    LaunchedEffect(Unit) {
        direcViewModel.getGpse()
        direcViewModel.getGps()
        direcViewModel.getGp()
        direcViewModel.getDorecTerm()
    }
    val context = LocalContext.current
//    val data by profileViewModel.profileData.collectAsState()
    val data = profileViewModel.profileData.collectAsState().value




    NavHost(
        navController = navController,
        startDestination = Screens.FirstScreen.rout,
    ) {

        composable(Screens.FirstScreen.rout) {
            LoginScreen(
                navController = navController,
            )
        }

        composable(
            route = Screens.LastSignup.rout,
        ) {
            SelfSignUpLastScreen(
                navController = navController
            )
        }

        composable(Screens.Login.rout) {
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

        composable("myDirectory") {
            val termResponse = direcViewModel.termData.collectAsState().value
            val gpseResponse = direcViewModel.gpseData.collectAsState().value
            val gpsResponse = direcViewModel.gpsData.collectAsState().value
            val gpResponse = direcViewModel.gpData.collectAsState().value
            DirecScreen(
                term = termResponse,
                gpse = gpseResponse,
                gps = gpsResponse,
                gp = gpResponse
            )
        }

        composable("myAlias") {
            println(data)
            if (data == null) {
                HomeScreen(navController = navController)
                Toast.makeText(context, "데이터를 불러올 수 없습니다.", Toast.LENGTH_SHORT).show()
            } else {
                AliasScreen(level = data.level, exp = data.exp)
            }
        }

        // 홈
        composable(BottomScreen.Home.rout) {
            HomeScreen(
                navController = navController,
            )
        }

        // 퀴즈
        composable(BottomScreen.Quiz.rout) {
            QuizMainScreen(navHostController = navController, quizViewModel = quizViewModel)
        }

        // 프로필
        composable(BottomScreen.Profile.rout) {
            LaunchedEffect(Unit) {
                profileViewModel.getProfile(token)
            }
            ProfileMAinScreen(navHostController = navController, profileData = data)
        }

        // 포도알
        composable(Screens.Grapes.rout + "/{id}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            Grapes(
                id = id.toInt(),
                navController = navController,
            )
        }

        // 포도씨
        composable(Screens.Grape.rout + "/{id}/{title}") { backStackEntry ->
            val id = backStackEntry.arguments?.getString("id") ?: "0"
            val title = backStackEntry.arguments?.getString("title") ?: ""
            Grape(
                navController = navController,
                gpseId = id.toInt(),
                title = title,
            )
        }

        // 용어
        composable(Screens.Term.rout + "/{text}") { backStackEntry ->
            val text = backStackEntry.arguments?.getString("text") ?: ""
            TermScreen(text, navController = navController)
        }

        // 칭호
        composable(Screens.Style.rout) {
            Style(
                navController = navController,
            )
        }


        // 로그인
        composable(
            route = Screens.Signup.rout,
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
        }

        //퀴즈
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

        composable(
            "quizplay",
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }

        composable(
            Screens.QuizEndScreen.rout,
            enterTransition = {
                slideIntoContainer(
                    AnimatedContentTransitionScope.SlideDirection.Left,
                    animationSpec = tween(500)
                )
            }
        ) {
            QuizEndScreen(quizViewModel = quizViewModel, navController = navController)
        }


        //모르는거
        composable(
            route = Screens.Question.rout,
        ) {
            Questionnaire(
                navController = navController
            )
        }
    }
}