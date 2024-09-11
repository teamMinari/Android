package com.nohjason.minari.navigation

import ProfileMAinScreen
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.login.LoginScreen
import com.nohjason.minari.screens.login.UI.SelfLoginScreen
import com.nohjason.minari.screens.login.UI.SelfSingUpScreen
import com.nohjason.minari.screens.quiz.QuizPlayScreen
import com.nohjason.minari.screens.quiz.data.QuizViewModel
import com.nohjason.minari.screens.quiz.quiz_main.QuizMainScreen
import com.nohjason.minari.screens.term.Term
import com.nohjason.minari.screens.term.Test
import com.nohjason.myapplication.network.MainViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import com.nohjason.minari.screens.profile.DirecScreen
import com.nohjason.minari.screens.profile.DummyGpStatusResponse.gpStatusResponse
import com.nohjason.minari.screens.profile.DummyGpsStatusResponse.gpsStatusResponse
import com.nohjason.minari.screens.profile.DummyGpseStatusResponse.gpseStatusResponse
import com.nohjason.minari.screens.profile.DummyTermStatusResponse.termStatusResponse

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

    val profileData by viewModel.profileData.collectAsState()



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
//            QuizMainScreen(qtAll = dummyResponse, navHostController = navController)
        }
        composable(BottomBarScreen.Profile.rout) {
            ProfileMAinScreen(
                profileData = profileData,
                navController = navController
            )
        }

        composable("myDirectory") {
            DirecScreen(term = termStatusResponse, gpse = gpseStatusResponse, gps = gpsStatusResponse, gp = gpStatusResponse)
        }


        composable(BottomBarScreen.Term.rout) {
            Term(
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
        composable("quizplay") {
            // ViewModel 인스턴스 가져오기
//            val quizViewModel: QuizViewModel = viewModel()
//            println(quizViewModel.playData)
//
//            // playData가 null이 아닌지 확인 후 전달
//            quizViewModel.playData?.let { playData ->
//                QuizPlayScreen(qestion = playData)
//            }
        }

//        composable(
//            route = "quizplay/{playDataJson}",  // 경로 정의
//            arguments = listOf(navArgument("playDataJson") { type = NavType.StringType })  // 인자 설정
//        ) { backStackEntry ->
//            val playDataJson = backStackEntry.arguments?.getString("playDataJson")  // 전달된 JSON 데이터를 추출
//            val playData = Gson().fromJson(playDataJson, PlayData::class.java)  // JSON 데이터를 PlayData 객체로 변환
//            QuizPlayScreen(question = playData)  // QuizPlayScreen에 변환된 데이터를 전달
//        }



//        로그인
        composable("Singup"){
            SelfSingUpScreen(navController = navController)
        }
        composable("Login"){
            SelfLoginScreen(navController = navController)
        }
    }
}