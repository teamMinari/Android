package com.nohjason.minari.network.response

data class HotNewsResponse(
    val `data`: List<NewsData>,
    val message: String,
    val status: Int
)

data class NewsData(
    val company: String,
    val thumbnail: String,
    val title: String,
    val uploadTime: Any,
    val url: String
)