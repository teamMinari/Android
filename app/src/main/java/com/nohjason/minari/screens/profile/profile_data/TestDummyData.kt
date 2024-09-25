package com.nohjason.minari.screens.profile.profile_data

import com.nohjason.minari.screens.profile.directory.DirecGp
import com.nohjason.minari.screens.profile.directory.DirecGpResponse
import com.nohjason.minari.screens.profile.directory.DirecGps
import com.nohjason.minari.screens.profile.directory.DirecGpsResponse
import com.nohjason.minari.screens.profile.directory.DirecGpse
import com.nohjason.minari.screens.profile.directory.DirecGpseResponse
import com.nohjason.minari.screens.profile.directory.DirecTerm
import com.nohjason.minari.screens.profile.directory.DirecTermResponse

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
    val termStatusResponse = DirecTermResponse(
        status = 0,
        message = "Success",
        data = listOf(
            DirecTerm(
                termId = 1,
                termNm = "Inflation",
                termExplain = "The rate at which the general level of prices for goods and services is rising.",
                termDifficulty = TermDifficulty.LV_1
            ),
            DirecTerm(
                termId = 2,
                termNm = "Supply and Demand",
                termExplain = "The relationship between the quantity of a commodity that producers wish to sell at various prices and the quantity that consumers wish to buy.",
                termDifficulty = TermDifficulty.LV_2
            ),
            DirecTerm(
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
    val gpseStatusResponse = DirecGpseResponse(
        status = 0,
        message = "Success",
        data = listOf(
            DirecGpse(
                gpseId = 1,
                gpseName = "Gpse Example 1",
                gpseTime = 120,
                gpseLike = true
            ),
            DirecGpse(
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
    val gpsStatusResponse = DirecGpsResponse(
        status = 0,
        message = "Success",
        data = listOf(
            DirecGps(
                gpsId = 1,
                gpsContent = "Gps Content Example 1",
                gpsImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpsLike = true,
                gpsTpList = listOf("BEGINNER", "INTERMEDIATE")
            ),
            DirecGps(
                gpsId = 2,
                gpsContent = "Gps Content Example 2",
                gpsImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpsLike = false,
                gpsTpList = listOf("ADVANCED")
            )
        )
    )
}

// GpStatusResponse 더미 데이터
object DummyGpStatusResponse {
    val gpStatusResponse = DirecGpResponse(
        status = 0,
        message = "Success",
        data = listOf(
            DirecGp(
                gpId = 1,
                gpName = "Gp Example 1",
                gpImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpLike = true
            ),
            DirecGp(
                gpId = 2,
                gpName = "Gp Example 2",
                gpImg = "https://i.ibb.co/kJPp4Cv/image-34.png",
                gpLike = false
            )
        )
    )
}