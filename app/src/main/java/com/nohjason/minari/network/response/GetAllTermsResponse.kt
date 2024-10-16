package com.nohjason.minari.network.response

data class GetAllTermsResponse(
    val `data`: List<Term>,
    val message: String,
    val status: Int
)