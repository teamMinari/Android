import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.nohjason.minari.screens.profile.element.ProfileInfor

@Composable
fun ProfileMAinScreen(){
    Column(
        modifier = Modifier
            .fillMaxSize(),
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
        ProfileInfor()
        Row {
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




    }
}

@Preview
@Composable
fun PreProfile(){
    ProfileMAinScreen()
}