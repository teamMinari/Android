package com.nohjason.minari.screens.profile.my_dictionary.model

import com.nohjason.myapplication.network.response.Term

data class DictionaryModel(
    val termCategory: String,
    val termDifficulty: String,
    val termExplain: String,
    val termNm: String,
    val isChecked: Boolean
)

fun Term.toModel() =
    DictionaryModel(
        termCategory = termCategory,
        termDifficulty = termDifficulty,
        termExplain = termExplain,
        termNm = termNm,
        isChecked = false,
    )