package com.nohjason.minari.screens.profile.directory_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.ui.titlebar.TitleBar

@Composable
fun DirecScreen(
    direcViewModel: DirecViewModel,
    token: String,
){

    LaunchedEffect(Unit) {
        direcViewModel.getDirecGp(token)
        direcViewModel.getDirecGps(token)
        direcViewModel.getDirecGpse(token)
        direcViewModel.getDirecTerm(token)
    }
    val term = direcViewModel.direcTermData.collectAsState().value
    val gpse = direcViewModel.direcGpseData.collectAsState().value
    val gps = direcViewModel.direcGpsData.collectAsState().value
    val gp = direcViewModel.direcGpData.collectAsState().value


    val scrollState = rememberScrollState()

    Column (
        modifier = Modifier
            .background(color = Color(0xFFF5F6FA))
            .fillMaxSize()
            .verticalScroll(scrollState)
    ) {
        TitleBar(
            title = "저장소 이름",
        )
        Spacer(modifier = Modifier.height(15.dp))

        TutorialList(
            gpseItem = gpse?.data,
            gpsItem = gps?.data,
            gpItem = gp?.data
        )
        Spacer(modifier = Modifier.height(15.dp))
        TermList(termItem = term?.data)
        Spacer(modifier = Modifier.height(110.dp))

    }
}

@Preview(showBackground = true)
@Composable
fun PreDirecScreen( ){

}

