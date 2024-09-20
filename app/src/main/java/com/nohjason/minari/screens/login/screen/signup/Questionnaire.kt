package com.nohjason.minari.screens.login.screen.signup

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_bold

@Composable
fun Questionnaire(
    navController: NavController
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 30.dp),
    ) {
        Spacer(modifier = Modifier.weight(0.2f))

        Text(
            text = "어떤주제에 관심이\n있으신가요?",
            fontSize = 25.sp,
            fontFamily = pretendard_bold
        )

        Spacer(modifier = Modifier.weight(0.1f))

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(array()) { item ->
                Button(list = item)
            }
        }

        Spacer(modifier = Modifier.weight(0.6f))

        Text(
            text = "관심주제는 나중에 다시 수정할 수 있어요",
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            color = Color.Gray
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(CircleShape)
                .background(MinariBlue)
                .clickable {
                    navController.navigate(Screens.LastSignup.rout) {

                    }
                }
        ) {
            Text(
                text = "다음",
                color = Color.White,
                fontFamily = pretendard_bold,
                modifier = Modifier
                    .padding(13.dp)
                    .align(Alignment.Center)
            )
        }

        Spacer(modifier = Modifier.weight(0.15f))
    }
}

fun array(): List<List<String>> {
    return listOf(
        listOf("금융", "산업", "IT"),
        listOf("증권", "부동산"),
        listOf("글로벌 경제", "채권"),
    )
}

@Composable
private fun Button(list: List<String>) {
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        items(list) { item ->
            var clickable by rememberSaveable { mutableStateOf(false) }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(if (clickable) MinariBlue else Color.White)
                    .clickable { clickable = !clickable }
                    .border(1.dp, color = if (clickable) MinariBlue else Color(0xFFD4D4D4), shape = CircleShape)
            ) {
                Text(
                    text = item,
                    color = if (clickable) Color.White else Color.Black,
                    fontFamily = pretendard_bold,
                    modifier = Modifier
                        .padding(vertical = 7.dp, horizontal = 25.dp)
                        .align(Alignment.Center)
                )
            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Questionnaire(rememberNavController())
}