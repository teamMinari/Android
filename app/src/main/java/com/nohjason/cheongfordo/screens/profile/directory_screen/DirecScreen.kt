package com.nohjason.cheongfordo.screens.profile.directory_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.cheongfordo.screens.ui.titlebar.TitleBar

@Composable
fun DirecScreen(
    direcViewModel: DirecViewModel = viewModel(),
    token: String,
    navController: NavController,
){
    direcViewModel.getDirecTerm(token) // Term 데이터 호출
    direcViewModel.getDirecGpse(token) // Gpse 데이터 호출
    direcViewModel.getDirecGps(token) // Gps 데이터 호출
    direcViewModel.getDirecGp(token) // Gp 데이터 호출


    val scrollState = rememberScrollState()


    Column (
        modifier = Modifier
            .background(color = Color(0xFFF5F6FA))
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        TitleBar(
            title = "저장목록",
            onClick = {navController.popBackStack()}
        )
        Spacer(modifier = Modifier.height(15.dp))

        TutorialList(direcViewModel = direcViewModel, token = token, navController = navController)

        Spacer(modifier = Modifier.height(15.dp))

        TermList(direcViewModel = direcViewModel, token = token, navController = navController)

        Spacer(modifier = Modifier.height(110.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreDirecScreen( ){

}

