package com.nohjason.minari.screens.profile.directory

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.screens.profile.directory.direc_cards.DirecGp
import com.nohjason.minari.screens.profile.directory.direc_cards.DirecGps
import com.nohjason.minari.screens.profile.directory.direc_cards.DirecGpse
import com.nohjason.minari.screens.profile.directory.direc_cards.DirecTerm

@Composable
fun TutorialList(
    gpseItem: List<DirecGpse>?,
    gpsItem: List<DirecGps>?,
    gpItem: List<DirecGp>?
){
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White)
    ){
        if (gpseItem != null || gpsItem != null || gpItem != null) {
            Text(
                text = "튜토리얼",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 23.dp, top = 15.dp, start = 20.dp)
            )

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                // gpsItem
                gpsItem?.let {
                    it.forEachIndexed { index, data ->
                        DirecGps(data = data)
                        Spacer(modifier = Modifier.height(15.dp))
                        Divider(
                            color = Color(0xFFECEFFB), modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                // gpItem
                gpItem?.let {
                    it.forEachIndexed { index, data ->
                        DirecGp(data = data)
                        Spacer(modifier = Modifier.height(15.dp))
                        // The last Divider will be conditionally placed
                        if (index < it.size - 1 || gpsItem == null) {
                            Divider(
                                color = Color(0xFFECEFFB), modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                        }
                        Spacer(modifier = Modifier.height(15.dp))
                    }
                }

                // gpseItem
                gpseItem?.let {
                    it.forEachIndexed { index, data ->
                        DirecGpse(data = data)
                        if (index < it.size - 1 || gpsItem != null || gpItem != null) {
                            Spacer(modifier = Modifier.height(15.dp))
                            Divider(
                                color = Color(0xFFECEFFB), modifier = Modifier
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
fun  TermList(
    termItem:List<DirecTerm>?
){
    if (termItem != null){
        Column (
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
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                termItem.forEachIndexed { index, data ->
                    DirecTerm(data = data)

                    if (index < termItem.size - 1) {
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
        }
    }
}


@Preview
@Composable
fun PreDirec(){
    val sampleGpse = listOf(
        DirecGpse(
            gpseId = 1,
            gpseName = "10대 - 경제 관련 서비스",
            gpseTime = 2,
            gpseLike = true
        ),
        DirecGpse(
            gpseId = 2,
            gpseName = "20대 - 경제 관련 서비스",
            gpseTime = 3,
            gpseLike = false
        )
    )

    val sampleGps = listOf(
        DirecGps(
            gpsId = 1,
            gpsContent = "경제 시작하기",
            gpsImg = "https://i.ibb.co/Jc8BB5N/Group-1648-1.png",
            gpsLike = true,
            gpsTpList = listOf("초급", "중급", "고급")
        ),
        DirecGps(
            gpsId = 2,
            gpsContent = "경제 이론",
            gpsImg = "https://i.ibb.co/Jc8BB5N/Group-1648-1.png",
            gpsLike = false,
            gpsTpList = listOf("기초", "중급")
        )
    )

    val sampleGp = listOf(
        DirecGp(
            gpId = 1,
            gpName = "경제 프로젝트 1",
            gpImg = "https://i.ibb.co/Jc8BB5N/Group-1648-1.png",
            gpLike = true
        ),
        DirecGp(
            gpId = 2,
            gpName = "경제 프로젝트 2",
            gpImg = "https://i.ibb.co/Jc8BB5N/Group-1648-1.png",
            gpLike = false
        )
    )

    TutorialList(
        gpseItem = sampleGpse,
        gpsItem = sampleGps,
        gpItem = sampleGp
    )
}


