package com.nohjason.minari.screens.rout

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.network.response.GetTerm
import com.nohjason.minari.network.response.rout.Grape
import com.nohjason.minari.network.response.rout.GrapeSeed
import com.nohjason.minari.network.response.rout.Grapes
import com.nohjason.minari.network.response.rout.GrapesAll
import com.nohjason.minari.screens.rout.response.LikesResponse
import com.nohjason.myapplication.network.RetrofitInstance.api
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.ResponseBody
import retrofit2.HttpException
import java.io.IOException
import androidx.compose.runtime.State
import com.nohjason.minari.network.response.GetSearchTerm
import com.nohjason.minari.network.response.GetTermData
import com.nohjason.myapplication.network.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GrapeViewModel: ViewModel() {
    private val _route = MutableStateFlow<GrapesAll?>(null) // 초기값은 null로 설정
    val route: StateFlow<GrapesAll?> = _route

    fun getAllGps(token: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAllGps(token = token)
                }
                if (response.isSuccessful) {
                    _route.value = response.body()
                    Log.d("TAG", "getAllGps: 전체 포도송이 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getAllGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getAllGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getAllGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getAllGps: 알 수 없는 오류", e)
            }
        }
    }

    private val _gpsDetail = MutableStateFlow<Grapes?>(null)
    val gpsDetail: StateFlow<Grapes?> = _gpsDetail

    fun getGps(token: String, gpsId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getGps(token = token, gpsId = gpsId)
                }
                if (response.isSuccessful) {
                    _gpsDetail.value = response.body()
                    Log.d("TAG", "getGps: 포도알 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getGps: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getGps: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getGps: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getGps: 알 수 없는 오류", e)
            }
        }
    }

    private val _grapeMap = MutableStateFlow<Map<Int, Grape>>(emptyMap())
    val allGpMap: StateFlow<Map<Int, Grape>> = _grapeMap

    fun getAllGrape(token: String, gpId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getAllGrape(token = token, gpId = gpId)
                }
                if (response.isSuccessful) {
                    response.body()?.let { grape ->
                        _grapeMap.value = _grapeMap.value.toMutableMap().apply {
                            put(gpId, grape)
                        }
                        Log.d("TAG", "getAllGrape: 포도씨 서버 통신 성공 - gpId: $gpId")
                    }
                } else {
                    Log.e("TAG", "getAllGrape: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                Log.e("TAG", "getAllGrape: 네트워크 오류", e)
            } catch (e: HttpException) {
                Log.e("TAG", "getAllGrape: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                Log.e("TAG", "getAllGrape: 알 수 없는 오류", e)
            }
        }
    }

    private val _gpse = MutableStateFlow<GrapeSeed?>(null)
    val gpse: StateFlow<GrapeSeed?> = _gpse

    fun getGpse(token: String, gpseId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getGpse(token = token, gpseId = gpseId)
                }
                if (response.isSuccessful) {
                    _gpse.value = response.body()
                    Log.d("TAG", "getGpse: 포도씨 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getGpse: 알 수 없는 오류", e)
            }
        }
    }

    private val _gp = MutableStateFlow<Grape?>(null)
    val gp: StateFlow<Grape?> = _gp

    fun getGp(token: String, gpId: Int) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getGp(token = token, gpId = gpId)
                }
                if (response.isSuccessful) {
                    _gp.value = response.body()
                    Log.d("TAG", "getGp: 포도알 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getGp: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getGp: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getGp: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getGp: 알 수 없는 오류", e)
            }
        }
    }

    private val _likes = MutableStateFlow<LikesResponse?>(null)
    val likes: StateFlow<LikesResponse?> = _likes

    fun likes(token: String, category: String, id: Int, termNm: String = "") {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.likes(token, category, id)
                }
                if (response.isSuccessful) {
                    _likes.value = response.body()
                    Log.d("TAG", "likesGpse: 좋아요 서버 통신 성공")
                    if (category == "TERM") {
                        // ?
                        getTerm(token, termNm)
                    } else if (category == "GRAPES") {
                        getAllGps(token = token)
                    } else if (category == "GRAPE") {
                        getGps(token, id)
                    } else if (category == "GRAPESEED") {
                        getGpse(token, id)
                    }
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "likesGpse: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "likesGpse: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "likesGpse: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "likesGpse: 알 수 없는 오류", e)
            }
        }
    }

    private val _getTerm = MutableStateFlow<GetTerm?>(null) // 초기값은 null로 설정
    val getTerm: StateFlow<GetTerm?> = _getTerm

    fun getTerm(token: String, termNm: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getTerm(token, termNm)
                }
                if (response.isSuccessful) {
                    _getTerm.value = response.body()
                    Log.d("TAG", "getTerm: 단일 용어 조회 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getTerm: 알 수 없는 오류", e)
            }
        }
    }

    private val _getSearchTerm = MutableStateFlow<GetSearchTerm?>(null) // 초기값은 null로 설정
    val getSearchTerm: StateFlow<GetSearchTerm?> = _getSearchTerm

    fun getSearchTerm(token: String, termNm: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getSearchTerm(token, termNm)
                }
                if (response.isSuccessful) {
                    _getSearchTerm.value = response.body()
                    Log.d("TAG", "getSearchTerm: 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getSearchTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getSearchTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getSearchTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getSearchTerm: 알 수 없는 오류", e)
            }
        }
    }

