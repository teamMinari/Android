package com.nohjason.cheongfordo.screens.rout.response

data class GetAllNews(
    val status: Int,
    val message: String,
    val data: List<Data>,
)

data class Data(
    val title: String,
    val url: String,
    val company: String,
    val thumbnail: String,
    val uploadTime: String
)