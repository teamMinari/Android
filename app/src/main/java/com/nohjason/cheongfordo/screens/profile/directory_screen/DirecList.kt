package com.nohjason.cheongfordo.screens.profile.directory_screen

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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_cards.DirecGps
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_cards.DirecGpse
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_cards.DirecTerm
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecGps
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecGpse
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.cheongfordo.screens.rout.GrapeViewModel

@Composable
fun TutorialList(
    direcViewModel: DirecViewModel,
    grapeViewModel: GrapeViewModel = viewModel(),
    token: String,
    navController: NavController
) {
    val gpseItem = direcViewModel.direcGpseData.collectAsState().value?.data
    val gpsItem = direcViewModel.direcGpsData.collectAsState().value?.data


    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "튜토리얼",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 23.dp, top = 15.dp, start = 20.dp)
            )

            val combinedList = listOfNotNull(gpsItem, gpseItem).flatten()

            combinedList.forEachIndexed { index, data ->
                when (data) {
                    is DirecGps -> DirecGps(data = data, token = token, grapeViewModel=grapeViewModel, navController = navController)
                    is DirecGpse -> DirecGpse(data = data, token = token, grapeViewModel=grapeViewModel, navController = navController)
                }

                Spacer(modifier = Modifier.height(15.dp))

                if (index < combinedList.size - 1) {
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


@Composable
fun TermList(
    direcViewModel: DirecViewModel,
    grapeViewModel: GrapeViewModel = viewModel(),
    navController: NavController,
    token: String
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
            modifier = Modifier.padding(bottom = 23.dp, top = 15.dp, start = 20.dp)
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
                        DirecTerm(data = data, token = token, grapeViewModel=grapeViewModel, navController = navController)
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
                Spacer(modifier = Modifier.height(15.dp))
            }
        }
    }
}
