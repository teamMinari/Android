package com.nohjason.minari.screens.quiz.data

data class QuestionResponse(
    val status: Int, //200
    val message: String, //질문 난이도 별 조회 성공!"
    val data: List<QuestionData>
    //전체 어레이
)

data class QuestionData(
    //리스폰스 데이터 구성
    val qtContents: String,
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtTip: String,
    val qtDifficulty: QuizLavel
)

data class PointResponse(
    val status: Int,
    val message: String
)

data class PlayData(
    //UI의 모델 클래스
    val userCurrent: Int, //정답수
    val point: Int,
    val qtNum: Int,
    val qtList: List<QuestionData>
    //10개 뽑아서 넣은 후 인덱스로 조절
)


data class UserPoint(
    val status: Int,
    val message: String,
    val data: UserData
)

data class UserData(
    val idx: Int,
    val id: String,
    val totalExp: Int,
    val level: Int
)

