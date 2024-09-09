import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun ProfileMAinScreen(){
    Box(modifier = Modifier.fillMaxSize()){
        Row {
            ProfileButton(text = "칭호")
            ProfileButton(text = "관심")
        }
    }
}

@Preview
@Composable
fun PreProfile(){
    ProfileMAinScreen()
}