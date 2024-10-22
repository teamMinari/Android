package com.nohjason.minari.screens.quiz.data

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.screens.profile.profile_data.LogOutResponse
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
import kotlin.random.Random

class QuizViewModel : ViewModel() {

    private val _questionData = MutableStateFlow<QuestionResponse?>(null) // 초기값은 null로 설정
    val questionData: StateFlow<QuestionResponse?> = _questionData

    fun getQuestion(level: Int, token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getQuestion(token, level)
                }
                if (response.isSuccessful) {
                    _questionData.value = response.body()
                    Log.d("QuizView", "퀴즈 데이터 확인: ${questionData.value}")
                    Log.d("TAG", "getQuiz: 퀴즈 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getQuiz: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getQuiz: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getQuiz: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getQuiz: 알 수 없는 오류", e)
            }
        }
    }


    private val _pointData = MutableStateFlow<PointResponse?>(null) // 초기값은 null로 설정

    fun postPoint(token: String, point: PointRequest) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.postPoint(token=token, pointRequest = point)
                }
                if (response.isSuccessful) {
                    println("responsePoint"+response)
                    println("sendPoint"+point)
                    _pointData.value = response.body()
                    Log.d("TAG", "postPoint: 포인트 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "postPoint: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "postPoint: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "postPoint: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "postPoint: 알 수 없는 오류", e)
            }
        }
    }


    // PlayData를 관리하기 위한 상태
    private val _playData = MutableStateFlow<PlayData?>(null)
    val playData: StateFlow<PlayData?> = _playData


    // PlayData 초기화 함수
    fun initializePlayData(data: PlayData) {
        _playData.value = data
    }

    // 퀴즈를 다음으로 진행하는 함수
    fun nextQuestion() {
        _playData.value?.let { data ->
            val newQtNum = data.qtNum + 1
            if (newQtNum < data.qtList.size) {
                _playData.value = data.copy(qtNum = newQtNum)
            }
        }
    }

    // 점수 업데이트 함수
    fun updatePoints(newPoints: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(point = newPoints)
        }
    }

    fun updateCurrent(newCurrent: Int) {
        _playData.value?.let { data ->
            _playData.value = data.copy(userCurrent = newCurrent)
        }
    }

    // 정답 제출 함수
    fun submitAnswer(userAnswer: Boolean, correctAnswer: Boolean) {
        _playData.value?.let { data ->
            var addPoint = 0
            if(data.qtLevel == 1){
                addPoint = Random.nextInt(30, 41)
            } else if(data.qtLevel == 2){
                addPoint = Random.nextInt(41, 81)
            } else{
                addPoint = Random.nextInt(81, 101)
            }

            if (userAnswer == correctAnswer) {
                // 정답일 경우 점수를 1점 추가
                updatePoints(data.point + addPoint)
                updateCurrent(data.userCurrent + 1)
            }
        }
    }



}


