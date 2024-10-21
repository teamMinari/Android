package com.nohjason.myapplication.network

import android.content.Context
import com.nohjason.minari.network.ApiService
import com.nohjason.minari.preferences.AuthInterceptor
import com.nohjason.minari.preferences.TokenProvider
import com.nohjason.minari.preferences.getPreferences
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

object RetrofitInstance{
    private const val BASE_URL = "https://cheongfordo.kr/"  // 서버의 베이스 URL로 변경

    private lateinit var tokenProvider: TokenProvider
    private lateinit var authInterceptor: AuthInterceptor
    private lateinit var okHttpClient: OkHttpClient

    fun initialize(context: Context) {
        tokenProvider = TokenProvider(context)
        authInterceptor = AuthInterceptor(tokenProvider)

        okHttpClient = OkHttpClient.Builder()
            .addInterceptor(authInterceptor)
            .build()
    }

    val api: ApiService by lazy {
        Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }

}
