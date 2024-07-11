package com.nohjason.minari.screens.profile.my_dictionary

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.room.util.TableInfo
import com.nohjason.minari.R
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.profile.my_dictionary.card.WordCard
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.myapplication.network.MainViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

@Composable
fun MyDictionaryScreen(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    LaunchedEffect(Unit) {
        viewModel.fetchAllBookTerms()
    }
    val bookTerms by viewModel.books.collectAsState()
    var text by remember { mutableStateOf("") }

    Column {
        TopAppBar(
            title = {
//                MinariTextField(
//                    modifier = Modifier
//                        .height(25.dp)
//                        .padding(end = 30.dp)
//                        .fillMaxWidth()
//                        .background(Color.Transparent, shape = CircleShape),
//                    value = text,
//                    onValueChange = { text = it }
//                ) { }
            },
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                }
            }
        )
//        LazyVerticalStaggeredGrid(
//            columns = StaggeredGridCells.Adaptive(190.dp),
//            verticalItemSpacing = 10.dp,
//            horizontalArrangement = Arrangement.spacedBy(10.dp),
//            content = {
//                items(bookTerms) { item ->
//                    Box(
//                        modifier = Modifier
//                            .clickable {

//                            }
//                            .shadow(5.dp, RoundedCornerShape(10.dp))
//                            .background(Color.White)
//                            .padding(10.dp)
//                    ) {
//                        Column {
//                            MinariText(text = item.termNm, size = 20)
//                            if (item.isChecked) {
//                                androidx.compose.material.Text(text = item.termDifficulty, color = Color.Red, fontWeight = FontWeight.Bold)
//                            }
//                            MinariText(
//                                text = item.termExplain,
//                                fontWeight = FontWeight.Light,
//                                textAlign = TextAlign.Left,
//                                size = 13,
//                                maxLines = if (item.isChecked) Int.MAX_VALUE else 20,
//                                overflow = TextOverflow.Ellipsis
//                            )
//                            Row {
//                                Spacer(modifier = Modifier.weight(0.1f))
//                            }
//                        }
//                    }
//                }
//            },
//            modifier = Modifier
//                .fillMaxSize()
//                .padding(10.dp)
//        )
//------------------------------------------------------------
        LazyColumn(
            modifier = Modifier
                .padding(10.dp)
                .fillMaxSize()
                .background(MinariWhite),
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            items(bookTerms) { item ->
                Box(
                    modifier = Modifier
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .fillMaxWidth()
                        .clickable {
                            viewModel.checkedThat(item.termNm)
                        }
                        .padding(10.dp)
                ) {
                    val starCount = item.termDifficulty[3].toString().toInt()

                    Column {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            MinariText(text = item.termNm, size = 20)
                            Spacer(modifier = Modifier.width(10.dp))
                            LazyRow {
                                items(starCount) {
                                    Icon(
                                        painter = painterResource(R.drawable.star),
                                        contentDescription = "star",
                                        tint = Color.Unspecified,
                                        modifier = Modifier.size(15.dp)
                                    )
                                }
                            }
                            Spacer(modifier = Modifier.weight(0.1f))
                            Icon(
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,
                                Modifier.clickable {
                                    viewModel.addDelete(item.termNm)
                                }
                            )
                        }
                        if (item.isChecked) {
                            MinariText(
                                text = item.termExplain,
                                size = 15,
                                fontWeight = FontWeight.Light,
                                textAlign = TextAlign.Left
                            )
                        }
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    Wow()
}

data class Test(
    val id: Int,
    val termNm: String,
    val termDifficulty: String
)

fun generateDummyBookTerms(): List<Test> {
    return listOf(
        Test(1, "용어 1", "Lv_1"),
        Test(2, "용어 2", "Lv_1"),
        Test(3, "용어 3", "Lv_3"),
        Test(4, "용어 4", "Lv_2"),
        Test(5, "용어 5", "Lv_2")
        // 필요에 따라 추가적으로 데이터를 생성할 수 있습니다.
    )
}

@Composable()
fun Wow() {
    LazyColumn(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxSize()
            .background(MinariWhite),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(generateDummyBookTerms()) { item ->
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.White)
                    .fillMaxWidth()
                    .padding(10.dp)
                    .clickable { }
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MinariText(text = item.termNm, size = 20)
                    Spacer(modifier = Modifier.width(10.dp))
                    MinariText(text = item.termDifficulty, size = 10, color = Color.Red)
                }
            }
        }
    }
}
