package com.nohjason.minari.screens.quiz.data

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.StateFlow


class QuizViewModel : ViewModel() {
    //코드 로직 위주

    // PlayData를 관리하기 위한 상태(데이터 저장같은 느낌..?)
    var playData = mutableStateOf<PlayData?>(null)
        private set

    // PlayData 초기화 함수
    fun initializePlayData(data: PlayData) {
        playData.value = data
    }

    // 퀴즈를 다음으로 진행하는 함수
    fun nextQuestion() {
        playData.value?.let { data ->
            val newQtNum = data.qtNum + 1
            if (newQtNum < data.qtList.size) {
                playData.value = data.copy(qtNum = newQtNum) // Num 올리기
            } else {
                // 모든 문제를 다 푼 경우
                // 서버 연결 후 포인트 전달
                // 예를 들어: playData.value = data.copy(qtNum = data.qtList.size) // 리스트의 끝을 표시
            }
        }
    }

    // 포인트 업데이트 함수
    fun updatePoints(newPoints: Int) {
        playData.value?.let { data ->
            playData.value = data.copy(point = newPoints)
        }
    }

    // 정답수 업데이트 함수
    fun updateCorrectAnswer(newCorrect: Int) {
        playData.value?.let { data ->
            playData.value = data.copy(userCurrent = newCorrect)
        }
    }

    // 정답 제출 함수
    fun submitAnswer(answer: Boolean) {
        playData.value?.let { data ->
            val qtNum = data.qtNum
            val correct = data.qtList[qtNum].qtAnswer

            if (answer == correct) {
                val newPoints = playData.value?.point?.plus(1) ?: 0
                val newcorrectAnswer = playData.value?.userCurrent?.plus(1) ?: 0
                updatePoints(newPoints)
                updateCorrectAnswer(newcorrectAnswer)
            }
        }
    }
}


