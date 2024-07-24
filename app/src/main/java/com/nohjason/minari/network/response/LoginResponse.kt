package com.nohjason.minari.network.response

data class LoginRequest(
    val id: String,
    val password: String
)

data class Token(
    val accessToken: String,
    val refreshToken: String
)

data class LoginResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: List<Token>
)