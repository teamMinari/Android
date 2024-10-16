package com.nohjason.minari.navigation

import ProfileMAinScreen
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import android.annotation.SuppressLint
import android.app.Activity.RESULT_OK
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.fadeIn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.google.android.gms.auth.api.identity.Identity
import com.nohjason.minari.firebase.GoogleAuthUiClient
import com.nohjason.minari.firebase.SignInViewModel
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.login.screen.LoginScreen
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.login.screen.login.SelfLoginScreen
import com.nohjason.minari.screens.login.screen.signup.Questionnaire
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpLastScreen
import com.nohjason.minari.screens.login.screen.signup.SelfSignUpScreen
import com.nohjason.minari.screens.term.TermScreen
import com.nohjason.minari.screens.news.News
import com.nohjason.minari.screens.profile.alias_screen.AliasScreen
import com.nohjason.minari.screens.profile.directory_screen.DirecScreen
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.profile.profile_data.DummyProfileData.profileData
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.rout.Grape
import com.nohjason.minari.screens.rout.Grapes
import com.nohjason.minari.screens.rout.Rout
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.quiz_end_screen.QuizEndScreen
import com.nohjason.minari.screens.quiz.quiz_play.QuizPlayScreen
import com.nohjason.minari.screens.quiz.quiz_play.SeletO
import com.nohjason.minari.screens.quiz.quiz_play.SeletX
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen
import kotlinx.coroutines.launch

@SuppressLint("ComposableDestinationInComposeScope")
@Composable
fun NavGraph(
    applicationContext: Context,
    lifecycleScope: LifecycleCoroutineScope,
    navController: NavHostController,
    loginViewModel: LoginViewModel,
    profileViewModel: ProfileViewModel = viewModel(),
    quizViewModel: QuizViewModel = viewModel()
) {
    val context = LocalContext.current
    val googleAuthUiClient by lazy {
        GoogleAuthUiClient(
            context = applicationContext,
            oneTapClient = Identity.getSignInClient(applicationContext)
        )
    }
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
//    val data by profileViewModel.profileData.collectAsState()
    val data = profileViewModel.profileData.collectAsState().value




    NavHost(
        navController = navController,
        startDestination = Screens.FirstScreen.rout,
        enterTransition = { fadeIn(animationSpec = tween(0)) }
    ) {

        composable(Screens.FirstScreen.rout) {
            val viewModel = viewModel<SignInViewModel>()
            val state by viewModel.state.collectAsState()

            val launcher = rememberLauncherForActivityResult(
                contract = ActivityResultContracts.StartIntentSenderForResult(),
                onResult = { result ->
                    if (result.resultCode == RESULT_OK) {
                        lifecycleScope.launch {
                            val signInResult = googleAuthUiClient.signInwithIntent(
                                intent = result.data ?: return@launch
                            )
                            viewModel.onSignInResult(signInResult)
                        }
                    }
                }
            )

            LaunchedEffect(key1 = state.isSignInSuccessful) {
                if (state.isSignInSuccessful) {
                    Toast.makeText(
                        applicationContext,
                        "Sign in successful",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            LoginScreen(
                navController = navController,
                state = state,
                onSignInClick = {
                    Log.d("TAG", "NavGraph: ?")
                    lifecycleScope.launch {
                        val signInIntentSender = googleAuthUiClient.signIn()
                        launcher.launch(
                            IntentSenderRequest.Builder(
                                signInIntentSender ?: return@launch
                            ).build()
                        )
                    }
                }
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
                profileViewModel = profileViewModel,
                direcViewModel = DirecViewModel(),
                token = token,
//                preferencesManager = preferencesManager
            )
        }

        //저장목록
        composable(Screens.Directory.rout) {
            DirecScreen(
                direcViewModel = DirecViewModel(),
                token = token
            )
        }

        //칭호
        composable(Screens.Alias.rout){
            AliasScreen(navHostController = navController, profileViewModel = profileViewModel, token = token)
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
            text.replace("@", "/")
            TermScreen(text, navController = navController)
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
            Screens.QuizSelectO.rout,
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
            Screens.QuizSelectX.rout,
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
            Screens.QuizPlaycreen.rout,
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
            QuizEndScreen(quizViewModel = quizViewModel, navController = navController, token=token)
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