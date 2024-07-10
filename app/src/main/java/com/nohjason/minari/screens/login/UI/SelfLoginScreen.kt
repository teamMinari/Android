package com.nohjason.minari.screens.login.UI

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
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
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.login.Data.LoginRequest
import com.nohjason.minari.screens.login.Data.UserResponse
import com.nohjason.minari.ui.theme.MinariBlue
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
//    navController: NavController
){
    val poppinsFamily = FontFamily(
        Font(R.font.poppins_semibold, FontWeight.SemiBold),
        Font(R.font.poppins_medium, FontWeight.Medium),
        Font(R.font.poppins_regular),
        Font(R.font.poppins_bold, FontWeight.Bold),)

    Column (
        modifier = Modifier.fillMaxWidth().fillMaxHeight()
    ){
        Image(painterResource(id = R.drawable.grape), contentDescription = null,
            modifier = Modifier.width(21.dp).height(34.dp))
        Text(text = "로그인", style = androidx.compose.ui.text.TextStyle(
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
            modifier = Modifier.clickable {
//                navController.navigate("Singup")
                println("클릭됨")
            }
        )

        var id by remember { mutableStateOf("") }
        OutlinedTextField(
            value = id,
            onValueChange = { id = it },
            label = {
                Row {
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = "아이디",
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
                .background(Color.White)
                    ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Email,
                imeAction = ImeAction.Next
            ),
        )


        var password by rememberSaveable { mutableStateOf("") }
        var isVisiblePassword by remember { mutableStateOf(false) }

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("비밀번호를 입력하세요") },
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
            ,
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password, // 비밀번호 형식의 키보드를 사용
            ),
            singleLine = true,
            isError = false, // 에러 상태는 여기서는 false로 설정합니다
            visualTransformation = if (isVisiblePassword) VisualTransformation.None else PasswordVisualTransformation(),
            trailingIcon = {
                IconButton(onClick = { isVisiblePassword = !isVisiblePassword }) {
                    if (isVisiblePassword) {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_24),
                            contentDescription = "비밀번호 숨기기"
                        )
                    } else {
                        Icon(
                            painter = painterResource(id = R.drawable.baseline_visibility_off_24),
                            contentDescription = "비밀번호 보이기"
                        )
                    }
                }
            }
        )


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

        val scope = rememberCoroutineScope()
        var responseMessage by remember { mutableStateOf("") }
        Button(onClick = {
            scope.launch {
                val result = loginUser(id = id, password = password)
                result?.let {
                    if (result.success){
//                        navController.navigate(BottomBarScreen.Home.rout)
                    }
                }
            }
        }) {
            Text(text = "로그인")
        }
    }
}









@Preview(showBackground = true)
@Composable
fun PreLogin(){
    SelfLoginScreen()
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
            println("서버 요청 성공")
            return response.body() // response body 반환
        } else {
            println("서버 요청 실패: ${response.code()}, ${response.message()}")
        }
    } catch (e: Exception) {
        println("오류: ${e.message}")
        // 네트워크 오류 등 예외 처리
        e.printStackTrace()
    }

    return null
}

interface LoginPOST {
    @POST("http://cheong.baekjoon.kr/member/login") // POST 요청을 보낼 엔드포인트 URL을 지정합니다.
    suspend fun login(@Body request: LoginRequest): Response<UserResponse>
}



