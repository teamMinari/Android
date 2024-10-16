package com.nohjason.minari.screens.login.screen.signup

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.preferences.saveToPreferences
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.poppins_regular
import com.nohjason.minari.ui.theme.poppins_semibold

@Composable
fun SelfSignUpScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 25.dp, vertical = 14.dp),
    ) {
        Image(
            painterResource(id = R.drawable.grape),
            contentDescription = null,
            modifier = Modifier.size(40.dp)
        )

        Spacer(modifier = Modifier.weight(0.1f))

        Text(
            text = "회원가입",
            fontFamily = poppins_semibold,
            fontSize = 25.sp
        )
        Text(
            text = "이미 계정이 있다면",
            fontSize = 15.sp,
            fontFamily = poppins_regular
        )
        Text(
            text = "로그인은?",
            color = MinariBlue,
            fontSize = 15.sp,
            fontFamily = poppins_semibold,
            modifier = Modifier
                .clickable {
                    navController.navigate("Login")
                },
        )

        Spacer(modifier = Modifier.weight(0.1f))

        var email by remember { mutableStateOf("") }
        // 이메일 입력란을 구현합니다.
        LoginTextField(
            modifier = Modifier,
            value = email,
            icon_name = "이메일",
            text = "이메일 주소를 입력하세요",
            onValueChange = { email = it },
            visibility = true
        )

        Spacer(modifier = Modifier.weight(0.1f))

        var id by remember { mutableStateOf("") }
        // 아이디
        LoginTextField(
            modifier = Modifier,
            value = id,
            icon_name = "아이디",
            text = "아이디를 입력하세요",
            onValueChange = { id = it },
            visibility = true
        )

        Spacer(modifier = Modifier.weight(0.1f))

        var password by remember { mutableStateOf("") }
        LoginTextField(
            modifier = Modifier,
            value = password,
            icon_name = "비밀번호",
            text = "비밀번호를 입력하세요",
            onValueChange = { password = it },
            visibility = false
        )

        Spacer(modifier = Modifier.weight(0.1f))

        var repassword by remember { mutableStateOf("") }
        // 비밀번호 재입력란
        LoginTextField(
            modifier = Modifier,
            value = repassword,
            icon_name = "비밀번호 재확인",
            text = "비밀번호를 다시 입력하세요",
            onValueChange = { repassword = it },
            visibility = false
        )


        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        var popupMessage by remember { mutableStateOf("") }
        val context = LocalContext.current
        val signUpResult = loginViewModel.signUpResult.collectAsState()
        val isLoading = loginViewModel.isLoading.collectAsState(initial = false) // 로딩 상태 가져오기
        val preferences = getPreferences()
        val loginResponse by loginViewModel.loginRequest.collectAsState()
        LaunchedEffect(loginResponse) {
            if (loginResponse != null ) {
                saveToPreferences(preferences, "token", loginResponse!!.data.accessToken)
//                navController.navigate(BottomScreen.Home.rout)
                Log.d("TAG", "SelfLoginScreen: ${loginResponse!!.data.accessToken}")
            }
        }

        LaunchedEffect(signUpResult.value) {
            if (signUpResult.value != null) {
                Toast.makeText(context, signUpResult.value, Toast.LENGTH_SHORT).show()
                if (signUpResult.value!!.contains("성공", ignoreCase = true)) {
                    // 로그인 화면으로 이동하는 코드
                    Log.d("TAG", "SelfSignUpScreen: 성공")
                    navController.navigate(Screens.Question.rout)
                    loginViewModel.login(id = id, password = password)
                }
            }
        }

        Spacer(modifier = Modifier.weight(0.1f))

        Spacer(modifier = Modifier.weight(1f))

        if (isLoading.value) {
            Box(modifier = Modifier.fillMaxWidth()) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(50.dp)
                        .wrapContentSize()
                        .align(Alignment.Center)
                ) // 로딩 중 표시
            }
        } else {
            Button(
                onClick = {
                    when {
                        id.length > 20 -> {
                            popupMessage = "아이디는 20자 이하이어야 합니다."
                            Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT).show()
                        }

                        password.length > 20 -> {
                            popupMessage = "비밀번호는 20자 이하이어야 합니다."
                            Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT).show()
                        }

                        !email.matches(emailPattern.toRegex()) -> {
                            popupMessage = "올바른 이메일 형식을 입력하세요."
                            Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT).show()
                        }

                        password != repassword -> {
                            popupMessage = "비밀번호가 일치하지 않습니다."
                            Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT).show()
                        }

                        else -> {
                            loginViewModel.signUp(id, password, repassword, email)
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = MinariBlue),
                modifier = Modifier
                    .wrapContentSize()
                    .width(320.dp)
                    .padding(top = 10.dp, start = 20.dp)
            ) {
                Text(text = "회원가입")
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
fun TestSignUp() {
    SelfSignUpScreen(navController = rememberNavController())
}