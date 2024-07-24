package com.nohjason.minari.screens.login.UI

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.login.Data.LoginRequest
import com.nohjason.minari.screens.login.Data.UserResponse
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariPurple
import com.nohjason.myapplication.network.MainViewModel
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.Response
import java.time.format.TextStyle
import kotlin.math.max

@Composable
fun SelfLoginScreen(
    context: Context,
    navController: NavController,
    mainViewModel: MainViewModel,
    loginViewModel: LoginViewModel
) {
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_regular),
        Font(R.font.poppins_bold, FontWeight.Bold),
    )

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 25.dp, top = 14.dp),
    ) {
        Image(
            painterResource(id = R.drawable.grape), contentDescription = null,
            modifier = Modifier
                .width(21.dp)
                .height(34.dp)
                .padding(top = 0.dp, start = 0.dp)
        )
        Text(
            text = "로그인",
            modifier = Modifier.padding(top = 55.dp),
            style = androidx.compose.ui.text.TextStyle(
                fontSize = 30.sp,
                fontFamily = poppinsFamily,
                fontWeight = FontWeight.SemiBold
            )
        )


        val annotatedText = buildAnnotatedString {
            append("아직 회원가입을 하지 않으셨다면\n여기서 ")
            pushStringAnnotation("SignUp", " 가입하세요")
            withStyle(SpanStyle(color = MinariBlue, textDecoration = TextDecoration.Underline)) {
                append("가입하세요")
            }
            pop()
        }

        Text(
            text = annotatedText,
            modifier = Modifier
                .padding(top = 22.dp, bottom = 50.dp)
                .clickable {
                    navController.navigate("Singup")
                }
        )

        var id by remember { mutableStateOf("") }
        LoginTextField(
            modifier = Modifier,
            value = id,
            icon_name = "아이디",
            text = "아이디을 입력하세요",
            onValueChange = { id = it },
            visibility = true
        ) {

        }


        var password by rememberSaveable { mutableStateOf("") }
        LoginTextField(
            modifier = Modifier,
            value = password,
            icon_name = "비밀번호",
            text = "비밀번호를 입력하세요",
            onValueChange = { password = it },
            visibility = false
        ) {

        }


        //로그인 저장, 비번 찾기
//        val checkState = remember {
//            mutableStateOf(false)
//        }
//        Row {
//            Checkbox(checked = true, onCheckedChange = {ischecked -> checkState.value = ischecked })
//            Text(text = "이 계정 기억하기")
//
//            Text(text = "비밀번호를 잊으셨나요?",
//                Modifier
//                    .clickable { /*비번 찾기 이동*/ }
//                    .padding(10.dp))
//        }


//        val scope = rememberCoroutineScope()
        val preferencesManager = remember { PreferencesManager(context) }
        val loginResponse by loginViewModel.loginResponse.collectAsState()
//        val data = remember { mutableStateOf(preferencesManager.getData("accessToken", "")) }

        Button(
            onClick = {
                loginViewModel.login(id = id, password = password)
                if (loginResponse != null) {
                    if (loginResponse!!.success) {
                        val token = loginResponse!!.data[0]
                        preferencesManager.saveData("accessToken", token.accessToken)
                        Log.d("TAG", "SelfLoginScreen: ${preferencesManager.getData("accessToken", "")}")
                        navController.navigate(BottomBarScreen.Home.rout)
                    } else {
                        Log.d("TAG", "Login Failed: ${loginResponse!!.message}")
                    }
                }
//                scope.launch {
//                    try {
//                        // 로그인 요청 보내기
//                        val result = loginUser(id = id, password = password)
//                        result?.let {
//                            if (result.success) {
//                                // 로그인 성공 시 처리
//                                TokenManager.saveTokens(
//                                    context,
//                                    result.data.toString(),
//                                    result.data.toString()
//                                )
//                            } else {
//                                // 로그인 실패 시 Toast 메시지 표시
//                                Toast.makeText(
//                                    context,
//                                    "다른 아이디나 비밀번호로 시도해주세요.",
//                                    Toast.LENGTH_SHORT
//                                ).show()
//                            }
//                        }
//                    } catch (e: Exception) {
//                        // 네트워크 오류 등 예외 처리
//                        Toast.makeText(
//                            context,
//                            "다른 아이디나 비밀번호로 시도해주세요.",
//                            Toast.LENGTH_SHORT
//                        ).show()
//                        e.printStackTrace()
//                    }
//                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue),
            modifier = Modifier
                .wrapContentSize()
                .width(320.dp)
                .padding(top = 95.dp, start = 60.dp)
        ) {
            Text(text = "로그인")
        }

//        val tokens by loginViewModel.tokens.collectAsState()
//        Button(onClick = {
//            loginViewModel.loadTokens()
//            if (tokens != null) {
//                val (accessToken, refreshToken) = tokens!!
//                Log.d("TAG", "SelfLoginScreen: $accessToken.")
//                Log.d("TAG", "SelfLoginScreen: $refreshToken.")
//            }
//        }) {
//            Text("Load Tokens")
//        }
    }

}

suspend fun loginUser(id: String, password: String): UserResponse? {
    try {
        // Retrofit 인스턴스 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://cheong.baekjoon.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // ApiService 인터페이스 구현체 생성
        val apiService = retrofit.create(LoginPOST::class.java)

        // 서버로 요청 보내기
        val loginRequest = LoginRequest(id, password)
        val response = apiService.login(loginRequest)

        // 서버 응답 처리
        if (response.isSuccessful) {
            println("서버 요청 성공: ${response.code()}, ${response.body()}, ${response.message()}")
            return response.body()
        } else {
            // 서버 요청 실패 처리
            println("서버 요청 실패: ${response.code()}, ${response.message()}")
            throw Exception("서버 요청 실패: ${response.code()}, ${response.message()}")
        }
    } catch (e: Exception) {
        // 네트워크 오류 등 예외 처리
        println("오류: ${e.message}")
        throw e
    }
}

interface LoginPOST {
    @POST("http://cheong.baekjoon.kr/member/login") // POST 요청을 보낼 엔드포인트 URL을 지정합니다.
    suspend fun login(@Body request: LoginRequest): Response<UserResponse>
}



