package com.nohjason.cheongfordo.network.response


data class BookResponse (
    val success: Boolean = true,
    val status: String,
    val message: String,
    val data: List<Term>
)