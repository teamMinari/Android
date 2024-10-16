package com.nohjason.minari.screens.login

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.response.LoginRequest
import com.nohjason.minari.screens.login.response.LoginResponse
import com.nohjason.minari.screens.login.response.RegisterRequest
import com.nohjason.minari.screens.login.response.SignUpError
import com.nohjason.minari.screens.login.response.SignUpResponse
import com.nohjason.myapplication.network.RetrofitInstance
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class LoginViewModel : ViewModel() {
    private val _loginRequest = MutableStateFlow<LoginResponse?>(null)
    val loginRequest: StateFlow<LoginResponse?> = _loginRequest

    fun login(id: String, password: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.login(LoginRequest(id, password))
                }
                if (response.isSuccessful) {
                    _loginRequest.value = response.body()
                    Log.d("TAG", "login: 로그인 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "login: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "login: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "login: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "login: 알 수 없는 오류", e)
            }
        }
    }

    private val _signUpResult = MutableStateFlow<String?>(null)
    val signUpResult: StateFlow<String?> get() = _signUpResult
    private val _isLoading = MutableStateFlow(false) // 로딩 상태 변수 추가
    val isLoading: StateFlow<Boolean> get() = _isLoading

    fun signUp(id: String, password: String, confirmPassword: String, email: String) {
        _isLoading.value = true
        val request = RegisterRequest(id, password, confirmPassword, email)
        api.signUp(request).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                _isLoading.value = false // 요청 완료 후 로딩 상태 false로 설정
                if (response.isSuccessful) {
                    response.body()?.let { signUpResponse ->
                        if (signUpResponse.status == 0) {
                            _signUpResult.value = signUpResponse.message
                        } else {
                            _signUpResult.value = "${signUpResponse.message}"
                        }
                    }
                } else {
                    response.errorBody()?.let { errorBody ->
                        val errorResponse = Gson().fromJson(errorBody.string(), SignUpError::class.java)
                        _signUpResult.value = "${errorResponse.message}"
                    }
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                _isLoading.value = false // 요청 실패 시 로딩 상태 false로 설정
                _signUpResult.value = "Network Error: ${t.message}"
            }
        })
    }

//    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
//    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse
//
//    fun register(id: String, password: String, confirmPassword: String, email: String) {
//        viewModelScope.launch {
//            try {
//                val response = api.register(RegisterRequest(id, password, confirmPassword, email))
//                _registerResponse.value = response
//                Log.d("TAG", "register: 회원가입 성공")
//            } catch (e: Exception) {
//                Log.e("TAG", "register: $e")
//                _registerResponse.value = RegisterResponse(
//                    success = false,
//                    status = "",
//                    message = e.message.toString(),
//                )
//            }
//        }
//    }
}