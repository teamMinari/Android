package com.nohjason.minari.screens.term

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
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.screens.profile.my_dictionary.db.MainViewModel
import com.nohjason.minari.screens.profile.my_dictionary.db.UserEntity
import com.nohjason.minari.screens.term.button.TermButtonViewModel
import com.nohjason.minari.screens.term.card.TermCard
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun Term(
    viewModel: TermButtonViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
    navController: NavController,
    allProduct: List<UserEntity>,
    mainViewModel: MainViewModel
) {
    var text by remember { mutableStateOf("") }
    Column {
        TopAppBar(
            title = {
                MinariTextField(
                    modifier = Modifier
                        .height(30.dp)
                        .padding(end = 30.dp)
                        .fillMaxWidth()
                        .background(MinariWhite, shape = CircleShape),
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
//                    items(GetDummyTermButton()) { index ->
//                        val initialState = if (index.title == "전체") false else true
//                        TermButton(viewModel = viewModel, title = index.title, initialState = initialState)
//                    }
                }
                // ----------------------------------------------------
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(10.dp, 10.dp, 0.dp, 0.dp))
                        .background(Color.White),
                ) {
                    itemsIndexed(items = allWords) { index, item ->
                        TermCard(
                            title = item.title,
                            value = item.value,
                            starCount = item.starCount,
                            dummyTermSimilarButton = item.dummyTermSimilarButton,
                            navController = navController,
                            viewModel = mainViewModel,
                        )
                        if (index != allWords.size - 1) { // 마지막 항목이 아닌 경우에만 Divider 추가
                            MinariLine(horizontalPadding = 10.dp)
                        }
                    }
                }
            }
        }
    }
}
