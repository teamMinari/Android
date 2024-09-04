package com.nohjason.minari.screens.quiz

import com.nohjason.minari.screens.quiz.data.QuestionData
import com.nohjason.minari.screens.quiz.data.QuestionResponse

data class QuestionResponse(
    val status: Int,
    val message: String,
    val data: List<QuestionData>
)

data class QuestionData(
    val qtContents: String,
    val qtAnswer: Boolean,
    val qtCmt: String,
    val qtTip: String,
    val qtDifficulty: String
)

val dummyQtList = listOf(
    QuestionData(
        qtContents = "한국의 수도는 어디인가요?",
        qtAnswer = true,
        qtCmt = "서울은 대한민국의 수도입니다.",
        qtTip = "한강이 흐르는 도시를 생각해보세요.",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한국의 국기는 무궁화입니다.",
        qtAnswer = false,
        qtCmt = "한국의 국기는 태극기입니다.",
        qtTip = "태극의 상징이 들어간 깃발을 떠올려보세요.",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한글은 세종대왕이 창제한 문자이다.",
        qtAnswer = true,
        qtCmt = "세종대왕이 훈민정음을 창제하였습니다.",
        qtTip = "한국의 대표적인 왕을 떠올려보세요.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "대한민국의 독립 기념일은 8월 15일이다.",
        qtAnswer = true,
        qtCmt = "8월 15일은 광복절로 대한민국의 독립을 기념하는 날입니다.",
        qtTip = "광복절은 어떤 날인지 생각해보세요.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "한국의 대표적인 전통 음식은 초밥이다.",
        qtAnswer = false,
        qtCmt = "한국의 대표적인 전통 음식은 김치입니다.",
        qtTip = "매운맛이 특징인 한국의 음식은 무엇일까요?",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한라산은 한국의 최고봉이다.",
        qtAnswer = true,
        qtCmt = "한라산은 제주도에 위치한 한국의 최고봉입니다.",
        qtTip = "제주도의 상징적인 산을 떠올려보세요.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "한국은 동아시아에 위치한 국가입니다.",
        qtAnswer = true,
        qtCmt = "한국은 동아시아에 위치해 있으며, 한반도에 자리잡고 있습니다.",
        qtTip = "한반도는 어느 지역에 속할까요?",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한국의 전통 의상은 기모노입니다.",
        qtAnswer = false,
        qtCmt = "한국의 전통 의상은 한복입니다.",
        qtTip = "전통 의상은 일본과 다릅니다.",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한국의 대표적인 전통 음악은 판소리이다.",
        qtAnswer = true,
        qtCmt = "판소리는 한국의 전통 음악 중 하나로, 서사적인 이야기를 노래로 표현합니다.",
        qtTip = "이야기를 노래로 표현하는 전통 음악입니다.",
        qtDifficulty = "LV_3"
    ),
    QuestionData(
        qtContents = "서울은 대한민국에서 가장 큰 도시이다.",
        qtAnswer = true,
        qtCmt = "서울은 대한민국의 수도이자 가장 큰 도시입니다.",
        qtTip = "한국의 수도이자 가장 인구가 많은 도시를 생각해보세요.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "한국의 대표적인 전통 놀이 중 하나는 윷놀이이다.",
        qtAnswer = true,
        qtCmt = "윷놀이는 한국의 전통 놀이로, 명절에 자주 즐깁니다.",
        qtTip = "명절에 가족들이 모여 즐기는 전통 놀이입니다.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "백두산은 남한에서 가장 높은 산이다.",
        qtAnswer = false,
        qtCmt = "백두산은 북한과 중국의 경계에 있는 산입니다.",
        qtTip = "백두산은 남한이 아닌 북한에 위치해 있습니다.",
        qtDifficulty = "LV_3"
    ),
    QuestionData(
        qtContents = "한국의 대표적인 전통 춤은 부채춤이다.",
        qtAnswer = true,
        qtCmt = "부채춤은 한국의 전통 춤으로, 화려한 부채를 사용하는 것이 특징입니다.",
        qtTip = "부채를 활용한 전통 춤을 떠올려보세요.",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "삼국시대는 고구려, 백제, 신라로 이루어져 있다.",
        qtAnswer = true,
        qtCmt = "삼국시대는 고구려, 백제, 신라 세 나라가 공존하던 시기입니다.",
        qtTip = "한국의 역사에서 세 나라가 공존하던 시기를 생각해보세요.",
        qtDifficulty = "LV_3"
    ),
    QuestionData(
        qtContents = "한국의 현대사에서 1988년에 개최된 올림픽은 서울 올림픽이다.",
        qtAnswer = true,
        qtCmt = "1988년에 서울에서 하계 올림픽이 개최되었습니다.",
        qtTip = "1988년에 대한민국에서 열린 국제적인 스포츠 행사는 무엇일까요?",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "한국의 국화는 장미이다.",
        qtAnswer = false,
        qtCmt = "한국의 국화는 무궁화입니다.",
        qtTip = "무궁화는 한국의 상징적인 꽃입니다.",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한국의 대표적인 전통 문양은 십장생이다.",
        qtAnswer = true,
        qtCmt = "십장생은 장수를 상징하는 10가지 물건을 묘사한 전통 문양입니다.",
        qtTip = "장수를 상징하는 전통 문양입니다.",
        qtDifficulty = "LV_3"
    ),
    QuestionData(
        qtContents = "대한민국의 수도는 부산이다.",
        qtAnswer = false,
        qtCmt = "대한민국의 수도는 서울입니다.",
        qtTip = "한국의 수도는 서울입니다.",
        qtDifficulty = "LV_1"
    ),
    QuestionData(
        qtContents = "한국의 교육 제도는 초등학교, 중학교, 고등학교 순으로 이루어져 있다.",
        qtAnswer = true,
        qtCmt = "한국의 교육 제도는 초등학교 6년, 중학교 3년, 고등학교 3년으로 구성됩니다.",
        qtTip = "한국의 교육 시스템은 몇 단계로 구성되어 있나요?",
        qtDifficulty = "LV_2"
    ),
    QuestionData(
        qtContents = "한국은 세계에서 가장 긴 역사를 가진 국가 중 하나이다.",
        qtAnswer = true,
        qtCmt = "한국은 고조선부터 이어진 긴 역사를 가진 국가입니다.",
        qtTip = "한국의 역사는 몇 천 년 이상 이어져 왔습니다.",
        qtDifficulty = "LV_3"
    )
)

val dummyResponse = QuestionResponse(
    status = 200,
    message = "질문 난이도 별 조회 성공!",
    data = dummyQtList
)

