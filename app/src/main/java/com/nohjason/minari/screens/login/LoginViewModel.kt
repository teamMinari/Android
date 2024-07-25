package com.nohjason.minari.screens.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.screens.login.response.LoginRequest
import com.nohjason.minari.screens.login.response.LoginResponse
import com.nohjason.minari.screens.login.response.RegisterRequest
import com.nohjason.minari.screens.login.response.RegisterResponse
import com.nohjason.minari.screens.login.response.Token
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {
    private val _loginResponse = MutableStateFlow<LoginResponse?>(null)
    val loginResponse: StateFlow<LoginResponse?> = _loginResponse

    fun login(id: String, password: String) {
        viewModelScope.launch {
            try {
                val response = api.login(LoginRequest(id, password))
                _loginResponse.value = response
                Log.d("TAG", "login: 로그인 성공")
            } catch (e: Exception) {
                // 오류 처리
                Log.e("TAG", "login: $e", e)
                _loginResponse.value = LoginResponse(success = false, status = "error", message = "error", data = listOf<Token>(
                    Token("", "")
                ))
            }
        }
    }

    private val _registerResponse = MutableStateFlow<RegisterResponse?>(null)
    val registerResponse: StateFlow<RegisterResponse?> = _registerResponse

    fun register(id: String, password: String, confirmPassword: String, email: String) {
        viewModelScope.launch {
            try {
                val response = api.register(RegisterRequest(id, password, confirmPassword, email))
                _registerResponse.value = response
                Log.d("TAG", "register: 회원가입 성공")
            } catch (e: Exception) {
                Log.e("TAG", "register: $e")
                _registerResponse.value = RegisterResponse(
                    success = false,
                    status = "",
                    message = e.message.toString(),
                )
            }
        }
    }
}