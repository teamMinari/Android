package com.nohjason.minari.preferences

import android.util.Base64
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.screens.login.response.RefreshToken
import kotlinx.coroutines.runBlocking
import okhttp3.Interceptor
import okhttp3.Response
import org.json.JSONObject

class AuthInterceptor (
    private val tokenProvider: TokenProvider,
    private val apiService: ApiService
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // 현재 액세스 토큰을 가져옴
        val token = tokenProvider.getToken()

        // 유효한 토큰이 있는 경우 Authorization 헤더 추가
        if (token != null && isTokenValid(token)) {
            requestBuilder.addHeader("Authorization", "Bearer $token")
        }

        var response = chain.proceed(requestBuilder.build())

        // 액세스 토큰이 만료된 경우 401 에러 처리
        if (response.code == 401) {
            val refreshToken = tokenProvider.getRefreshToken() ?: return response

            // 리프레시 토큰으로 새로운 액세스 토큰 요청
            val refreshTokenBody = RefreshToken(refreshToken)
            val newToken = runBlocking {
                val refreshResponse = apiService.refresh(refreshTokenBody)
                if (refreshResponse.isSuccessful) {
                    refreshResponse.body()
                } else {
                    null
                }
            }
            // 새로운 액세스 토큰이 있는 경우 저장하고 요청 재시도
            if (newToken != null) {
                tokenProvider.saveToken(newToken)
                requestBuilder.addHeader("Authorization", "Bearer $newToken")
                response.close() // 이전 응답 닫기
                response = chain.proceed(requestBuilder.build()) // 재요청
            }
        }

        return response // 최종 응답 반환
    }

    private fun isTokenValid(token: String): Boolean {
        return try {
            // JWT 토큰의 페이로드를 Base64로 디코딩하여 만료일 확인
            val parts = token.split(".")
            if (parts.size != 3) return false

            val payloadJson = String(Base64.decode(parts[1], Base64.DEFAULT))
            val jsonObject = JSONObject(payloadJson)
            val exp = jsonObject.getLong("exp")
            val currentTime = System.currentTimeMillis() / 1000

            exp > currentTime // 만료일이 현재 시간보다 나중인지 확인
        } catch (e: Exception) {
            false // 파싱 오류 발생 시 false 반환
        }
    }
}