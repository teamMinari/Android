package com.nohjason.minari.screens.profile

class WebLink {
    // 레벨에 따라 URL을 반환하는 함수
    fun getUrlForLevel(level: String): String {
        return when (level) {
            "LV_1" -> "https://i.ibb.co/kJPp4Cv/image-34.png"
            "LV_2" -> "https://i.ibb.co/tHhbhsb/Frame-1717.png"
            "LV_3" -> "https://i.ibb.co/5LD6x2G/Frame-1717-1.png"
            else -> "http://example.com/api/default"
        }
    }

    fun getUrlIdGp(id: Int): String {
        return when (id) {
            2 -> "https://i.ibb.co/bb9jPfV/Ellipse-266-1.png"
            else -> "http://example.com/api/default"
        }
    }

    fun getUrlIdFps(id: Int): String {
        return when (id) {
            1 -> "https://i.ibb.co/Jc8BB5N/Group-1648-1.png"
            else -> "http://example.com/api/default"
        }
    }
}