package com.nohjason.minari.screens.profile.directory_screen.direc_data

import android.util.Log
import androidx.lifecycle.ViewModel
import com.nohjason.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class DirecViewModel  : ViewModel() {
    private val _direcTermData = MutableStateFlow<DirecTermResponse?>(null)
    val direcTermData: StateFlow<DirecTermResponse?> get() = _direcTermData


    suspend fun getDirecTerm(token: String): DirecTermResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getDiercTerm(token)
                Log.d("TAG", "getDirecTerm: 저장목록 용어 서버 통신 성공")
                response // 서버 응답 반환
            } catch (e: Exception) {
                Log.e("TAG", "getDirecTerm: 저장목록 용어 오류", e)
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }



    private val _direcGpseData = MutableStateFlow<DirecGpseResponse?>(null)
    val direcGpseData: StateFlow<DirecGpseResponse?> get() = _direcGpseData


    suspend fun getDirecGpse(token: String): DirecGpseResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getDiercGpse(token)
                Log.d("TAG", "getDirecGpse: 저장목록 포도씨 서버 통신 성공")
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGpse: 저장목록 포도씨 오류", e)
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }



    private val _direcGpsData = MutableStateFlow<DirecGpsResponse?>(null)
    val direcGpsData: StateFlow<DirecGpsResponse?> get() = _direcGpsData


    suspend fun getDirecGps(token: String): DirecGpsResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getDiercGps(token)
                Log.d("TAG", "getDirecGps: 저장목록 포도알 서버 통신 성공")
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGps: 저장목록 포도알 오류", e)
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }


    private val _gpDirecData = MutableStateFlow<DirecGpResponse?>(null)
    val direcGpData: StateFlow<DirecGpResponse?> get() = _gpDirecData


    suspend fun getDirecGp(token: String): DirecGpResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getDierctGp(token)
                Log.d("TAG", "getDirecGp: 저장목록 포도송이 서버 통신 성공")
                println("Gp임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGp: 저장목록 포도송이 오류", e)
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }
}