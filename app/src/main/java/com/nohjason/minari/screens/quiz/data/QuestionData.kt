package com.nohjason.minari.screens.quiz.data

import android.graphics.Point

data class Question (
    var id: Int,
    var question: String, //문제
    var answer: String, //답
    var Commentary: String, //해설
)

data class TemporaryPoint(
    var name: String,
    var point: Int,
    var replies: Int, //사용자의 답변 제출 횟수
    var user_answer: String,
    var Correct: Int
)

object QuestionData {
    fun getQuestion(): ArrayList<Question>{
        val queList: ArrayList<Question> = arrayListOf(
            Question(
                1,
                "은행예금이나 보험이 모두 저축을 목적으로 한다는 점에서는 똑같다.1",
                "X",
                "은행예금은 저축을 목적으로 하지만, 보험은 만약에 생길지도 모르는 사고위험에 대한 보장을 목적으로 합니다.",
            ),
            Question(
                2,
                "은행예금이나 보험이 모두 저축을 목적으로 한다는 점에서는 똑같다.2",
                "O",
                "은행예금은 저축을 목적으로 하지만, 보험은 만약에 생길지도 모르는 사고위험에 대한 보장을 목적으로 합니다.",
            ),
            Question(
                3,
                "은행예금이나 보험이 모두 저축을 목적으로 한다는 점에서는 똑같다.3",
                "O",
                "은행예금은 저축을 목적으로 하지만, 보험은 만약에 생길지도 모르는 사고위험에 대한 보장을 목적으로 합니다.",
            )
        )
        return queList
    }
}

object Temporary_pointData {
    fun getPoint(): ArrayList<TemporaryPoint> {
        val pointList: ArrayList<TemporaryPoint> = arrayListOf(
            TemporaryPoint(
                "나영",
                1444,
                0,
                "",
                0
            )
        )
        return pointList
    }
}