package com.nohjason.minari.screens.profile.directory_screen

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGp
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGp
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel

@Composable
fun TutorialList(
    direcViewModel: DirecViewModel
){
    val gpseItem = direcViewModel.direcGpseData.collectAsState().value
    val gpsItem = direcViewModel.direcGpsData.collectAsState().value
    val gpItem = direcViewModel.direcGpData.collectAsState().value

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        Text(
            text = "튜토리얼",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 23.dp, top = 15.dp, start = 20.dp)
        )

        if (gpseItem != null || gpsItem != null || gpItem != null) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // gpsItem
                gpsItem?.data?.let { it ->
                    it.forEachIndexed { index, data ->
                        DirecGps(data = data)
                        // Divider 추가 (마지막 요소가 아닐 때만)
                        if (index < it.size - 1 || gpseItem != null || gpItem != null) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
                                color = Color(0xFFECEFFB),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }

                // gpItem
                gpItem?.data?.let { it ->
                    it.forEachIndexed { index, data ->
                        DirecGp(data = data)
                        Spacer(modifier = Modifier.height(15.dp))
                        // Divider 추가 (마지막 요소가 아닐 때만)
                        if (index < it.size - 1 || gpsItem == null) {
                            Divider(
                                color = Color(0xFFECEFFB),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                // gpseItem
                gpseItem?.data?.let { it ->
                    it.forEachIndexed { index, data ->
                        DirecGpse(data = data)
                        // Divider 추가 (마지막 요소가 아닐 때만)
                        if (index < it.size - 1 || gpsItem != null || gpItem != null) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
                                color = Color(0xFFECEFFB),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun TermList(
    direcViewModel: DirecViewModel
) {
    val term = direcViewModel.direcTermData.collectAsState().value
    val termItem = term?.data


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Text(
            text = "용어",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .padding(bottom = 23.dp, top = 15.dp, end = 308.dp)
        )
        if (termItem != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                termItem.let {
                    it.forEachIndexed { index, data ->
                        DirecTerm(data = data)
                        // Divider 추가 (마지막 요소가 아닐 때만)
                        if (index < it.size - 1) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
                                color = Color(0xFFECEFFB),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                            Spacer(modifier = Modifier.height(15.dp))
                        }
                    }
                }
            }
        }
    }
}
