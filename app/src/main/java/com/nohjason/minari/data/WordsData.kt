package com.nohjason.minari.data

val allWords: List<Word> =
    listOf(
        Word("이름", "뜻", 3),
        Word("이름1", "뜻1", 2),
        Word("이름2", "뜻2", 1),
        Word("이름3", "뜻3", 4),
    )

data class Word(
    val title: String,
    val value: String,
    val starCount: Int
)