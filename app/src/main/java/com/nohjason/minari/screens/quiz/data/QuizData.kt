package com.nohjason.minari.screens.quiz.data
///questions/level/{level} - 문제 난이도 별 조회
//{
//    "status": 200,
//    "message": "질문 난이도 별 조회 성공!",
//    "data": [
//    {
//        "qtContents": "string",
//        "qtAnswer": true,
//        "qtCmt": "string",
//        "qtTip": "string",
//        "qtDifficulty": "LV_1"
//    }
//    ]
//}

data class QuestionResponse(
    val status: Int,
    val message: String,
    val data: List<QuestionData>
    //전체 어레이
)

data class QuestionData(
    val qtContents: String,
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtTip: String,
    val qtDifficulty: String
)



data class PlayData(
    val userCurrent: Int,
    val point: Int,
    val qtNum: Int,
    val qtList: List<QuestionData>
    //10개 뽑아서 넣은 후 인덱스로 조절
)

