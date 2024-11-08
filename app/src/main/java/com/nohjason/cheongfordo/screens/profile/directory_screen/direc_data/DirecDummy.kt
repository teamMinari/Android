package com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data

import com.nohjason.cheongfordo.screens.profile.profile_data.TermDifficulty

fun getDummyDirecTermResponse(): DirecTermResponse {
    return DirecTermResponse(
        status = 200,
        message = "Success",
        data = listOf(
            DirecTerm(
                termId = 1,
                termNm = "경제학 기초",
                termExplain = "경제학의 기본 개념 설명",
                termDifficulty = TermDifficulty.LV_1
            ),
            DirecTerm(
                termId = 2,
                termNm = "고급 경제학",
                termExplain = "심화 경제 개념 설명",
                termDifficulty = TermDifficulty.LV_3
            )
        )
    )
}

fun getDummyDirecGpseResponse(): DirecGpseResponse {
    return DirecGpseResponse(
        status = 200,
        message = "Success",
        data = listOf(
            DirecGpse(
                gpseId = 1,
                gpseName = "Gpse #1",
                gpseTime = 120,
                gpseLike = true
            ),
            DirecGpse(
                gpseId = 2,
                gpseName = "Gpse #2",
                gpseTime = 150,
                gpseLike = false
            )
        )
    )
}

//fun getDummyDirecGpsResponse(): DirecGpsResponse {
//    return DirecGpsResponse(
//        status = 200,
//        message = "Success",
//        data = listOf(
//            DirecGps(
//                gpsId = 1,
//                gpsContent = "GPS 내용 1",
//                gpsImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
//                gpsLike = true,
//                gpsTpList = listOf("항목1", "항목2")
//            ),
//            DirecGps(
//                gpsId = 2,
//                gpsContent = "GPS 내용 2",
//                gpsImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
//                gpsLike = false,
//                gpsTpList = listOf("항목3", "항목4")
//            )
//        )
//    )
//}

fun getDummyDirecGpResponse(): DirecGpResponse {
    return DirecGpResponse(
        status = 200,
        message = "Success",
        data = listOf(
            DirecGp(
                gpId = 1,
                gpName = "GP 이름 1",
                gpImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpLike = true
            ),
            DirecGp(
                gpId = 2,
                gpName = "GP 이름 2",
                gpImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpLike = false
            )
        )
    )
}
