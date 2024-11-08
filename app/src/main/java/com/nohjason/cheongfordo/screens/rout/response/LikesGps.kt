package com.nohjason.cheongfordo.screens.rout.response

data class LikesResponse (
    val status: Int,
    val message: String,
)

data class LikesGps (
    val status: Int,
    val message: String,
    val data: List<Gps>
)

data class Gps (
    val gpsId: Int,
    val gpsContent: String,
    val gpsImg: String,
    val gpsLike: Boolean,
    val gpsTpList: List<String>
)