package com.nohjason.minari.screens.profile.my_dictionary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.myapplication.network.MainViewModel

//@Preview
@Composable
fun MyDictionaryScreen(
    navController: NavHostController,
    viewModel: MainViewModel
) {
    val bookTerms by viewModel.books.collectAsState()
    var text by remember { mutableStateOf("") }

    LaunchedEffect(key1 = Unit) {
        viewModel.fetchAllBookTerms()
    }

    Column {
        TopAppBar(
            title = {
                MinariTextField(
                    modifier = Modifier
                        .height(25.dp)
                        .padding(end = 30.dp)
                        .fillMaxWidth()
                        .background(Color.Transparent, shape = CircleShape),
                    value = text,
                    onValueChange = {text = it}
                ) {  }
            },
            backgroundColor =  Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                }
            }
        )
        LazyVerticalStaggeredGrid(
            columns = StaggeredGridCells.Adaptive(190.dp),
            verticalItemSpacing = 10.dp,
            horizontalArrangement = Arrangement.spacedBy(10.dp),
            content = {
                bookTerms?.let {
                    items(it.data) { item ->
                        Box(
                            modifier = Modifier
                                .shadow(5.dp, RoundedCornerShape(10.dp))
                                .background(Color.White)
                                .padding(10.dp)
                        ) {
                            Column {
                                MinariText(text = item.termNm, size = 20)
                                MinariText(
                                    text = item.termExplain,
                                    fontWeight = FontWeight.Light,
                                    textAlign = TextAlign.Right,
                                    size = 13,
                                    maxLines = 50
                                )
                            }
                        }
                    }
                }
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(10.dp)
        )
//        Column(
//            modifier = Modifier
//                .fillMaxSize(),
//            horizontalAlignment = Alignment.CenterHorizontally
//        ) {
//
//            val test by mainViewModel.allProducts.observeAsState(emptyList())
//            var count = 0;
//            test.map { user ->
//                if(user.check == false) {
//                    count += 1
//                }
//            }
//
//            Column {
//                if (test.size - count != 0) {
//                    Box(modifier = Modifier
//                        .fillMaxSize()
//                        .background(Color.White)
//                    ) {
//                        LazyColumn(
//                            modifier = Modifier
//                                .padding(10.dp)
//                        ) {
//                            items(test) { item ->
//                                if (item.check != false) {
//                                    WordCard(
//                                        title = item.id,
//                                        isLike = item.check
//                                    ) {
//                                        mainViewModel.upsertProduct(
//                                            UserEntity(id = item.id, check = !item.check)
//                                        )
//                                    }
//                                    Spacer(modifier = Modifier.padding(5.dp))
//                                }
//                            }
//                        }
//                        Spacer(modifier = Modifier.height(20.dp))
//                    }
//                } else {
//                    InProduction(
//                        "아직 추가된 용어가 없습니다",
//                        "용어를 추가해 보세요"
//                    )
//                }
//            }
//        }
    }
}

@Preview(showBackground = true)
@Composable
fun Test() {
    Wow()
}

@Composable()
fun Wow() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        items(5) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                Box(
                    modifier = Modifier
                        .weight(0.5f)
                        .shadow(6.dp, shape = RoundedCornerShape(10.dp))
                        .background(Color.White)
                        .padding(10.dp)
                ) {
                    Text(text = "wow\nwef\nwef\newf\nwef\nwef\nwef\nwef\nwef\nwef")
                }
                Box(modifier = Modifier.weight(0.5f)) {
                    Text(text = "wow")
                }
            }
        }
    }
}