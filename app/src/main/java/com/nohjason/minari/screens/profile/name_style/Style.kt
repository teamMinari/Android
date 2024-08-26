package com.nohjason.minari.screens.profile.name_style

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.TopAppBar
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.pretendard_semibold

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Style(
    navController: NavController
) {
    Scaffold(
        topBar = {
            TopAppBar(
                backgroundColor = Color.White,
                title = {
                    Icon(
                        painter = painterResource(id = R.drawable.trophy),
                        contentDescription = null,
                        tint = Color.Unspecified
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "칭호 보기",
                        fontFamily = pretendard_semibold,
                        fontSize = 17.sp
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Icon(
                            painter = painterResource(id = R.drawable.befor_arrow),
                            contentDescription = null,
                            tint = Color.Unspecified,
                            modifier = Modifier.size(20.dp)
                        )
                    }
                }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier.padding(innerPadding)
        ) {
            item {

            }
            items(10) {

            }
        }
    }
}

@Preview(showSystemUi = true)
@Composable
private fun Test() {
    Style(
        navController = rememberNavController()
    )
}