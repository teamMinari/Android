package com.nohjason.minari.network.response

import com.nohjason.myapplication.network.response.Term

data class BookResponse (
    val success: Boolean = true,
    val status: String,
    val message: String,
    val data: List<Term>
)