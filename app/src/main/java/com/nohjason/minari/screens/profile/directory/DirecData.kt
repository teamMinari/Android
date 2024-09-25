package com.nohjason.minari.screens.profile.directory

import com.nohjason.minari.screens.profile.profile_data.TermDifficulty

data class DirecGpseResponse(
    val status: Int,
    val message: String,
    val data: List<DirecGpse>
)

data class DirecGpse(
    val gpseId: Int,
    val gpseName: String,
    val gpseTime: Int,
    val gpseLike: Boolean
)

data class DirecGpsResponse(
    val status: Int,
    val message: String,
    val data: List<DirecGps>
)

data class DirecGps(
    val gpsId: Int,
    val gpsContent: String,
    val gpsImg: String,
    val gpsLike: Boolean,
    val gpsTpList: List<String> // 문자열 리스트
)

data class DirecGpResponse(
    val status: Int,
    val message: String,
    val data: List<DirecGp>
)

data class DirecGp(
    val gpId: Int,
    val gpName: String,
    val gpImg: String,
    val gpLike: Boolean
)

data class DirecTermResponse(
    val status: Int,
    val message: String,
    val data: List<DirecTerm>
)

data class DirecTerm(
    val termId: Int,
    val termNm: String,
    val termExplain: String,
    val termDifficulty: TermDifficulty
)