package com.nohjason.minari.screens.profile.profile_data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException


class ProfileViewModel : ViewModel() {
    private val _profileData = MutableStateFlow<ProfileResponse?>(null) // 초기값은 null로 설정
    val profileData: StateFlow<ProfileResponse?> = _profileData

    fun getProfile(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getProfile(token)
                }
                if (response.isSuccessful) {
                    _profileData.value = response.body()
                    Log.d("TAG", "getProflie: 전체 포도송이 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getAllGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: Exception) {
                // 네트워크 오류 처리
                Log.e("TAG", "getAllGps: 알 수 없는 오류", e)
            }
        }
    }
}


