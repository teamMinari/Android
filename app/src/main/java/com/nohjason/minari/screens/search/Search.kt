package com.nohjason.minari.screens.search

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
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
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_medium

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Search(
    navController: NavController,
    grapeViewModel: GrapeViewModel = viewModel()
) {
    val getSearchTerm by grapeViewModel.getSearchTerm.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    var text by remember { mutableStateOf("") }
    val focusRequester = remember { FocusRequester() } // FocusRequester 선언
    LaunchedEffect(Unit) {
        grapeViewModel.getSearchTerm(token, text)
    }

    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    val keyboardController = LocalSoftwareKeyboardController.current

                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp)
                    ) {
                        MinariTextField(
                            value = text,
                            onValueChange = {
                                text = it
                                grapeViewModel.getSearchTerm(token, text)
                            },
                            onClick = {
                                if (text.isNotEmpty() && getSearchTerm!!.data.isNotEmpty()) {
                                    keyboardController?.hide() // 키보드 내리기
                                    navController.navigate(Screens.Term.rout + "/${text}")
                                }
                            },
                            fontSize = 20.sp,
                            modifier = Modifier.focusRequester(focusRequester) // FocusRequester 연결
                        )
                    }
                }
            )
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize()
                .background(Color(0xFFF5F6FA))
        ) {
            if (getSearchTerm != null) {
                item {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
                items(getSearchTerm!!.data) { item ->
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .background(Color.White)
                            .clickable {
                                text = item.termNm
                            }
                            .padding(vertical = 10.dp, horizontal = 20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.small_search),
                            tint = Color.Unspecified,
                            contentDescription = null,
                            modifier = Modifier.size(30.dp)
                        )
                        Spacer(modifier = Modifier.width(5.dp))
                        Text(
                            text = item.termNm,
                            fontFamily = pretendard_medium,
                            fontSize = 20.sp,
                            color = Color(0xFFADB0BF)
                        )
                    }
                }
                item {
                    Spacer(
                        modifier = Modifier
                            .height(20.dp)
                            .fillMaxWidth()
                            .background(Color.White)
                    )
                }
            }
        }
    }

    // 화면이 로드될 때 포커스를 요청
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}

@Preview
@Composable
private fun Test() {
    Search(navController = rememberNavController())
}