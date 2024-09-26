package com.nohjason.minari.network.response

data class Quize(
    val getTermData: GetTermData,
    val message: String,
    val status: Int
)

data class Data(
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtContents: String,
    val qtDifficulty: String,
    val qtTip: String
)