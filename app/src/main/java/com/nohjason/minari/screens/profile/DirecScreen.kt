package com.nohjason.minari.screens.profile

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
import com.nohjason.minari.screens.profile.directory.TermList
import com.nohjason.minari.screens.profile.directory.TutorialList
import com.nohjason.minari.screens.quiz.quiz_end.TitleBar
import com.nohjason.myapplication.network.response.TermResponse

@Composable
fun DirecScreen(
    term: DirecTermResponse?,
    gpse: DirecGpseResponse?,
    gps: DireGpsResponse?,
    gp: DirecGpResponse?,
//    title: String (후에는 서버에서 값을 받아와야한다.)
){
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

