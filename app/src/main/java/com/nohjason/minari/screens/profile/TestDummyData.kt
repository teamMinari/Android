package com.nohjason.minari.screens.profile

// ProfileData 더미 데이터
object DummyProfileData {
    val profileData = ProfileResponse(
        idx = 1,
        id = "user123",
        email = "user123@example.com",
        vocaBook = "My Vocabulary Book",
        point = 1000,
        exp = 500,
        authority = "User",
        title = "Economist",
        level = 2,
        totalExp = 1200
    )
}

// TermStatusResponse와 관련된 더미 데이터
object DummyTermStatusResponse {
    val termStatusResponse = TermStatusResponse(
        status = 0,
        message = "Success",
        data = listOf(
            Term(
                termId = 1,
                termNm = "Inflation",
                termExplain = "The rate at which the general level of prices for goods and services is rising.",
                termDifficulty = TermDifficulty.LV_1
            ),
            Term(
                termId = 2,
                termNm = "Supply and Demand",
                termExplain = "The relationship between the quantity of a commodity that producers wish to sell at various prices and the quantity that consumers wish to buy.",
                termDifficulty = TermDifficulty.LV_2
            ),
            Term(
                termId = 3,
                termNm = "Opportunity Cost",
                termExplain = "The loss of potential gain from other alternatives when one alternative is chosen.",
                termDifficulty = TermDifficulty.LV_3
            )
        )
    )
}

// GpseStatusResponse 더미 데이터
object DummyGpseStatusResponse {
    val gpseStatusResponse = GpseStatusResponse(
        status = 0,
        message = "Success",
        data = listOf(
            Gpse(
                gpseId = 1,
                gpseName = "Gpse Example 1",
                gpseTime = 120,
                gpseLike = true
            ),
            Gpse(
                gpseId = 2,
                gpseName = "Gpse Example 2",
                gpseTime = 45,
                gpseLike = false
            )
        )
    )
}

// GpsStatusResponse 더미 데이터
object DummyGpsStatusResponse {
    val gpsStatusResponse = GpsStatusResponse(
        status = 0,
        message = "Success",
        data = listOf(
            Gps(
                gpsId = 1,
                gpsContent = "Gps Content Example 1",
                gpsImg = "http://example.com/image1.png",
                gpsLike = true,
                gpsTpList = listOf("BEGINNER", "INTERMEDIATE")
            ),
            Gps(
                gpsId = 2,
                gpsContent = "Gps Content Example 2",
                gpsImg = "http://example.com/image2.png",
                gpsLike = false,
                gpsTpList = listOf("ADVANCED")
            )
        )
    )
}

// GpStatusResponse 더미 데이터
object DummyGpStatusResponse {
    val gpStatusResponse = GpStatusResponse(
        status = 0,
        message = "Success",
        data = listOf(
            Gp(
                gpId = 1,
                gpName = "Gp Example 1",
                gpImg = "http://example.com/gp1.png",
                gpLike = true
            ),
            Gp(
                gpId = 2,
                gpName = "Gp Example 2",
                gpImg = "http://example.com/gp2.png",
                gpLike = false
            )
        )
    )
}