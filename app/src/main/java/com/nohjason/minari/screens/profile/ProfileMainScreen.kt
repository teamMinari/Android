package com.nohjason.minari.screens.profile

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.profile.LikeList
import com.nohjason.minari.screens.profile.ProfileResponse
import com.nohjason.minari.screens.profile.ProfileViewModel
import com.nohjason.minari.screens.profile.element.ProfileButton
import com.nohjason.minari.screens.profile.element.ProfileInfor
import com.nohjason.minari.screens.profile.element.RewardBar
import com.nohjason.minari.screens.profile.likes.Dummy.likeDummy
import com.nohjason.minari.screens.profile.likes.Like
import com.nohjason.minari.screens.profile.likes.LikeList
import com.nohjason.myapplication.network.MainViewModel
import com.nohjason.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@Composable
fun ProfileMAinScreen(
    profileData: ProfileResponse?,
    navHostController: NavHostController
) {

    if (profileData == null) {
        Text(text = "No profile data available")
        return
    }else{
        val scrollState = rememberScrollState()

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color(0xFFF5F6FA))
                .verticalScroll(scrollState),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            IconButton(onClick = { /*TODO*/ }) {
                Icon(
                    modifier = Modifier
                        .padding(start = 325.dp, top = 35.dp),
                    painter = painterResource(id = R.drawable.ic_log_out),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
            ProfileInfor(
                id = profileData.id,
                email = profileData.email,
                totalExp = profileData.totalExp,
                level = profileData.level,
                title = profileData.title
            )
            Row(
                modifier = Modifier.padding(top = 22.dp)
            ) {
                ProfileButton(
                    text = "칭호",
                    onClick = {
                        navHostController.navigate("myAlias")
                    }
                )
                Spacer(modifier = Modifier.width(5.dp))
                ProfileButton(
                    text = "관심",
                    onClick = {
                        //로그인-설문조사 화면으로 이동
                    }
                )
            }
            Spacer(modifier = Modifier.height(10.dp))

            val percentage = (50 / 100f)//exp구현 시 변경해야함
            RewardBar(progress = percentage, xp = profileData.exp, level = profileData.level)
            LikeList(likeList = likeDummy, navHostController = navHostController)
            Spacer(modifier = Modifier.height(25.dp))
    }

    }
}