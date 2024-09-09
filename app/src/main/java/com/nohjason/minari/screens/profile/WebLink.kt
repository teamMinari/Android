package com.nohjason.minari.screens.profile

class WebLink {
    // 레벨에 따라 URL을 반환하는 함수
    fun getUrlForLevel(level: String): String {
        return when (level) {
            "LV_1" -> "http://example.com/api/level1"
            "LV_2" -> "http://example.com/api/level2"
            "LV_3" -> "http://example.com/api/level3"
            else -> "http://example.com/api/default"
        }
    }
}