//    private val _getAllLikesTerm = MutableStateFlow<GetAllLikesTerm?>(null)
//    val getAllLikesTerm: StateFlow<GetAllLikesTerm?> = _getAllLikesTerm
//
//    fun getAllLikesTerm(token: String) {
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.getAllLikesTerm(token)
//                }
//                if (response.isSuccessful) {
//                    _getAllLikesTerm.value = response.body()
//                    Log.d("TAG", "getAllLikesTerm: 좋아요 서버 통신 성공")
//                } else {
//                    // 서버 응답 에러 처리
//                    Log.e("TAG", "getAllLikesTerm: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                // 네트워크 오류 처리
//                Log.e("TAG", "getAllLikesTerm: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                // HTTP 오류 처리
//                Log.e("TAG", "getAllLikesTerm: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                // 기타 예외 처리
//                Log.e("TAG", "getAllLikesTerm: 알 수 없는 오류", e)
//            }
//        }
//    }

    private val _getEasyTerm = MutableStateFlow<String?>(null) // String으로 수정
    val getEasyTerm: StateFlow<String?> = _getEasyTerm

    fun getEasyTerm(token: String, termNm: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    api.getEasyTerm(token, termNm)
                }
                if (response.isSuccessful) {
                    _getEasyTerm.value = response.body()?.string()  // ResponseBody를 문자열로 변환
                    Log.d("TAG", "getEasyTerm: 서버 통신 성공")
                } else {
                    // 서버 응답 에러 처리
                    Log.e("TAG", "getEasyTerm: 서버 응답 에러 - 코드: ${response.code()}")
                }
            } catch (e: IOException) {
                // 네트워크 오류 처리
                Log.e("TAG", "getEasyTerm: 네트워크 오류", e)
            } catch (e: HttpException) {
                // HTTP 오류 처리
                Log.e("TAG", "getEasyTerm: HTTP 오류 - 코드: ${e.code()}", e)
            } catch (e: Exception) {
                // 기타 예외 처리
                Log.e("TAG", "getEasyTerm: 알 수 없는 오류", e)
            }
        }
    }

//    private val _likesGpse = MutableStateFlow<LikesResponse?>(null)
//    val likesGpse: StateFlow<LikesResponse?> = _likesGpse
//
//    fun likes(token: String, category: String, id: Int) {
//        viewModelScope.launch {
//            try {
//                val response = withContext(Dispatchers.IO) {
//                    api.likes(token, category, id)
//                }
//                if (response.isSuccessful) {
//                    _likesGpse.value = response.body()
//                    Log.d("TAG", "likesGpse: 포도씨 좋아요 서버 통신 성공")
//                } else {
//                    // 서버 응답 에러 처리
//                    Log.e("TAG", "likesGpse: 서버 응답 에러 - 코드: ${response.code()}")
//                }
//            } catch (e: IOException) {
//                // 네트워크 오류 처리
//                Log.e("TAG", "likesGpse: 네트워크 오류", e)
//            } catch (e: HttpException) {
//                // HTTP 오류 처리
//                Log.e("TAG", "likesGpse: HTTP 오류 - 코드: ${e.code()}", e)
//            } catch (e: Exception) {
//                // 기타 예외 처리
//                Log.e("TAG", "likesGpse: 알 수 없는 오류", e)
//            }
//        }
//    }

}