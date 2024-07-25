package com.nohjason.minari.screens.login.UI

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun SelfSingUpScreen(
    navController: NavController,
    loginViewModel: LoginViewModel = viewModel()
){
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_regular),
        Font(R.font.poppins_bold, FontWeight.Bold),)

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight()
            .padding(start = 25.dp, top = 14.dp)
    ){
        Image(painterResource(id = R.drawable.grape), contentDescription = null, modifier = Modifier
            .width(21.dp)
            .height(34.dp))

        Text(text = "회원가입", fontFamily = poppinsFamily, fontWeight = FontWeight.SemiBold, fontSize = 30.sp, modifier = Modifier.padding(top = 55.dp))
        val annotatedText = buildAnnotatedString {
            append("이미 계정이 있으시다면\n여기서 ")
            pushStringAnnotation("SignUp", "여기서 로그인하세요")
            withStyle(SpanStyle(color = MinariBlue, textDecoration = TextDecoration.Underline)) {
                append("로그인하세요")
            }
            pop()
        }
        Text(
            text = annotatedText,
            fontFamily = poppinsFamily,
            fontSize = 16.sp,
            modifier = Modifier
                .padding(top = 22.dp, bottom = 50.dp)
                .clickable {
                    navController.navigate("Login")
                }
        )


        var email by remember { mutableStateOf("") }
        // 이메일 입력란을 구현합니다.
        LoginTextField(
            modifier = Modifier,
            value = email,
            icon_name = "이메일",
            text = "이메일 주소를 입력하세요",
            onValueChange = {email = it},
            visibility = true
        ){
        }




        var id by remember { mutableStateOf("") }
        // 아이디
        LoginTextField(
            modifier = Modifier,
            value = id,
            icon_name = "아이디",
            text = "아이디를 입력하세요",
            onValueChange = {id = it},
            visibility = true
        ){}

        var password by remember { mutableStateOf("") }
        LoginTextField(
            modifier = Modifier,
            value = password,
            icon_name = "비밀번호",
            text = "비밀번호를 입력하세요",
            onValueChange = {password = it},
            visibility = false
        ){}

        var repassword by remember { mutableStateOf("") }
        // 비밀번호 재입력란
        LoginTextField(
            modifier = Modifier,
            value = repassword,
            icon_name = "비밀번호 재확인",
            text = "비밀번호를 다시 입력하세요",
            onValueChange = {repassword = it},
            visibility = false
        ){}





        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
        var popupMessage by remember { mutableStateOf("") }
        val context = LocalContext.current

        val registerResponse by loginViewModel.registerResponse.collectAsState()
        LaunchedEffect(registerResponse) {
            registerResponse?.let {
                Log.d("TAG", "SelfLoginScreen: $it")
                if (it.success) {
                    Log.d("TAG", "SelfSingUpScreen: success")
                    navController.navigate(Screen.Home.rout)
                } else {
                    Log.d("TAG", "SelfSingUpScreen: failed")
                    Toast.makeText(context, "아이디가 이미 존재합니다", Toast.LENGTH_SHORT).show()
                }
            }
        }

        Button(onClick = {
            when {
                id.length > 20 -> {
                    popupMessage = "아이디는 20자 이하이어야 합니다."
                    Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT ).show()
                }
                password.length > 20 -> {
                    popupMessage = "비밀번호는 20자 이하이어야 합니다."
                    Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT ).show()
                }
                !email.matches(emailPattern.toRegex()) -> {
                    popupMessage = "올바른 이메일 형식을 입력하세요."
                    Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT ).show()
                }
                password != repassword -> {
                    popupMessage = "비밀번호가 일치하지 않습니다."
                    Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT ).show()
                }
                else -> {
                    loginViewModel.register(id, password, repassword, email)
                }
            }
        }
        ,colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ,modifier = Modifier
                .wrapContentSize()
                .width(320.dp)
                .padding(top = 10.dp, start = 20.dp)
        ) {
            Text(text = "회원가입")
        }
    }
}