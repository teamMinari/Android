package com.nohjason.minari.network.response

data class AddDeleteTerm(
    val success: Boolean = true,
    val status: String,
    val message: String,
)
