package com.nohjason.minari.screens.profile.my_dictionary

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.network.MainViewModel

@Composable
fun MyDictionaryScreen(
    context: Context,
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    val preferencesManager = remember { PreferencesManager(context) }
    val accessToken = preferencesManager.getData("accessToken", "")
    LaunchedEffect(Unit) {
        viewModel.fetchAllBookTerms(accessToken)
    }
    val bookTerms by viewModel.books.collectAsState()
    var text by remember { mutableStateOf("") }

    Column {
        TopAppBar(
            title = {},
            backgroundColor = Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null, tint = Color.Black)
                }
            }
        )
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
//                                    viewModel.addDelete(item.termNm, accessToken)
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