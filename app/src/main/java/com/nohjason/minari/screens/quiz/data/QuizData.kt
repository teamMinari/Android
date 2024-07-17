package com.nohjason.minari.screens.quiz.data

data class QuizRequest(
    val qtContents: String,
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtDifficulty: String
)

data class QuizResponses(
    //퀴즈 데이터 전송 시 오는 값
    val success: Boolean,
    val status: String,
    val message: String,
    val data: List<Any>? = null
)

//유저 경험치랑 포인트는 memberID로 가져와야함