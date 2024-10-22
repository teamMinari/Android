import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomScreen
import com.nohjason.minari.preferences.clearUserToken
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.login.LoginViewModel
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecViewModel
import com.nohjason.minari.screens.profile.profile_data.ProfileViewModel
import com.nohjason.minari.screens.profile.profile_element.ProfileInfor
import com.nohjason.minari.screens.profile.profile_element.RewardBar
import com.nohjason.minari.screens.profile.profile_element.LikeList
import com.nohjason.minari.screens.profile.profile_element.ProfileButton
import com.nohjason.minari.screens.quiz.QuizPopup
import com.nohjason.minari.screens.rout.GrapeViewModel
import kotlinx.coroutines.launch

@Composable
fun ProfileMAinScreen(
    profileViewModel: ProfileViewModel = viewModel(),
    direcViewModel: DirecViewModel = viewModel(),
    loginViewModel: LoginViewModel,
    grapeViewModel: GrapeViewModel = viewModel(),
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

    if (data == null) {
        CircularProgressIndicator()
        return
    }

    val scrollState = rememberScrollState()

    val preferences = getPreferences()
    val coroutineScope = rememberCoroutineScope()

    var showPopup by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color(0xFFF5F6FA))
            .verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_log_out),
            contentDescription =null ,
            modifier = Modifier
                .padding(start = 325.dp, top = 35.dp)
                .clickable (
                    interactionSource = remember { MutableInteractionSource() },
                    indication = null
                ){ showPopup = true },
            tint = Color.Unspecified
        )

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

        Spacer(modifier = Modifier.height(10.dp))

        Row {
            ProfileButton(text = "칭호") {
                navHostController.navigate(Screens.Alias.rout)
            }

            Spacer(modifier = Modifier.width(5.dp))

            ProfileButton(text = "관심") {
                navHostController.navigate(Screens.Question.rout)
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        //저장목록
        LikeList(
            direcViewModel = direcViewModel,
            navHostController = navHostController,
            token = token,
            grapeViewModel = grapeViewModel
        )

        Spacer(modifier = Modifier.height(25.dp))
    }

    if (showPopup) {
        QuizPopup(
            onDismissRequest = {
                showPopup = false
            }, // 취소
            onConfirmation = {
                coroutineScope.launch {
                    try {
                        // 로그아웃 요청 처리
                        profileViewModel.getLogout(token)
                        clearUserToken(preferences = preferences, key = token)
                        loginViewModel.clearLoginRequest()

                        // 로그아웃 성공 시 화면 전환
                        navHostController.navigate("firstScreen") {
                            popUpTo(0) // 뒤로 가기 방지
                        }
                        Log.d("TAG", "ProfileMainScreen: 로그아웃 성공 \n loginModelView 데이터 여부 ${loginViewModel.loginRequest}")
                    } catch (e: Exception) {
                        Log.e("TAG", "ProfileMainScreen: 로그아웃 실패, 에러: ${e.localizedMessage}")
                    }
                }
            },  // 확인
            dialogTitle = "정말로 나가시겠습니까?",
            dialogText = "다시 로그인하면 접속이 가능합니다.",
            icon = painterResource(id = R.drawable.ic_x)
        )
    }

}

