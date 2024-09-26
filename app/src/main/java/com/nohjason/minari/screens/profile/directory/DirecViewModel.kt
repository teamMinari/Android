package com.nohjason.minari.screens.profile.directory

import androidx.lifecycle.ViewModel
import com.nohjason.minari.screens.profile.DirecGpResponse
import com.nohjason.minari.screens.profile.DirecGpsResponse
import com.nohjason.minari.screens.profile.DirecGpseResponse
import com.nohjason.minari.screens.profile.DirecTermResponse
import com.nohjason.minari.screens.profile.ProfileResponse
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.myapplication.network.RetrofitInstance
import com.nohjason.myapplication.network.response.TermResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.withContext

class DirecViewModel  : ViewModel() {
    private val _termData = MutableStateFlow<DirecTermResponse?>(null)
    val termData: StateFlow<DirecTermResponse?> get() = _termData

    fun initializeTermData(data: DirecTermResponse) {
        _termData.value = data
    }
    suspend fun getDorecTerm(token: String): DirecTermResponse {
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



    private val _gpseData = MutableStateFlow<DirecGpseResponse?>(null)
    val gpseData: StateFlow<DirecGpseResponse?> get() = _gpseData

    fun initializeGpseData(data: DirecGpseResponse) {
        _gpseData.value = data
    }
    suspend fun getGpse(token: String): DirecGpseResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getGpse(token)
                println("Gpse임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }



    private val _gpsData = MutableStateFlow<DirecGpsResponse?>(null)
    val gpsData: StateFlow<DirecGpsResponse?> get() = _gpsData

    fun initializeGpsData(data: DirecGpsResponse) {
        _gpsData.value = data
    }
    suspend fun getGps(token: String): DirecGpsResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getGps(token)
                println("Gps임"+response)
                response // 서버 응답 반환
            } catch (e: Exception) {
                // 기타 예외 처리
                println("Error: ${e.message}")
                throw e // 필요에 따라 다시 던질 수 있음
            }
        }
    }


    private val _gpData = MutableStateFlow<DirecGpResponse?>(null)
    val gpData: StateFlow<DirecGpResponse?> get() = _gpData

    fun initializeGpData(data: DirecGpResponse) {
        _gpData.value = data
    }
    suspend fun getGp(token: String): DirecGpResponse {
        // Retrofit 인스턴스를 가져옴
        val apiService = RetrofitInstance.api

        return withContext(Dispatchers.IO) {
            try {
                // GET 요청을 보내고 응답을 받아옴
                val response = apiService.getGp(token)
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