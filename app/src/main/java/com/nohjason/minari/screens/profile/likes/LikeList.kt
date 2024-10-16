package com.nohjason.minari.screens.profile.likes

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGp
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_cards.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGp
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGps
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpsResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpseResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecTerm
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecTermResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpsResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpseResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecTermResponse
import com.nohjason.minari.screens.profile.profile_data.LikeListData
import com.nohjason.minari.screens.quiz.quiz_main.selectPlayData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeList(
    direcViewModel: DirecViewModel,
    navHostController: NavHostController,
    token: String
) {
    val termItem = direcViewModel.direcTermData.collectAsState().value?.data



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
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.padding(top = 14.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_list),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                )
                Text(
                    text = "저장 목록",
                    fontWeight = FontWeight.SemiBold,
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

//                if (gpseItem != null || gpsItem != null || gpItem != null) {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight(),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Spacer(modifier = Modifier.height(10.dp))

                        // termItem
//                        gpsItem?.take(2)?.let {
//                            it.forEachIndexed { index, data ->
//                                DirecGps(data = data)
//                                Spacer(modifier = Modifier.height(15.dp))
//                                // 마지막 요소일 경우 Divider를 표시하지 않음
//                                if (index < it.size - 1) { // 마지막 요소가 아닐 때만 Divider 추가
//                                    Divider(
//                                        color = Color(0xFFECEFFB),
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(1.dp)
//                                    )
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                }
//                            }
//                        }
//
//                        // gpItem
//                        gpItem?.take(2)?.let {
//                            it.forEachIndexed { index, data ->
//                                DirecGp(data = data)
//                                Spacer(modifier = Modifier.height(15.dp))
//                                // 마지막 요소일 경우 Divider를 표시하지 않음
//                                if (index < it.size - 1) { // 마지막 요소가 아닐 때만 Divider 추가
//                                    Divider(
//                                        color = Color(0xFFECEFFB),
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(1.dp)
//                                    )
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                }
//                            }
//                        }
//
//                        // gpseItem
//                        gpseItem?.take(2)?.let {
//                            it.forEachIndexed { index, data ->
//                                DirecGpse(data = data)
//                                Spacer(modifier = Modifier.height(15.dp))
//                                // 마지막 요소일 경우 Divider를 표시하지 않음
//                                if (index < it.size - 1) { // 마지막 요소가 아닐 때만 Divider 추가
//                                    Divider(
//                                        color = Color(0xFFECEFFB),
//                                        modifier = Modifier
//                                            .fillMaxWidth()
//                                            .height(1.dp)
//                                    )
//                                    Spacer(modifier = Modifier.height(15.dp))
//                                }
//                            }
//                        }

                        // termItem
                        termItem?.take(2)?.let {
                            it.forEachIndexed { index, data ->
                                DirecTerm(data = data)
                                Spacer(modifier = Modifier.height(15.dp))
                                // 마지막 요소일 경우 Divider를 표시하지 않음
                                if (index < it.size - 1) { // 마지막 요소가 아닐 때만 Divider 추가
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

                        //카드 저장목록
                        Text(
                            text = "더보기",
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .clickable {
//                                    navHostController.navigate("myDirectory")
                                },
                        )
                        Spacer(modifier = Modifier.height(23.dp))
                    }
//                } else{
//                    Text(text = "저장된 요소가 없습니다.")
//                }
            }
//            Spacer(modifier = Modifier.height(14.dp)) // Spacer 추가하여 버튼과 리스트 사이에 공간 추가
//        }
    }
}


//@Preview
//@Composable
//fun PreLikeList() {
//    val rem = rememberNavController()
//    val gpse = getDummyDirecGpseResponse().data
//    val gps = getDummyDirecGpsResponse().data
//    val gp = getDummyDirecGpResponse().data
//    val term = getDummyDirecTermResponse().data
//
//    LikeList(gpseItem = gpse, gpsItem = gps, gpItem = gp, termItem = term, navHostController = rem)
//}
