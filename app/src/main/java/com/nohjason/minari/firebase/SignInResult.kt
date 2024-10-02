package com.nohjason.minari.firebase

data class SignInResult(
    val data: com.nohjason.minari.firebase.UserData?,
    val errorMessage: String?
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePicture: String?
)
