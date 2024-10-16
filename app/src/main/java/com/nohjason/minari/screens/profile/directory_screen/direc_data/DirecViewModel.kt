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


    private val _direcGpseData = MutableStateFlow<DirecGpseResponse?>(null) // 초기값은 null로 설정
    val direcGpseData: StateFlow<DirecGpseResponse?> = _direcGpseData

    fun getDirecGpse(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getDiercGpse(token)
                }
                if (response.isSuccessful) {
                    _direcGpseData.value = response.body()
                    Log.d("TAG", "getDirecGpse: 저장목록 포도씨 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getDirecGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getDirecGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getDirecGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGpse: 알 수 없는 오류", e)
            }
        }
    }



    private val _direcGpsData = MutableStateFlow<DirecGpsResponse?>(null) // 초기값은 null로 설정
    val direcGpsData: StateFlow<DirecGpsResponse?> = _direcGpsData

    fun getDirecGps(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getDiercGps(token)
                }
                if (response.isSuccessful) {
                    _direcGpsData.value = response.body()
                    Log.d("TAG", "getDirecGps: 저장목록 포도송이 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getDirecGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getDirecGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getDirecGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGps: 알 수 없는 오류", e)
            }
        }
    }


    private val _direcGpData = MutableStateFlow<DirecGpResponse?>(null) // 초기값은 null로 설정
    val direcGpData: StateFlow<DirecGpResponse?> = _direcGpData

    fun getDirecGp(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getDierctGp(token)
                }
                if (response.isSuccessful) {
                    _direcGpData.value = response.body()
                    Log.d("TAG", "getDirecGp: 저장목록 포도알 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getDirecGp: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getDirecGp: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getDirecGp: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getDirecGp: 알 수 없는 오류", e)
            }
        }
    }
}