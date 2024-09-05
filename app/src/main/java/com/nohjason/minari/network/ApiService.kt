package com.nohjason.myapplication.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.home.news.Test
import com.nohjason.minari.screens.login.Data.LoginRequest
import com.nohjason.minari.screens.login.Data.UserResponse
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.Response
import retrofit2.http.Body
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

    @GET("/terms/all")
    suspend fun getTerms(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA",
    ): List<TermResponse>

    @GET("/terms")
    suspend fun getOneTerm(
        @Query("termNm") termNm: String,
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA",
    ): Term

    @PATCH("/likes/toggle")
    suspend fun addDeleteTerm(
        @Query("word") word: String,
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA",
    ): AddDeleteTerm

    @GET("/likes/my")
    suspend fun getBookTerms(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA"
    ): BookResponse



    @GET("/questions/level/{level}")
    suspend fun getQuestion(
//        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA",
        @Path("level") level: String
    ): QuestionResponse
}