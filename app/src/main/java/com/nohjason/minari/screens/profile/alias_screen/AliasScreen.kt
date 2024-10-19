package com.nohjason.minari.screens.profile.alias_screen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Divider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.profile.profile_element.RewardBar
import com.nohjason.minari.screens.ui.titlebar.TitleBar

@Composable
fun AliasScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    navHostController: NavHostController,
    token: String
){
    LaunchedEffect(Unit) {
        profileViewModel.getProfile(token)
    }

    val data = profileViewModel.profileData.collectAsState().value
    val scrollState = rememberScrollState()
    val nameList = remember {
        List(30) { index -> "Title $index" }
    }

    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF5F6FA))
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        TitleBar(
            title = "칭호보기",
            imgResId =  R.drawable.ic_noun,
            onClick = {
                navHostController.popBackStack()
            }
        )

        Row(
            modifier = Modifier.padding(end = 230.dp, top = 35.dp)
        ){
            Icon(
                painter = painterResource(id = R.drawable.ic_target),
                contentDescription = null,
                tint = Color.Unspecified,
            )
            Text(
                text ="현재 목표 칭호",
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.padding(start = 5.dp)
            )
        }

        Box(
            modifier = Modifier
                .width(337.dp)
                .height(106.dp)
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ){
            data?.let { profile ->
                AliasMainCard(level = data.level, exp = data.exp)
            }
        }

        Box(
            modifier = Modifier
                .width(337.dp)
                .wrapContentHeight()
                .padding(top = 25.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ){

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                data?.let { profile ->
                    nameList.forEachIndexed { index, data ->
                        Spacer(modifier = Modifier.height(15.dp))
                        AliasCard(level = index+1, exp = profile.exp, myLevel = profile.level)
                        Spacer(modifier = Modifier.height(15.dp))

                        if (index < nameList.size - 1) {
                            Divider(
                                color = Color(0xFFECEFFB),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(1.dp)
                            )
                        }
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(15.dp))
    }
}
