package com.nohjason.cheongfordo.screens.profile.profile_data

class WebLinkData {
    // 레벨에 따라 타이틀과 URL을 반환하는 함수
    fun getTitleAndUrlForLevel(level: Int): TitleAndUrl {
        return when (level) {
            1 -> TitleAndUrl(
                title = "찐돌이",
                url = "https://i.ibb.co/tHhbhsb/Frame-1717.png"
            )
            2 -> TitleAndUrl(
                title = "노인정의 최고 소비자",
                url = "https://i.ibb.co/5LD6x2G/Frame-1717-1.png"
            )
            3 -> TitleAndUrl(
                title = "소비대왕",
                url = "https://i.ibb.co/kJPp4Cv/image-34.png"
            )
            4 -> TitleAndUrl(
                title = "무역의 제왕",
                url = "https://i.ibb.co/Pm0X4zf/image.png"
            )
            5 -> TitleAndUrl(
                title = "전설의 구매자",
                url = "https://i.ibb.co/n3J74LQ/image.png"
            )
            6 -> TitleAndUrl(
                title = "명품 소비자",
                url = "https://i.ibb.co/wh7MHtz/image.png"
            )
            7 -> TitleAndUrl(
                title = "왕국의 보물",
                url = "https://i.ibb.co/VWXh4QG/image.png"
            )
            8 -> TitleAndUrl(
                title = "소비의 대가",
                url = "https://i.ibb.co/Gx4VGhk/image.png"
            )
            9 -> TitleAndUrl(
                title = "금강의 고객",
                url = "https://i.ibb.co/FWMMrGR/image.png"
            )
            10 -> TitleAndUrl(
                title = "비즈니스 대왕",
                url = "https://i.ibb.co/Fz6fCd8/image.png"
            )
            11 -> TitleAndUrl(
                title = "경험의 왕",
                url = "https://i.ibb.co/QQZhGmf/image.webp"
            )
            12 -> TitleAndUrl(
                title = "스마트 소비자",
                url = "https://i.ibb.co/ww8GM2c/image.png"
            )
            13 -> TitleAndUrl(
                title = "명예의 고객",
                url = "https://i.ibb.co/G0n7phP/image.png"
            )
            else -> TitleAndUrl(
                title = "기본 타이틀",
                url = "http://example.com/api/default"
            )
        }
    }
}
