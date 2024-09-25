package com.nohjason.minari.screens.profile.profile_data

data class ProfileResponse(
    val idx: Int,
    val id: String,
    val email: String,
    val vocaBook: String,
    val point: Int,
    val exp: Int,
    val authority: String,
    val title: String,
    val level: Int,
    val totalExp: Int
)

data class TitleAndUrl(
    val title: String,
    val url: String
)

data class LikeList(
    val name: List<String>
)


enum class TermDifficulty {
    LV_1,
    LV_2,
    LV_3
}
