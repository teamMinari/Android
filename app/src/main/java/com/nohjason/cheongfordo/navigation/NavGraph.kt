package com.nohjason.cheongfordo.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import android.content.Context
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.nohjason.cheongfordo.navigation.bottombar.BottomScreen
import com.nohjason.cheongfordo.preferences.getFromPreferences
import com.nohjason.cheongfordo.preferences.getPreferences
import com.nohjason.cheongfordo.screens.home.HomeScreen
import com.nohjason.cheongfordo.screens.login.screen.LoginScreen
import com.nohjason.cheongfordo.screens.login.LoginViewModel
import com.nohjason.cheongfordo.screens.login.Screens
import com.nohjason.cheongfordo.screens.login.screen.login.SelfLoginScreen
import com.nohjason.cheongfordo.screens.login.screen.signup.Questionnaire
import com.nohjason.cheongfordo.screens.login.screen.signup.SelfSignUpLastScreen
import com.nohjason.cheongfordo.screens.login.screen.signup.SelfSignUpScreen
import com.nohjason.cheongfordo.screens.term.TermScreen
import com.nohjason.cheongfordo.screens.news.News
import com.nohjason.cheongfordo.screens.profile.alias_screen.AliasScreen
import com.nohjason.cheongfordo.screens.profile.directory_screen.DirecScreen
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.cheongfordo.screens.rout.Grape
import com.nohjason.cheongfordo.screens.rout.Grapes
import com.nohjason.cheongfordo.screens.rout.Rout
import com.nohjason.cheongfordo.screens.quiz.data.QuizViewModel
import com.nohjason.cheongfordo.screens.quiz.quiz_end_screen.QuizEndScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletO
import com.nohjason.cheongfordo.screens.quiz.quiz_play.SeletX
import com.nohjason.cheongfordo.screens.quiz.quiz_main.QuizMainScreen
import com.nohjason.cheongfordo.screens.search.Search

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    applicationContext: Context,
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    quizViewModel: QuizViewModel = viewModel()
) {
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")

    val startDestination = if (token.isNullOrEmpty()) {
        Screens.FirstScreen.rout
    } else {
        BottomScreen.Home.rout
    }

    NavHost(
        navController = navController,
        startDestination = startDestination,
        enterTransition = { fadeIn(animationSpec = tween(0)) }
    ) {

        composable(Screens.FirstScreen.rout) {
            LoginScreen(navController = navController)
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
        composable(
            BottomScreen.News.rout,
        ) {
            News(navController = navController)
        }

        // 홈
        composable(BottomScreen.Home.rout) {
            HomeScreen(
                navController = navController,
            )
        }

        // 퀴즈
        composable(BottomScreen.Quiz.rout) {
            QuizMainScreen(
                navHostController = navController,
                quizViewModel = quizViewModel,
                token = token
            )
        }

        // 프로필
        composable(BottomScreen.Profile.rout) {
            ProfileMAinScreen(
                navHostController = navController,
                direcViewModel = DirecViewModel(),
                token = token,
                loginViewModel = loginViewModel
            )
        }

        //저장목록
        composable(Screens.Directory.rout) {
            DirecScreen(
                token = token,
                navController = navController
            )
        }

        //칭호
        composable(Screens.Alias.rout) {
            AliasScreen(navHostController = navController, token = token)
        }

        // 검색 화면
        composable(Screens.Search.rout) {
            Search(navController = navController)
            composable(Screens.Alias.rout) {
                AliasScreen(
                    navHostController = navController,
                    token = token
                )
            }
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
            val text = backStackEntry.arguments?.getString("text")?.replace("@", "/") ?: ""
            TermScreen(text, navController = navController)
        }

        // 로그인
        composable(
            route = Screens.Signup.rout,
        ) {
            SelfSignUpScreen(
                navController = navController
            )
        }


        //퀴즈
        composable(
            route = Screens.QuizSelectO.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) }
        ) {
            SeletO(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            route = Screens.QuizSelectX.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) }
        ) {
            SeletX(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            route = Screens.QuizPlaycreen.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) }
        ) {
            QuizPlayScreen(navHostController = navController, quizViewModel = quizViewModel)
        }
        composable(
            route = Screens.QuizEndScreen.rout,
            enterTransition = { slideInHorizontally(initialOffsetX = { fullWidth -> fullWidth }) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { fullWidth -> -fullWidth }) }
        ) {
            QuizEndScreen(
                quizViewModel = quizViewModel,
                navController = navController,
                token = token
            )
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