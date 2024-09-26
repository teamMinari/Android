package com.nohjason.minari.screens.login.response

data class RegisterRequest(
    val id: String,
    val password: String,
    val confirmPassword: String,
    val email: String
)

data class RegisterResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: String? = null
)