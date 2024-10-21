package com.nohjason.minari.screens.login.response

data class LoginRequest(
    val id: String,
    val password: String
)

data class LoginResponse(
    val status: Int,
    val message: String,
    val data: TokenData
)

data class TokenData(
    val accessToken: String,
    val refreshToken: String
)

data class RefreshToken(
    val refreshToken: String
)
