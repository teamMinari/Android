package com.nohjason.minari.screens.quiz.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class QuizViewModel : ViewModel() {
    //데이터 처리 코드 로직 위주

    // PlayData를 관리하기 위한 상태(데이터 저장같은 느낌..?)
    var playData = mutableStateOf<PlayData?>(null)
        private set

    // PlayData 초기화 함수
    fun initializePlayData(data: PlayData) {
        playData.value = data
    }

    // 퀴즈를 다음으로 진행하는 함수 -- 변경 필요
    fun nextQuestion() {
        playData.value?.let { data ->
            val newQtNum = data.qtNum + 1
            if (newQtNum < data.qtList.size) {
                playData.value = data.copy(qtNum = newQtNum)
            }
        }
    }

    // 점수 업데이트 함수
    fun updatePoints(newPoints: Int) {
        playData.value?.let { data ->
            playData.value = data.copy(point = newPoints)
        }
    }

    // 정답 제출 함수
    fun submitAnswer(userAnswer: Boolean, correctAnswer:Boolean) {
        playData.value?.let { data ->
            val currentQuestion = data.qtList[data.qtNum]

            // 정답 여부 판별
            if (userAnswer == correctAnswer) {
                // 정답일 경우 점수를 1점 추가
                updatePoints(data.point + 1)
            }
        }
    }
}