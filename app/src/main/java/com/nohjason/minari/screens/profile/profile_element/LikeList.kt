package com.nohjason.minari.screens.profile.profile_element

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGp
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.rout.GrapeViewModel
import com.nohjason.minari.ui.theme.pretendard_bold
import com.nohjason.minari.ui.theme.pretendard_semibold


@Composable
fun LikeList(
    direcViewModel: DirecViewModel,
    navHostController: NavHostController,
    grapeViewModel: GrapeViewModel,
    token: String
) {
    val termItem = direcViewModel.direcTermData.collectAsState().value?.data
    val gpseItem = direcViewModel.direcGpseData.collectAsState().value?.data
    val gpsItem = direcViewModel.direcGpsData.collectAsState().value?.data

    Box(
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight()
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp)) // Box에 clip 적용
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(horizontal = 24.dp),
        ) {
            Row(
                modifier = Modifier.padding(top = 14.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_list),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                )
                Text(
                    text = "저장 목록",
                    fontFamily = pretendard_semibold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(23.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(10.dp))
                    val combinedList = listOfNotNull(gpsItem, gpseItem, termItem).flatten().take(5)

                    combinedList.forEachIndexed { index, data ->
                        when (data) {
                            is DirecGps -> DirecGps(data = data, token = token, grapeViewModel=grapeViewModel, navController = navHostController)
                            is DirecGpse -> DirecGpse(data = data, token = token, grapeViewModel=grapeViewModel, navController = navHostController)
                            is DirecTerm -> DirecTerm(data = data, token = token, grapeViewModel=grapeViewModel, navController = navHostController)
                        }

                        Spacer(modifier = Modifier.height(15.dp))

                        // 마지막 항목이 아니면 Divider 추가
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

                    //카드 저장목록
                    Text(
                        text = "더보기",
                        fontFamily = pretendard_bold,
                        modifier = Modifier
                            .clickable {
                                    navHostController.navigate(Screens.Directory.rout)
                            },
                    )
                    Spacer(modifier = Modifier.height(23.dp))

                }
            }
            Spacer(modifier = Modifier.height(14.dp)) // Spacer 추가하여 버튼과 리스트 사이에 공간 추가
        }
    }
}