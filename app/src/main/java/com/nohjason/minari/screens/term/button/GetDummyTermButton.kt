package com.nohjason.minari.screens.term.button

data class TermButton(
    val id: Int,
    val title: String,
)

fun GetDummyTermButton(): List<TermButton> {
    return listOf(
        TermButton(1, "전체"),
        TermButton(2, "금융"),
        TermButton(3, "증권"),
        TermButton(4, "부동산"),
        TermButton(5, "글로벌 경제"),
        TermButton(6, "산업/재계"),
    )
}