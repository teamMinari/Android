package com.nohjason.minari.preferences

import android.content.Context
import android.content.SharedPreferences

class TokenProvider(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("app_prefs", Context.MODE_PRIVATE)

    // 액세스 토큰 저장
    fun saveToken(token: String) {
        sharedPreferences.edit().putString("token", token).apply()
    }

    // 액세스 토큰 가져오기
    fun getToken(): String? {
        return sharedPreferences.getString("token", null)
    }

    // 리프레시 토큰 저장
    fun saveRefreshToken(refreshToken: String) {
        sharedPreferences.edit().putString("refresh_token", refreshToken).apply()
    }

    // 리프레시 토큰 가져오기
    fun getRefreshToken(): String? {
        return sharedPreferences.getString("refresh_token", null)
    }

    // 토큰 삭제
    fun clearToken() {
        sharedPreferences.edit().remove("token").apply()
        sharedPreferences.edit().remove("refresh_token").apply()
    }
}