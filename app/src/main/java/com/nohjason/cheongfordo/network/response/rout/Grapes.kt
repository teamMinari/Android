package com.nohjason.cheongfordo.network.response.rout

//data class GrapesAll(
//    val message: String,
//    val status: Int,
//    val data: List<Data>
//)
//
//data class Data(
//    val gpsId: Int,
//    val gpsName: String,
//    val gpsContent: String,
//    val gpsTime: Int,
//    val gpsLike: Boolean,
//    val gpTpList: List<String>,
//)

data class GrapesAll(
    val status: Int,
    val message: String,
    val data: List<GpsData>
)

data class GpsData(
    val gpsId: Int,
    val gpsName: String,
    val gpsContent: String,
    val gpsTime: Int,
    val gpsLike: Boolean,
    val gpsAgeGroup: String,
    val gpsWork: String
)


data class Grapes(
    val status: Int,
    val message: String,
    val data: GrapesData,
)

data class GrapesData (
    val gpsName: String,
    val gpsContent: String,
    val gpsTime: Int,
    val gpsLike: Boolean,
    val gpsImg: String,
    val gpCnt: Int,
    val gpCntMax: Int,
    val gpsAgeGroup: String,
    val gpsWork: String,
    val gpList: List<GrapeData>,
)

data class GrapeData (
    val gpImg: String,
    val gpId: Int,
    val gpNm: String,
    val gpExp: Int,
    val gpTm: Int,
    val gpLike: Boolean,
    val gpseCnt: Int,
    val gpseCntMax: Int
)

data class Grape(
    val status: Int,
    val message: String,
    val data: List<GrapeSeedLessData>,
)

data class GrapeSeedLessData(
    val gpseId: Int,
    val gpseNm: String,
    val gpseTm: Int,
    val gpseTF: Boolean,
)

data class GrapeSeed(
    val status: Int,
    val message: String,
    val data: GrapeSeedData,
)

data class GrapeSeedData(
    val gpseName: String,
    val gpseContent: String,
    val gpseTime: Int,
    val gpseTF: Boolean,
    val gpseLike: Boolean,
    val gpseExp: Int,
    val gpseQtId: Int,
    val gpseUrl: String,
    val termNmList: List<String>,
)

