package com.nohjason.minari.navigation

import android.util.Log
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
import com.google.common.reflect.TypeToken
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nohjason.minari.R
//import com.nohjason.minari.R
import com.nohjason.minari.firebase.rememberFirebaseAuthLauncher
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectO
import com.nohjason.minari.screens.QizeScreen.Commentary_CorrectX
import com.nohjason.minari.screens.QizeScreen.QuizScreen_play
import com.nohjason.minari.screens.QizeScreen.queIDList
import com.nohjason.minari.screens.profile.my_dictionary.MyDictionaryScreen
import com.nohjason.minari.screens.home.HomeScreen
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.login.LoginScreen
import com.nohjason.minari.screens.profile.ProfileScreen
import com.nohjason.minari.screens.profile.my_dictionary.db.MainViewModel
import com.nohjason.minari.screens.profile.my_dictionary.db.UserEntity
import com.nohjason.minari.screens.quiz.QuizEndScreen
import com.nohjason.minari.screens.quiz.QuizScreen
import com.nohjason.minari.screens.quiz.data.Question
import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.TemporaryPoint
import com.nohjason.minari.screens.quiz.data.Temporary_pointData
import com.nohjason.minari.screens.term.Term
import com.nohjason.minari.screens.term.Test
import kotlin.random.Random

@Composable
fun NavGraph(
    navController: NavHostController,
    allProduct: List<UserEntity>,
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

    val question = QuestionData.getQuestion()
    val dummyUser = Temporary_pointData.getPoint()
//    val list = QuizScreen(navController = navController, user = user)


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
        composable(BottomBarScreen.Quiz.rout, ) {
            QuizScreen(navController = navController, user = dummyUser)
        }
        composable(BottomBarScreen.Profile.rout) {
            ProfileScreen(navController = navController)
        }
        composable(BottomBarScreen.Term.rout) {
            Term(
                navController = navController,
                allProduct = allProduct,
                mainViewModel = viewModel
            )
        }
        composable("myDictionary") {
            MyDictionaryScreen(
                navController = navController,
                mainViewModel = viewModel
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


        //퀴즈-------------------------------------{questionId}부분 지워야함
        composable("quizStartRoute") {
            QuizScreen(navController = navController, user = dummyUser)
//            val jsonString = it.arguments?.getString("arrayList")  // 'it'을 사용하여 arguments를 참조합니다
//            val arrayListType = object : TypeToken<ArrayList<String>>() {}.type  // ArrayList<String> 타입 정보를 얻기 위한 TypeToken 생성
//            val arrayList: ArrayList<String> = Gson().fromJson(jsonString, arrayListType)  // JSON 문자열을 ArrayList<String> 객체로 변환
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
        //--------------------------------------------
    }
}