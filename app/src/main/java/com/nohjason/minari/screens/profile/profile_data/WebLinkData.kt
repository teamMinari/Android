package com.nohjason.minari.screens.profile.profile_data

class WebLinkData {
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
            4 -> TitleAndUrl(
                title = "무역의 제왕",
                url = "https://i.ibb.co/5j7Qngk/image-4.png"
            )
            5 -> TitleAndUrl(
                title = "전설의 구매자",
                url = "https://i.ibb.co/whdxDgf/image-5.png"
            )
            6 -> TitleAndUrl(
                title = "명품 소비자",
                url = "https://i.ibb.co/YZXh8dF/image-6.png"
            )
            7 -> TitleAndUrl(
                title = "왕국의 보물",
                url = "https://i.ibb.co/fdKkjRJ/image-7.png"
            )
            8 -> TitleAndUrl(
                title = "소비의 대가",
                url = "https://i.ibb.co/cc4P8M4/image-8.png"
            )
            9 -> TitleAndUrl(
                title = "금강의 고객",
                url = "https://i.ibb.co/QkWg7Z5/image-9.png"
            )
            10 -> TitleAndUrl(
                title = "비즈니스 대왕",
                url = "https://i.ibb.co/9NpRB9y/image-10.png"
            )
            11 -> TitleAndUrl(
                title = "경험의 왕",
                url = "https://i.ibb.co/3fK6G4K/image-11.png"
            )
            12 -> TitleAndUrl(
                title = "상업의 제왕",
                url = "https://i.ibb.co/7Gp5bgK/image-12.png"
            )
            13 -> TitleAndUrl(
                title = "명예의 고객",
                url = "https://i.ibb.co/d2VTrkG/image-13.png"
            )
            14 -> TitleAndUrl(
                title = "현명한 소비자",
                url = "https://i.ibb.co/ZzyQsJP/image-14.png"
            )
            15 -> TitleAndUrl(
                title = "재정의 대가",
                url = "https://i.ibb.co/6FP15h4/image-15.png"
            )
            16 -> TitleAndUrl(
                title = "전문가 소비자",
                url = "https://i.ibb.co/JcpH8w7/image-16.png"
            )
            17 -> TitleAndUrl(
                title = "정복자의 고객",
                url = "https://i.ibb.co/kQ9M2YZ/image-17.png"
            )
            18 -> TitleAndUrl(
                title = "우수한 구매자",
                url = "https://i.ibb.co/BL4MLnL/image-18.png"
            )
            19 -> TitleAndUrl(
                title = "스마트 소비자",
                url = "https://i.ibb.co/Y2s8kz9/image-19.png"
            )
            20 -> TitleAndUrl(
                title = "가장 똑똑한",
                url = "https://i.ibb.co/1d6K3JY/image-20.png"
            )
            21 -> TitleAndUrl(
                title = "왕국의 수호자",
                url = "https://i.ibb.co/1v5pR8s/image-21.png"
            )
            22 -> TitleAndUrl(
                title = "소비의 장인",
                url = "https://i.ibb.co/Tcf7z7F/image-22.png"
            )
            23 -> TitleAndUrl(
                title = "계획의 대가",
                url = "https://i.ibb.co/nkjh4z4/image-23.png"
            )
            24 -> TitleAndUrl(
                title = "고객의 신",
                url = "https://i.ibb.co/2cBF9Zs/image-24.png"
            )
            25 -> TitleAndUrl(
                title = "전문가의 고객",
                url = "https://i.ibb.co/LYhXjH3/image-25.png"
            )
            26 -> TitleAndUrl(
                title = "패션의 왕",
                url = "https://i.ibb.co/bzL8vx2/image-26.png"
            )
            27 -> TitleAndUrl(
                title = "최고의 소비자",
                url = "https://i.ibb.co/zPb5vW5/image-27.png"
            )
            28 -> TitleAndUrl(
                title = "소비의 대명사",
                url = "https://i.ibb.co/X5xDWv5/image-28.png"
            )
            29 -> TitleAndUrl(
                title = "세계의 고객",
                url = "https://i.ibb.co/VH6Ln4D/image-29.png"
            )
            30 -> TitleAndUrl(
                title = "전설의 소비자",
                url = "https://i.ibb.co/8z1x6C4/image-30.png"
            )
            else -> TitleAndUrl(
                title = "기본 타이틀",
                url = "http://example.com/api/default"
            )
        }
    }
}
