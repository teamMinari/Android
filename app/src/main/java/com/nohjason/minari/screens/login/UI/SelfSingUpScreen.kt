package com.nohjason.minari.screens.login.UI

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
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
import com.nohjason.minari.screens.login.Data.SinguUpRequest
import com.nohjason.minari.screens.login.Data.UserResponse
import com.nohjason.minari.screens.login.LoginTextField
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariBlue
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

@Composable
fun SelfSingUpScreen(
    navController: NavController
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
            visibility = false
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

        var seconVisiblePassword by remember { mutableStateOf(false) }
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





        val scope = rememberCoroutineScope()
        val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+"
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
                            if(result.success){
                                navController.navigate(BottomBarScreen.Home.rout)
                            } else{
                                popupMessage = "다른 아이디나 비번으로 시도해주세요."
                                Toast.makeText(context, popupMessage, Toast.LENGTH_SHORT ).show()
                            }
                        }
                    }
                }
            }
        }
        ,colors = ButtonDefaults.buttonColors(containerColor = MinariBlue)
        ,modifier = Modifier
                .wrapContentSize()
                .width(320.dp)
                .padding(top = 95.dp, start = 20.dp)
        ) {
            Text(text = "회원가입")
        }
    }
}






//@Preview(showBackground = true)
//@Composable
//fun PreSing(){
//    SelfSingUpScreen()
//}

suspend fun SingUPUser(id: String, password: String, confirmPassword: String, email: String): UserResponse? {
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
    suspend fun login(@Body request: SinguUpRequest): Response<UserResponse>
}