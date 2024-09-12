package com.nohjason.myapplication.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.screens.home.news.Test
import com.nohjason.minari.screens.profile.DireGpsResponse
import com.nohjason.minari.screens.profile.DirecGpResponse
import com.nohjason.minari.screens.profile.DirecGpseResponse
import com.nohjason.minari.screens.profile.DirecTermResponse
import com.nohjason.minari.screens.profile.ProfileResponse
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

    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxNDcyNDcsImV4cCI6MTcyNjIzMzY0N30.h2BUtVKRVApYDkj8xBnXErFI1I3QtQXLvlhP1BauLyY"
    ): ProfileResponse

    @GET("/likes/term")
    suspend fun getTerm(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYwNTk0MTUsImV4cCI6MTcyNjE0NTgxNX0.uJ62qskBw3CkJ7IhST9K1iawRDY6QYFWbQXVVGn_izw"
    ): DirecTermResponse

    @GET("/likes/gpse")
    suspend fun getGpse(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYwNTk0MTUsImV4cCI6MTcyNjE0NTgxNX0.uJ62qskBw3CkJ7IhST9K1iawRDY6QYFWbQXVVGn_izw"
    ): DirecGpseResponse

    @GET("/likes/gps")
    suspend fun getGps(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYwNTk0MTUsImV4cCI6MTcyNjE0NTgxNX0.uJ62qskBw3CkJ7IhST9K1iawRDY6QYFWbQXVVGn_izw"
    ): DireGpsResponse

    @GET("/likes/gp")
    suspend fun getGpe(
        @Header("Authorization") token: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYwNTk0MTUsImV4cCI6MTcyNjE0NTgxNX0.uJ62qskBw3CkJ7IhST9K1iawRDY6QYFWbQXVVGn_izw"
    ): DirecGpResponse
}