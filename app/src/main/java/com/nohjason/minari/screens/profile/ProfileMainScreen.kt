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
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.ProfileData
import com.nohjason.minari.screens.profile.element.ProfileInfor
import com.nohjason.minari.screens.profile.element.RewardBar
import com.nohjason.minari.screens.profile.likes.Dummy
import com.nohjason.minari.screens.profile.likes.Dummy.dummyLikeList
import com.nohjason.minari.screens.profile.likes.LikeList

@Composable
fun ProfileMAinScreen(
    profileData: ProfileData
){
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
            exp = profileData.exp,
            totalExp = profileData.totalExp,
            level = profileData.level,
            title = profileData.title
        )
        Row (
            modifier = Modifier.padding(top = 22.dp)
        ){
            ProfileButton(
                text = "칭호",
                onClick = {

                }
            )
            Spacer(modifier = Modifier.width(5.dp))
            ProfileButton(
                text = "관심" ,
                onClick = {
                }
            )
        }
        Spacer(modifier = Modifier.height(10.dp))
        val persent = profileData.totalExp/profileData.exp
        //persent를 progress에 넣어야함
        RewardBar(progress = 0.5f, level = "", xp = profileData.exp)
        LikeList(
            likeList = dummyLikeList
        )


    }
}

@Preview
@Composable
fun PreProfile(){
    ProfileMAinScreen(
        profileData = ProfileData(
            idx = 155,
            id = "wlals6691",
            email = "rhdiddl6691@gmail.com",
            vocaBook = "???",
            point = 50,
            exp = 50,
            authority = "",
            title = "소비대",
            level = 5,
            totalExp = 100
        )
    )
}