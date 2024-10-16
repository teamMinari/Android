package com.nohjason.minari.screens.profile.directory_screen.direc_data

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.screens.profile.profile_data.ProfileResponse
import com.nohjason.myapplication.network.RetrofitInstance
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class DirecViewModel  : ViewModel() {

    private val _direcTermData = MutableStateFlow<DirecTermResponse?>(null) // 초기값은 null로 설정
    val direcTermData: StateFlow<DirecTermResponse?> = _direcTermData

    fun getDirecTerm(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getDiercTerm(token)
                }
                if (response.isSuccessful) {
                    _direcTermData.value = response.body()
                    Log.d("TAG", "getDirecTerm: 저장목록 단어 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getDirecTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getDirecTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getDirecTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecTerm: 알 수 없는 오류", e)
            }
        }
    }



    private val _direcGpseData = MutableStateFlow<DirecGpseResponse?>(null)
    val direcGpseData: StateFlow<DirecGpseResponse?> get() = _direcGpseData


    suspend fun getDirecGpse(token: String): DirecGpseResponse {
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDiercGpse(token)
                Log.d("TAG", "getDirecGpse: 저장목록 포도씨 서버 통신 성공")
                _direcGpseData.value = response
                response
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGpse: 저장목록 포도씨 오류", e)
                throw e
            }
        }
    }



    private val _direcGpsData = MutableStateFlow<DirecGpsResponse?>(null)
    val direcGpsData: StateFlow<DirecGpsResponse?> get() = _direcGpsData


    suspend fun getDirecGps(token: String): DirecGpsResponse {
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDiercGps(token)
                Log.d("TAG", "getDirecGps: 저장목록 포도알 서버 통신 성공")
                _direcGpsData.value = response
                response
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGps: 저장목록 포도알 오류", e)
                throw e
            }
        }
    }


    private val _gpDirecData = MutableStateFlow<DirecGpResponse?>(null)
    val direcGpData: StateFlow<DirecGpResponse?> get() = _gpDirecData


    suspend fun getDirecGp(token: String): DirecGpResponse {
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                val response = apiService.getDierctGp(token)
                Log.d("TAG", "getDirecGp: 저장목록 포도송이 서버 통신 성공")
                _gpDirecData.value = response
                response
            } catch (e: Exception) {
                Log.e("TAG", "getDirecGp: 저장목록 포도송이 오류", e)
                throw e
            }
        }
    }
}