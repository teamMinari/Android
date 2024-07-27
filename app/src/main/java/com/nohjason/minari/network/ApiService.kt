package com.nohjason.minari.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.login.response.LoginRequest
import com.nohjason.minari.screens.login.response.LoginResponse
import com.nohjason.minari.screens.login.response.RegisterRequest
import com.nohjason.minari.screens.login.response.RegisterResponse
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @GET("/terms/all")
    suspend fun getTerms(
        @Header("Authorization") token: String
    ): List<TermResponse>

    @GET("/terms")
    suspend fun getOneTerm(
        @Query("termNm") termNm: String,
        @Header("Authorization") token: String,
    ): Term

    @PATCH("/likes/toggle")
    suspend fun addDeleteTerm(
        @Query("word") word: String,
        @Header("Authorization") token: String
    ): AddDeleteTerm

    @GET("/likes/my")
    suspend fun getBookTerms(
        @Header("Authorization") token: String
    ): BookResponse

    // login
    @POST("/member/login")
    suspend fun login(
        @Body loginResponse: LoginRequest
    ): LoginResponse

    @POST("/member/register")
    suspend fun register(
        @Body registerResponse: RegisterRequest
    ): RegisterResponse
}