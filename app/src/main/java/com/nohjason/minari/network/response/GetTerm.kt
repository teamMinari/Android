package com.nohjason.minari.network.response

data class GetTerm(
    val status: Int,
    val message: String,
    val data: GetTermData,
)

data class GetSearchTerm(
    val status: Int,
    val message: String,
    val data: List<Term>,
)

data class GetAllLikesTerm(
    val status: Int,
    val message: String,
    val data: List<GetTermData>,
)

data class GetTermData(
    val termLike: Boolean,
    val termDifficulty: String,
    val termExplain: String,
    val termNm: String,
    val termId: Int
)