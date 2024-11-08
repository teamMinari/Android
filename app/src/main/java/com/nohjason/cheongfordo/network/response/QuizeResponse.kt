package com.nohjason.cheongfordo.network.response

data class Quize(
    val data: QuizData,
    val message: String,
    val status: Int
)

data class QuizData(
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtContents: String,
    val qtDifficulty: String,
    val qtTip: String
)