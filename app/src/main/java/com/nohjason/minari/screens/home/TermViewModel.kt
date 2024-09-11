package com.nohjason.minari.screens.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.GetTerm
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class TermViewModel : ViewModel() {

    // 단일 용어 조회
    private val _getTerm = MutableStateFlow<GetTerm?>(null) // 초기값은 null로 설정
    val getTerm: StateFlow<GetTerm?> = _getTerm

    fun getTerm(token: String, termNm: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getTerm(token, termNm)
                }
                if (response.isSuccessful) {
                    _getTerm.value = response.body()
                    Log.d("TAG", "getTerm: 단일 용어 조회 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getTerm: 알 수 없는 오류", e)
            }
        }
    }
}