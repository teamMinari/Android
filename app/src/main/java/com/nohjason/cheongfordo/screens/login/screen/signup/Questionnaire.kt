package com.nohjason.cheongfordo.screens.login.screen.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohjason.cheongfordo.screens.login.Screens
import com.nohjason.cheongfordo.screens.ui.titlebar.TitleBar
import com.nohjason.cheongfordo.ui.theme.MinariBlue
import com.nohjason.cheongfordo.ui.theme.pretendard_bold
import com.nohjason.cheongfordo.ui.theme.pretendard_regular
import com.nohjason.cheongfordo.ui.theme.pretendard_semibold

@Composable
fun Questionnaire(navController: NavController) {
    val showNextButton = remember { mutableStateOf(false) }
    val isButtonClicked = remember { mutableStateOf(false) }
    var selectedButtonIndex by remember { mutableStateOf(-1) }
    var selectedCardIndex by remember { mutableStateOf(-1) }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxWidth().verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TitleBar(title = "맞춤 학습 선택") {
                navController.popBackStack()
            }

            Spacer(modifier = Modifier.height(25.dp))

            Text(text = "당신의 맞춤 학습", fontSize = 25.sp, fontFamily = pretendard_semibold)

            Text(
                text = buildAnnotatedString {
                    append("자신에게 맞는 ")
                    withStyle(style = SpanStyle(color = Color(0xFF363CD5), fontFamily = pretendard_semibold)) {
                        append("포도송이")
                    }
                    append("를 획득할 수 있어요.")
                },
                fontSize = 13.sp,
                fontFamily = pretendard_regular
            )

            array().forEachIndexed { index, buttonList ->
                Button(list = buttonList) {
                    selectedButtonIndex = index
                    isButtonClicked.value = true
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                // 카드 선택 시 기존 선택 해제 및 새로운 카드 선택
                Card(
                    "https://i.ibb.co/kJPp4Cv/image-34.png",
                    "일러스트레이터",
                    selectedCardIndex == 0,
                ) {
                    selectedCardIndex = 0
                    showNextButton.value = true
                }
                Spacer(modifier = Modifier.width(10.dp))
                Card("https://i.ibb.co/kJPp4Cv/image-34.png", "알바생", selectedCardIndex == 1) {
                    selectedCardIndex = 1
                    showNextButton.value = true
                }
            }

            Spacer(modifier = Modifier.height(30.dp))

            Row {
                Card("https://i.ibb.co/kJPp4Cv/image-34.png", "IT개발자", selectedCardIndex == 2) {
                    selectedCardIndex = 2
                    showNextButton.value = true
                }
                Spacer(modifier = Modifier.width(10.dp))
                Card("https://i.ibb.co/kJPp4Cv/image-34.png", "선생님", selectedCardIndex == 3) {
                    selectedCardIndex = 3
                    showNextButton.value = true
                }
            }

            Spacer(modifier = Modifier.height(100.dp))
        }

        if (showNextButton.value) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(300.dp)
                    .background(Color.Transparent)
                    .align(Alignment.BottomCenter)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color.Transparent, Color.Black.copy(alpha = 0.7f)),
                            startY = (LocalConfiguration.current.screenHeightDp * 3 / 10).toFloat(),
                            endY = LocalConfiguration.current.screenHeightDp.toFloat()
                        )
                    ),
                contentAlignment = Alignment.Center
            ) {
                Box(
                    modifier = Modifier
                        .width(70.dp)
                        .clip(CircleShape)
                        .background(MinariBlue)
                        .clickable {
                            navController.navigate(Screens.LastSignup.rout)
                        },
                ) {
                    Text(
                        text = "다음",
                        color = Color.White,
                        fontFamily = pretendard_bold,
                        modifier = Modifier.padding(13.dp).align(Alignment.Center)
                    )
                }
            }
        }
    }
}

fun array(): List<List<String>> {
    return listOf(
        listOf("10대", "20대", "30대", "40대"),
    )
}

@Composable
private fun Button(list: List<String>, onClick: () -> Unit) {
    var selectedItemIndex by rememberSaveable { mutableStateOf(-1) }

    LazyRow(horizontalArrangement = Arrangement.spacedBy(20.dp)) {
        items(list) { item ->
            val index = list.indexOf(item)
            val isSelected = selectedItemIndex == index

            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (isSelected) MinariBlue else Color.White)
                    .clickable {
                        selectedItemIndex = index
                        onClick()
                    }
                    .border(
                        1.dp,
                        color = if (isSelected) MinariBlue else Color(0xFFD4D4D4),
                        shape = CircleShape
                    )
            ) {
                Text(
                    text = item,
                    color = if (isSelected) Color.White else Color(0xFFCFD7F4),
                    fontFamily = pretendard_regular,
                    modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp).align(Alignment.Center)
                )
            }
        }
    }
}

@Composable
private fun Card(
    getWebLink: String,
    text: String,
    isSelected: Boolean,
    onClick: () -> Unit,
) {
    val alpha = if (isSelected) 1f else 0.3f

    Column(
        modifier = Modifier
            .width(165.dp)
            .height(238.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .alpha(alpha)
            .border(
                2.dp,
                color = if (isSelected) Color(0xFF2B30AA) else Color(0xFFECEFFB),
                shape = RoundedCornerShape(20.dp)
            )
            .clickable {
                onClick()
            },
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        AsyncImage(
            modifier = Modifier.size(135.dp),
            model = getWebLink,
            contentDescription = null
        )
        Text(text = text, fontSize = 18.sp, fontFamily = pretendard_bold)
    }
}


@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Questionnaire(rememberNavController())
}
