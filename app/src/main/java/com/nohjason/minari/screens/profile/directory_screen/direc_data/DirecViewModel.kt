package com.nohjason.minari.screens.profile.directory_screen.direc_data

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
                println("용어"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
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
                println("Gpse임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
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
                println("Gps임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
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
                println("Gp임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }
}