package com.nohjason.minari.network

import com.nohjason.minari.network.response.AddDeleteTerm
import com.nohjason.minari.network.response.BookResponse
import com.nohjason.minari.network.response.GetAllLikesTerm
import com.nohjason.minari.network.response.GetTerm
import com.nohjason.minari.network.response.Quize
import com.nohjason.minari.network.response.rout.Grape
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.network.response.rout.Grapes
import com.nohjason.minari.network.response.rout.GrapesAll
import com.nohjason.minari.screens.login.response.LoginRequest
import com.nohjason.minari.screens.login.response.LoginResponse
import com.nohjason.minari.screens.login.response.RegisterRequest
import com.nohjason.minari.screens.login.response.RegisterResponse
import com.nohjason.minari.screens.profile.DirecGpResponse
import com.nohjason.minari.screens.profile.DirecGpsResponse
import com.nohjason.minari.screens.profile.DirecGpseResponse
import com.nohjason.minari.screens.profile.DirecTermResponse
import com.nohjason.minari.screens.rout.response.GetAllNews
import com.nohjason.minari.screens.rout.response.LikesResponse
import com.nohjason.minari.screens.profile.ProfileResponse
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
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjcwNzQyODAsImV4cCI6MTcyNzE2MDY4MH0._PEUQnz1VtDZdX_0KpiPVVso6uUeFPw6YuEMUxU8Stk",
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
        @Body loginRequest: LoginRequest
    ): Response<LoginResponse>

    @POST("/member/register")
    suspend fun register(
        @Body registerResponse: RegisterRequest
    ): RegisterResponse

    // 포도송이 전체 조회
    @GET("/gps")
    suspend fun getAllGps(
        @Header("Authorization") token: String
    ): Response<GrapesAll>

    // 포도알 조회
    @GET("/gps/{gpsId}")
    suspend fun getGps(
        @Header("Authorization") token: String,
        @Path("gpsId") gpsId: Int,
    ): Response<Grapes>

    // 포도씨 전체 조회
    @GET("/gp/{gpId}")
    suspend fun getAllGrape(
        @Header("Authorization") token: String,
        @Path("gpId") gpId: Int,
    ): Response<Grape>

    // 포도씨 조회
    @GET("/gpse/{gpseId}")
    suspend fun getGpse(
        @Header("Authorization") token: String,
        @Path("gpseId") gpseId: Int,
    ): Response<GrapeSeed>

    @GET("/questions/{questionIdx}")
    suspend fun getQuize(
        @Header("Authorization") token: String,
        @Path("questionIdx") questionIdx: Int,
    ): Response<Quize>

    // 좋아요
    @PATCH("/likes")
    suspend fun likes(
        @Header("Authorization") token: String,
        @Query("category") category: String,
        @Query("id") id: Int,
    ): Response<LikesResponse>

    // 뉴스 가져오기
    @GET("/news")
    suspend fun getAllNews(
        @Header("Authorization") token: String,
        @Query("category") category: String,
    ): Response<GetAllNews>

    // 단어 용어 가져오기
    @GET("/terms/name/{termNm}")
    suspend fun getTerm(
        @Header("Authorization") token: String,
        @Path("termNm") termNm: String
    ): Response<GetTerm>

    // 용어 좋아요 전체 가져오기
    @GET("/likes/term")
    suspend fun getAllLikesTerm(
        @Header("Authorization") token: String,
    ): Response<GetAllLikesTerm>

    @GET("/member/profile")
    suspend fun getProfile(
        @Header("Authorization") token: String
    ): Response<ProfileResponse>



    @GET("/questions/level/{level}")
    suspend fun getQuestion(
        @Header("Authorization") Authorization: String = "eyJhbGciOiJIUzI1NiJ9.eyJhdXRob3JpdHkiOiJST0xFX1VTRVIiLCJzdWIiOiJyaGRpZGRsNjY5MUBnbWFpbC5jb20iLCJpYXQiOjE3MjYxMzY2MzgsImV4cCI6MTcyNjIyMzAzOH0.kUqZInuvOuSvxmL_kRXSBoJ725F5URKufOy0L7QRWBM",
        @Path("level") level: Int
    ): QuestionResponse

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