package com.nohjason.minari.screens.quiz

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.Quize
import com.nohjason.minari.network.response.rout.GrapesAll
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class QuizeViewModel: ViewModel() {
    private val _quize = MutableStateFlow<Quize?>(null) // 초기값은 null로 설정
    val quize: StateFlow<Quize?> = _quize

    fun getQuize(token: String, quizeId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getQuize(token = token, questionIdx = quizeId)
                }
                if (response.isSuccessful) {
                    _quize.value = response.body()
                    Log.d("TAG", "getQuize: 퀴즈 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getQuize: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getQuize: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getQuize: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getQuize: 알 수 없는 오류", e)
            }
        }
    }
}