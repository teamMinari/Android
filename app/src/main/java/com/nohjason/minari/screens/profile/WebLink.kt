package com.nohjason.minari.screens.profile

class WebLink {
    // 레벨에 따라 타이틀과 URL을 반환하는 함수
    fun getTitleAndUrlForLevel(level: Int): TitleAndUrl {
        return when (level) {
            1 -> TitleAndUrl(
                title = "찐돌이",
                url = "https://i.ibb.co/kJPp4Cv/image-34.png"
            )
            2 -> TitleAndUrl(
                title = "노인정의 최고 소비자",
                url = "https://i.ibb.co/tHhbhsb/Frame-1717.png"
            )
            3 -> TitleAndUrl(
                title = "소비대왕",
                url = "https://i.ibb.co/5LD6x2G/Frame-1717-1.png"
            )
            else -> TitleAndUrl(
                title = "기본 타이틀",
                url = "http://example.com/api/default"
            )
        }
    }
}