package com.nohjason.minari.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.nohjason.minari.screens.profile.directory.GpStatusResponse
import com.nohjason.minari.screens.profile.directory.GpsStatusResponse
import com.nohjason.minari.screens.profile.directory.GpseStatusResponse
import com.nohjason.minari.screens.profile.directory.TermStatusResponse

@Composable
fun DirecScreen(
    term: TermStatusResponse,
    gpse: GpseStatusResponse,
    gps: GpsStatusResponse,
    gp: GpStatusResponse,
){
    Column (
        modifier = Modifier.background(color = Color(0xFFF5F6FA))
    ){

    }
}