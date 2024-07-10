package com.nohjason.myapplication.network

import com.nohjason.minari.network.response.BookResponse
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.http.GET
import retrofit2.http.Header
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

    @GET("/terms/all")
    suspend fun getTerms(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoiam9tYmlAZHVjay5jb20iLCJpYXQiOjE3MjA1NzY3OTEsImV4cCI6MTcyMDY2MzE5MX0.RC6qwMPwj7-LvGAAn7Bm1DfJr7F9Mnwz6SlrDDWlNxA'",
    ): List<TermResponse>

    @GET("/terms")
    suspend fun getOneTerm(
        @Query("termNm") termNm: String,
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoiam9tYmlAZHVjay5jb20iLCJpYXQiOjE3MjA1NzY3OTEsImV4cCI6MTcyMDY2MzE5MX0.RC6qwMPwj7-LvGAAn7Bm1DfJr7F9Mnwz6SlrDDWlNxA'",
    ): Term

//    @PATCH("/likes/toggle")
//    suspend fun addWord(
//        @Query("word") word: String,
//        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoiam9tYmlAZHVjay5jb20iLCJpYXQiOjE3MjA1NzY3OTEsImV4cCI6MTcyMDY2MzE5MX0.RC6qwMPwj7-LvGAAn7Bm1DfJr7F9Mnwz6SlrDDWlNxA'",
//    ): Book

    @GET("/likes/my")
    suspend fun getBookTerms(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoiam9tYmlAZHVjay5jb20iLCJpYXQiOjE3MjA1NzY3OTEsImV4cCI6MTcyMDY2MzE5MX0.RC6qwMPwj7-LvGAAn7Bm1DfJr7F9Mnwz6SlrDDWlNxA'"
    ): BookResponse
}