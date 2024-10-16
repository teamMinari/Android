import android.content.Intent
import android.net.Uri
import android.util.Log
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.R
import com.nohjason.minari.preferences.clearUserToken
import com.nohjason.minari.screens.login.PreferencesManager
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpsResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecGpseResponse
import com.nohjason.minari.screens.profile.directory_screen.direc_data.getDummyDirecTermResponse
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.profile.profile_element.ProfileInfor
import com.nohjason.minari.screens.profile.profile_element.RewardBar
import com.nohjason.minari.screens.profile.likes.LikeList
import com.nohjason.minari.screens.profile.profile_data.LikeListData
import com.nohjason.minari.screens.quiz.quiz_main.selectPlayData
import com.nohjason.minari.ui.theme.pretendard_semibold
import kotlinx.coroutines.launch

@Composable
fun ProfileMAinScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    direcViewModel: DirecViewModel,
    navHostController: NavHostController,
    token : String,
//    preferencesManager: PreferencesManager
) {
    profileViewModel.getProfile(token)
    direcViewModel.getDirecTerm(token) // Term 데이터 호출
    direcViewModel.getDirecGpse(token) // Gpse 데이터 호출
    direcViewModel.getDirecGps(token) // Gps 데이터 호출
    direcViewModel.getDirecGp(token) // Gp 데이터 호출

    val data = profileViewModel.profileData.collectAsState().value
    val direcTermData = direcViewModel.direcTermData.collectAsState().value
    val direcGpseData = direcViewModel.direcGpseData.collectAsState().value // Gpse 데이터 추가
    val direcGpsData = direcViewModel.direcGpsData.collectAsState().value // Gps 데이터 추가
    val direcGpData = direcViewModel.direcGpData.collectAsState().value // Gp 데이터 추가

    val scrollState = rememberScrollState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA))
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        IconButton(
            onClick = {
//                clearUserToken(preferencesManager, "token")
                navHostController.navigate("firstScreen")
//                Log.d("PreferencesManager", "Current token: ${preferencesManager.getData(token, "")}")

            }

        ) {
            Icon(
                modifier = Modifier
                    .padding(start = 325.dp, top = 35.dp),
                painter = painterResource(id = R.drawable.ic_log_out),
                contentDescription = null,
                tint = Color.Unspecified
            )
        }

        //프로필
        data?.let { profile ->
            ProfileInfor(
                id = profile.id,
                email = profile.email,
                totalExp = profile.exp,
                level = profile.level,
                title = profile.title
            )
        }

        Text(
            text = "${data?.point.toString()} P",
            fontWeight = FontWeight.Bold,
            fontSize = 40.sp
        )

        val context = LocalContext.current
        Text(
            text = "소비하러가기>",
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold,
            color = Color(0xFF585EEA),
            modifier = Modifier
                .clickable {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://open.kakao.com/o/sEhpNMTg"))
                    context.startActivity(intent)
                }
        )


        Spacer(modifier = Modifier.height(10.dp))

        //보상 그래프
        data?.let { profile ->
            RewardBar(
                xp= data.exp,
                level = data.level
                )
        }

        //저장목록
        direcTermData?.let { termData ->
            LikeList(direcViewModel = direcViewModel, navHostController = navHostController, token = token)
        }

        Spacer(modifier = Modifier.height(25.dp))
    }

}

@Preview(showBackground = true)
@Composable
fun PreviewProfileMainScreen() {

}


