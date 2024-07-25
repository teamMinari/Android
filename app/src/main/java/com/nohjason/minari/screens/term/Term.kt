package com.nohjason.minari.screens.term

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.screens.term.button.GetDummyTermButton
import com.nohjason.minari.screens.term.button.TermButton
import com.nohjason.minari.screens.term.card.TermCard
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.myapplication.network.MainViewModel
import kotlin.math.log

@Composable
fun Term(
    context: Context,
    navController: NavController,
    viewModel: MainViewModel,
) {
    val routin by viewModel.routines.collectAsState()
    var text by remember { mutableStateOf("") }
    val preferencesManager = remember { PreferencesManager(context) }
    val accessToken = preferencesManager.getData("accessToken", "")


    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllTerms(token = accessToken)
    }

    Column {
        TopAppBar(
            title = {
                MinariTextField(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(end = 30.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent, shape = CircleShape),
                    value = text,
                    onValueChange = { text = it },
                    onClick = { navController.navigate("test/${text}") }
                )
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(
                    onClick = { navController.popBackStack() },
                ) {
                    Icon(
                        Icons.Filled.ArrowBack,
                        null,
                        tint = Color.Black
                    )
                }
            }
        )
        Box(
            modifier = Modifier
                .background(MinariWhite)
                .fillMaxSize()
        ) {
            Column {
                // ----------------------------------------------------
                // 카테고리 버튼 영역
                LazyRow(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(10.dp)
                ) {
                    items(GetDummyTermButton()) { item ->
//                        TermButton(title = item.title, category = category)
                    }
                }
                // ----------------------------------------------------
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                        .background(Color.White),
                ) {
                    itemsIndexed(items = routin) { index, item ->
                        val starCount = item.termDifficulty[3].toString().toInt()

                        TermCard(
                            title = item.termNm,
                            value = item.termExplain,
                            starCount = starCount,
                            navController = navController,
                            viewModel = viewModel,
                        )

                        if (index != routin.size - 1) { // 마지막 항목이 아닌 경우에만 Divider 추가
                            MinariLine(horizontalPadding = 10.dp)
                        }
                    }
                }
            }
        }
    }
}
