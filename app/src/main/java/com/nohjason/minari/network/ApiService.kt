package com.nohjason.myapplication.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.home.news.Test
import com.nohjason.myapplication.network.response.Term
import com.nohjason.myapplication.network.response.TermResponse
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
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
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxMzY2MzgsImV4cCI6MTcyNjIyMzAzOH0.kUqZInuvOuSvxmL_kRXSBoJ725F5URKufOy0L7QRWBM",
        @Path("level") level: Int
    ): QuestionResponse

//    @GET("/member/{memberId}/givePoint")
//    suspend fun postPoint(
//        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX0FETUlOIiwic3ViIjoidGVzdEBnbWFpbC5jb20iLCJpYXQiOjE3MjA2MjA3MTYsImV4cCI6MTcyMDcwNzExNn0.HB5DqAsVW82Gke00pFnMH8SY0SbMjyJhuY0GKKxcbbA",
//        @Path("memberId") memberId: String
//    ): PointResponse

    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxOTUxOTEsImV4cCI6MTcyNjI4MTU5MX0.sx13Li5n1lugQxUZjmQRJCZhZrsCRojIUqqIYuAss5A"
    ): ProfileResponse

    @GET("/likes/term")
    suspend fun getDiercTerm(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxOTUxOTEsImV4cCI6MTcyNjI4MTU5MX0.sx13Li5n1lugQxUZjmQRJCZhZrsCRojIUqqIYuAss5A"
    ): DirecTermResponse

    @GET("/likes/gpse")
    suspend fun getGpse(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxOTUxOTEsImV4cCI6MTcyNjI4MTU5MX0.sx13Li5n1lugQxUZjmQRJCZhZrsCRojIUqqIYuAss5A"
    ): DirecGpseResponse

    @GET("/likes/gps")
    suspend fun getGps(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxOTUxOTEsImV4cCI6MTcyNjI4MTU5MX0.sx13Li5n1lugQxUZjmQRJCZhZrsCRojIUqqIYuAss5A"
    ): DirecGpsResponse

    @GET("/likes/gp")
    suspend fun getGp(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxOTUxOTEsImV4cCI6MTcyNjI4MTU5MX0.sx13Li5n1lugQxUZjmQRJCZhZrsCRojIUqqIYuAss5A"
    ): DirecGpResponse
}