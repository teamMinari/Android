package com.nohjason.minari.screens.quiz.data

data class UserExp(
    val status: Int,
    val message: String,
    val data: UserData
)

data class UserData(
    val idx: Int,
    val id: String,
    val totalExp: Int,
    val level: Int
)