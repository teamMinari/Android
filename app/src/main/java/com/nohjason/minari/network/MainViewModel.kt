package com.nohjason.myapplication.network

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.FinishLearn
import com.nohjason.minari.network.response.GetAllTermsResponse
import com.nohjason.minari.network.response.Term
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class MainViewModel : ViewModel() {

    private val _alltermsresponse = MutableStateFlow<GetAllTermsResponse?>(null)
    val getAllTerms: StateFlow<GetAllTermsResponse?> = _alltermsresponse

    fun getAllTerms(token: String, page: Int, size: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAlTerms(token, page, size)
                }
                if (response.isSuccessful) {
                    _alltermsresponse.value = response.body()
                    Log.d("TAG", "login: 로그인 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "login: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "login: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "login: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "login: 알 수 없는 오류", e)
            }
        }
    }

    private val _finishLearn = MutableStateFlow<FinishLearn?>(null)
    val finishLearn: StateFlow<FinishLearn?> = _finishLearn

    fun finishLearn(token: String, category: String, id: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.finishLearn(token, category, id)
                }
                if (response.isSuccessful) {
                    _finishLearn.value = response.body()
                    Log.d("TAG", "finishLearn: 학습 완료")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "finishLearn: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "finishLearn: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "finishLearn: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "finishLearn: 알 수 없는 오류", e)
            }
        }
    }
}