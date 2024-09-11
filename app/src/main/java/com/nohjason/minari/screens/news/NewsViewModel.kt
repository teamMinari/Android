package com.nohjason.minari.screens.news

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.screens.rout.response.GetAllNews
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class NewsViewModel: ViewModel() {
    private val _getAllNews = MutableStateFlow<GetAllNews?>(null)
    val getAllNews: StateFlow<GetAllNews?> = _getAllNews

    fun getAllNews(token: String, category: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    Log.d("TAG", "getAllNews: $token")
                    api.getAllNews(token, category)
                }
                if (response.isSuccessful) {
                    _getAllNews.value = response.body()
                    Log.d("TAG", "getAllNews: 모든 뉴스 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getAllNews: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getAllNews: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getAllNews: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getAllNews: 알 수 없는 오류", e)
            }
        }
    }
}