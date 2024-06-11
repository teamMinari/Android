package com.nohjason.minari.screens.term

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.data.button.DummyButton1
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.screens.term.button.GetDummyTermButton
import com.nohjason.minari.screens.term.button.TermButton
import com.nohjason.minari.screens.term.button.TermButtonViewModel
import com.nohjason.minari.screens.term.card.TermCard
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun Term(
    viewModel: TermButtonViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController
) {
    var text by remember { mutableStateOf("") }
    Column {
        Box(
            modifier = Modifier
                .background(MinariWhite)
                .fillMaxSize()
        ) {
            Column {
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(0.dp, 0.dp, 10.dp, 10.dp))
                        .fillMaxWidth()
                        .background(Color.White)
                        .padding(vertical = 15.dp)
                ) {
                    MinariTextField(value = text, onValueChange = { text = it }) {}
                }
                LazyRow(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(GetDummyTermButton()) { index ->
                        val initialState = if (index.title == "전체") false else true
                        TermButton(viewModel = viewModel, title = index.title, initialState = initialState)
                    }
                }

                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                        .background(Color.White),
                ) {
<<<<<<< HEAD
                    itemsIndexed(items = allWords) { index, item ->
                        TermCard(
                            title = item.title,
                            value = item.value,
                            starCount = item.starCount,
                            dummyTermSimilarButton = item.dummyTermSimilarButton,
                            navController = navController
                        )
                        if (index != allWords.size-1) { // 마지막 항목이 아닌 경우에만 Divider 추가
                            MinariLine()
                        }
                    }
=======
//                    itemsIndexed(items = GetDummyTermCard()) { index, item ->
//                        TermCard(item.title, item.detail, item.starCount, item.dummyTermSimilarButton)
//                        if (index != GetDummyTermCard().size-1) { // 마지막 항목이 아닌 경우에만 Divider 추가
//                            MinariLine()
//                        }
//                    }
>>>>>>> feature/term
                }
            }
        }
    }
}

<<<<<<< HEAD
=======
//data class DummyTermCard(
//    val title: String,
//    val detail: String,
//    val starCount: Int,
//    val dummyTermSimilarButton: List<DummyTermSimilarButton>
//)
//
//fun GetDummyTermCard(): List<DummyTermCard> {
//    return listOf(
//        DummyTermCard(
//            "통화승수",
//            "중앙은행이 공급한 본원통화는 예금은행의 신용창출과정을 통해 이의 수 배에 달하는 통화를 시중에 유통하게 한다. 통화승수(money multiplier)는 본원통화 한 단위가 이의 몇 배에 달하는 통화를 창출하였는가를 나타내주는 지표로서 통화 총량을 본원통화로 나누어 산출한다. 통화승수는 현금통화 비율과 지급준비율에 의하여 결정되는데 현금통화 비율은 단기적으로는 안정적이라 할 수 있으며 지급준비율은 중앙은행에 의하여 정책적 으로 결정된다. M(통화) = RB(본원통화) × k(통화승수) k = 1/{c+(1-c)r} (단 c는 현금통화 비율, r은 지급준비율). 참고로 2022년 6월말 현재 우리나라의 본원통화 잔액(말잔기준) 은 283조원이고, M1, M2, Lf, L의 잔액은 각각 1,401조원, 3,703조원, 5,029조원, 6,436조원 으로 통화 및 유동성지표의 승수는 각각 4.95배, 13.08배, 17.77배, 22.74배를 기록하고 있다. 한편 동 승수의 장기추세를 보면 우리나라의 경우 대략 2008년 글로벌 금융위기 이후 각 통화 및 유동성 지표의 승수가 장기적으로 낮아지는 모습을 보이고 있는데 M2, Lf, L 같은 광의 지표의 승수 하락폭이 상대적으로 크게 ",
//            1,
//            DummyButton1()
//        ),
//        DummyTermCard(
//            "가계처가능소득",
//            "가계처분가능소득(PDI; Personal Disposable Income)은 가계가 맘대로 소비와 저축 으로 처분할 수 있는 소득을 의미한다. 흔히 국민들의 생활수준을 파악해 볼 수 있는 지표로 1인당 GNI가 널리 쓰이고 있으나 국민총소득에는 가계 뿐 아니라 기업 금융기관 정부가 벌어 들인 소득이 모두 포함되어 있다. 따라서 기업과 금융기관 등이 가계부문 보다 더 많은 소득을 벌어 1인당 국민총소득(GNI)이 높아진 경우에는 가계가 느끼는 체감경기는 전체 경기와 괴리가 있게 된다. 1인당 가계총처분가능소득(PGDI; Personal Gross Disposable Income)은 가계부문의 총처분가능소득을 연앙인구로 나누어 계산한 지표로 가계의 구매력을 가장 정확히 가늠해 볼 수 있는 소득지표이다.",
//            3,
//            DummyButton2()
//        ),
//        DummyTermCard(
//            "가상통화공개(ICO)",
//            "가상통화(ICO; Initial Coin Offering) 공개는 주로 혁신적인 신생기업(startup)이 암호 화화폐(cryptocurrency) 또는 디지털 토큰(digital token, 일종의 투자증명)을 이용하여 자금을 조달할 수 있는 크라우드펀딩(crowd funding)의 한 방식이다. 가상통화공개 (ICO)에서 새로 발행된 암호화화폐는 법화(legal tender) 또는 비트코인 등 기존의 가상 통화와 교환되어 투자자에게 팔린다. 이 용어는 거래소에 상장하려는 기업이 투자자에게 자기 주식을 처음 공개적으로 매도하는 기업공개(IPO; Initial Public Offering)에서 연유 되었다고 볼 수 있다. 기업공개(IPO)에 참여한 투자자는 해당 기업의 소유권과 관련하여 주식을 획득한다. 반면 가상통화공개(ICO)에 참여한 투자자는 해당 신생기업의 코인 (coins) 또는 토큰을 얻는데, 이는 해당 기업이 제안한 프로젝트가 나중에 성공했을 경우 평가될 수 있는 가치(value)로 볼 수 있다. ICO는 주로 블록체인플랫폼인 이더리움 (Etherium)에서 이뤄지고 있다. 우리나라의 경우 현재 금지하고 있으며 앞으로 ICO에 대한 논의를 거쳐 유사수신행위 또는 증권관련 법률로 규제할 것으로 보인다.",
//            2,
//            DummyButton3()
//        ),
//    )
//}
//
//data class DummyTermSimilarButton(
//    val title: String,
//)
//
//fun DummyButton1(): List<DummyTermSimilarButton> {
//    return listOf(
//        DummyTermSimilarButton("중앙은행"),
//        DummyTermSimilarButton("본원통화"),
//        DummyTermSimilarButton("지급준비제도"),
//    )
//}
//
//fun DummyButton2(): List<DummyTermSimilarButton> {
//    return listOf(
//        DummyTermSimilarButton("국민총소득(GNI)")
//    )
//}
//
//fun DummyButton3(): List<DummyTermSimilarButton> {
//    return listOf(
//        DummyTermSimilarButton("가상통화"),
//        DummyTermSimilarButton("블록체인"),
//        DummyTermSimilarButton("비트코인"),
//        DummyTermSimilarButton("빅데이터"),
//        DummyTermSimilarButton("크라우드펀등"),
//        DummyTermSimilarButton("핀테크"),
//    )
//}

>>>>>>> feature/term

@Preview
@Composable
fun Testterm() {
    Term(navController = rememberNavController())
}