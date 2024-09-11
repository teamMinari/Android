package com.nohjason.minari.screens.profile

data class ProfileResponse(
    val idx: Int,
    val id: String,
    val email: String,
    val vocaBook: String,
    val point: Int,
    val exp: Int,
    val authority: String,
    val title: String,
    val level: Int,
    val totalExp: Int
)

data class LikeList(
    val name: List<String>
)





data class TermStatusResponse(
    val status: Int,
    val message: String,
    val data: List<Term>
)

data class Term(
    val termId: Int,
    val termNm: String,
    val termExplain: String,
    val termDifficulty: TermDifficulty
)

enum class TermDifficulty {
    LV_1,
    LV_2,
    LV_3
}



data class GpseStatusResponse(
    val status: Int,
    val message: String,
    val data: List<Gpse>
)


data class Gpse(
    val gpseId: Int,
    val gpseName: String,
    val gpseTime: Int,
    val gpseLike: Boolean
)

data class GpsStatusResponse(
    val status: Int,
    val message: String,
    val data: List<Gps>
)

data class Gps(
    val gpsId: Int,
    val gpsContent: String,
    val gpsImg: String,
    val gpsLike: Boolean,
    val gpsTpList: List<String> // 문자열 리스트
)




data class GpStatusResponse(
    val status: Int,
    val message: String,
    val data: List<Gp>
)

data class Gp(
    val gpId: Int,
    val gpName: String,
    val gpImg: String,
    val gpLike: Boolean
)
