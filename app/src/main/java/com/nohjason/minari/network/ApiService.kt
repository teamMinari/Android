package com.nohjason.myapplication.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.home.news.Test
import com.nohjason.minari.screens.profile.DirecGpResponse
import com.nohjason.minari.screens.profile.DirecGpsResponse
import com.nohjason.minari.screens.profile.DirecGpseResponse
import com.nohjason.minari.screens.profile.DirecTermResponse
import com.nohjason.minari.screens.profile.ProfileResponse
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
//    @POST("routine")
//    suspend fun createRoutine(@Body routine: Routine)
//
//    @GET("routine") // 엔드포인트는 실제 API URL에 맞게 변경
//    suspend fun getRoutine(): ApiResponseRoutine
//
//    @DELETE("routine/{id}")
//    suspend fun deleteRoutine(@Path("id") id: Int): Response<Void>
//
//    @PATCH("routine/{id}")
//    suspend fun updateRoutine(
//        @Path("id") id: Int,
//        @Body routine: Routine
//    ): Response<Routine>
//
//    @PATCH("routine/set/{id}")
//    suspend fun updateSetRoutine(
//        @Path("id") id: Int,
//        @Body routine: Routine
//    ): Response<Routine>
//
//
//    @POST("task")
//    suspend fun createTask(@Body task: Task)
//
//    @GET("/task/{date}")
//    suspend fun getTasksDate(@Path("date") date: String): Response<ApiResponseTask>
//
//    @DELETE("/task/del/{id}")
//    suspend fun deleteTask(@Path("id") id: Int): Response<Void>
//
//    @PATCH("/task/edit/{id}")
//    suspend fun updateTask(
//        @Path("id") id: Int,
//        @Body task: Task
//    ): Response<Void>
//

    @GET("/questions/level/{level}")
    suspend fun getQuestion(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
        @Path("level") level: Int
    ): QuestionResponse

    @GET("/terms/all")
    suspend fun getTerms(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): List<TermResponse>

    @GET("/terms")
    suspend fun getOneTerm(
        @Query("termNm") termNm: String,
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): Term

    @PATCH("/likes/toggle")
    suspend fun addDeleteTerm(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
        @Query("word") word: String,
    ): AddDeleteTerm

    @GET("/likes/my")
    suspend fun getBookTerms(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): BookResponse

    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): ProfileResponse

    @GET("/likes/term")
    suspend fun getDiercTerm(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): DirecTermResponse

    @GET("/likes/gpse")
    suspend fun getGpse(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): DirecGpseResponse

    @GET("/likes/gps")
    suspend fun getGps(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): DirecGpsResponse

    @GET("/likes/gp")
    suspend fun getGp(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
    ): DirecGpResponse
}