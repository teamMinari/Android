package com.nohjason.minari.network.response

import com.nohjason.myapplication.network.response.Term

data class AddDeleteTerm(
    val success: Boolean = true,
    val status: String,
    val message: String,
)
