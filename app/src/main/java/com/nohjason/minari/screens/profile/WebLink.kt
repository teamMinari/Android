package com.nohjason.minari.screens.profile

class WebLink {
    // 레벨에 따라 URL을 반환하는 함수
    fun getUrlForLevel(level: Int): String {
        return when (level) {
            1 -> "https://i.ibb.co/kJPp4Cv/image-34.png"
            2 -> "https://i.ibb.co/tHhbhsb/Frame-1717.png"
            3 -> "https://i.ibb.co/5LD6x2G/Frame-1717-1.png"
            else -> "http://example.com/api/default"
        }
    }

}