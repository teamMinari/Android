package com.nohjason.minari.screens.login.UI

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.login.Data.LoginResponse
import com.nohjason.minari.screens.login.Data.SinguUpRequest
import com.nohjason.minari.screens.login.Data.SinguUpResponse
import com.nohjason.minari.ui.theme.MinariBlue
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

@Composable
fun SelfSingUpScreen(
//    navController: NavController
){
    Column {

        Text(text = "회원가입")
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
            modifier = Modifier.clickable {
//                navController.navigate("Login")
            }
        )


        var email by remember { mutableStateOf("") }
        // 이메일 입력란을 구현합니다.
        OutlinedTextField(
            value = email,
            onValueChange = { email = it },
            label = { Text("이메일") }, // 입력란에 표시될 라벨
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email, // 이메일 형식의 키보드를 사용
                imeAction = ImeAction.Next // 다음 입력란으로 이동하는 액션 설정
            )
        )

        var id by remember { mutableStateOf("") }
        // 아이디
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = { Text("아이디") }, // 입력란에 표시될 라벨
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next // 다음 입력란으로 이동하는 액션 설정
            )
        )

        var password by remember { mutableStateOf("") }
        Row {
            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("비밀번호") }, // 입력란에 표시될 라벨
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp)
                ,
                keyboardOptions = KeyboardOptions(
                    keyboardType = KeyboardType.Password, // 이메일 형식의 키보드를 사용
                    imeAction = ImeAction.Next // 다음 입력란으로 이동하는 액션 설정
                ),
                visualTransformation = PasswordVisualTransformation(),
//                trailingIcon = {
//                    val icon = if (isVisiblePassword) {
//                        //눈 뜸
//                    } else {
//                        //눈 끔
//                    }
//                    IconButton(onClick = { isVisiblePassword = !isVisiblePassword })
//                    { Icon(imageVector = icon, contentDescription = null,) }
//                    VisualTransformation = if (isVisiblePassword) {
//                        VisualTransformation.None
//                    } else {
//                        PasswordVisualTransformation()
//                    }
//                },
            )
        }

        var repassword by remember { mutableStateOf("") }
        // 닉네임 입력란을 구현합니다.
        OutlinedTextField(
            value = repassword,
            onValueChange = { repassword = it },
            visualTransformation = PasswordVisualTransformation(),
            label = { Text("비밀번호 재확인") }, // 입력란에 표시될 라벨
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, // 이메일 형식의 키보드를 사용
                imeAction = ImeAction.Next // 다음 입력란으로 이동하는 액션 설정
            )
        )

        val scope = rememberCoroutineScope()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
//        var showPopup by remember { mutableStateOf(false) }
        var popupMessage by remember { mutableStateOf("") }
        val context = LocalContext.current

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
                    scope.launch {
                        val result = SingUPUser(id = id, password = password, confirmPassword = repassword, email = email)
                        result?.let {
                            // navController.navigate(BottomBarScreen.Home.rout)
                        }
                    }
                }
            }
        }) {
            Text(text = "회원가입")
        }
    }
}






@Preview(showBackground = true)
@Composable
fun PreSing(){
    SelfSingUpScreen()
}

suspend fun SingUPUser(id: String, password: String, confirmPassword: String, email: String): LoginResponse? {
    try {
        // Retrofit 인스턴스 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("http://cheong.baekjoon.kr/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // ApiService 인터페이스 구현체 생성
        val apiService = retrofit.create(SingUpPOST::class.java)



        // 서버로 요청 보내기
        val SingupRequest = SinguUpRequest(id, password, confirmPassword, email)
        val response = apiService.login(SingupRequest)

        // 서버 응답 처리
        if (response.isSuccessful) {
            println("서버 요청 성공: ${response.code()}, ${response.body()}, ${response.message()}")
            val SingUpResponse = response.body() //response값 받음
            println(SingUpResponse)

        } else {
            // 서버 요청 실패 처리
            println("서버 요청 실패: ${response.code()}, ${response.message()}")
        }
    } catch (e: Exception) {
        println("오류: ${e}, ${e.message}")
        e.printStackTrace()
    }

    return null
}

interface SingUpPOST {
    @POST("http://cheong.baekjoon.kr/member/register") // POST 요청을 보낼 엔드포인트 URL을 지정합니다.
    suspend fun login(@Body request: SinguUpRequest): Response<SinguUpResponse>
}