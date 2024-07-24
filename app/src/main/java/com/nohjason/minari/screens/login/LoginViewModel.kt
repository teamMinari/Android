package com.nohjason.minari.screens.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.LoginRequest
import com.nohjason.minari.network.response.LoginResponse
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
            } catch (e: Exception) {
                // 오류 처리
            }
        }
    }
}