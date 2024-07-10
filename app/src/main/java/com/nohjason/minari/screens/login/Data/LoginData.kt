package com.nohjason.minari.screens.login.Data

data class LoginResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: List<Any>? = null
)

data class LoginRequest(
    val id: String,
    val password: String
)


data class SinguUpResponse(
    val success: Boolean,
    val status: String,
    val message: String,
    val data: List<Any>? = null
)

data class SinguUpRequest(
    val id: String,
    val password: String,
    val confirmPassword: String,
    val email: String
)