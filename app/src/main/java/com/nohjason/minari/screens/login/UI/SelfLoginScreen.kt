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
import androidx.compose.runtime.saveable.rememberSaveable
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
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun SelfLoginScreen(
    navController: NavController,
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
                    navController.navigate("Signup")
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

        val context = LocalContext.current
        val preferencesManager = remember { PreferencesManager(context) }
        val loginResponse by loginViewModel.loginResponse.collectAsState()

        LaunchedEffect(loginResponse) {
            loginResponse?.let {
                Log.d("TAG", "SelfLoginScreen: $it")
                if (it.success) {
                    val token = it.data[0]
                    preferencesManager.saveData("accessToken", token.accessToken)
                    Log.d("TAG", "SelfLoginScreen: ${preferencesManager.getData("accessToken", "")}")
                    navController.navigate(Screen.Home.rout) {
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                    }
                } else {
                    Toast.makeText(context, "아이디 또는 비밀번호를 확인해보세요", Toast.LENGTH_SHORT).show()
                    Log.d("TAG", "Login Failed: ${it.message}")
                }
            }
        }

        Button(
            onClick = {
                loginViewModel.login(id = id, password = password)
            },
            colors = ButtonDefaults.buttonColors(containerColor = MinariBlue),
            modifier = Modifier
                .wrapContentSize()
                .width(320.dp)
                .padding(top = 95.dp, start = 60.dp)
        ) {
            Text(text = "로그인")
        }
    }
}