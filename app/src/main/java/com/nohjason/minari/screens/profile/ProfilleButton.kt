import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun ProfileButton(
    text: String
){

    Box(
        modifier = Modifier
            .width(170.dp)
            .height(90.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.White)
    ){
        if(text == "관심"){
            Icon(
                modifier = Modifier.padding(start = 24.dp),
                painter = painterResource(id = R.drawable.ic_interest),
                contentDescription = null,
                tint = Color.Unspecified
            )
        } else{
            Icon(
                modifier = Modifier.padding(start = 24.dp),
                painter = painterResource(id = R.drawable.ic_style),
                contentDescription = null,
                tint = Color.Unspecified,
            )
        }
        Text(
            modifier = Modifier.padding(top = 40.dp, start = 24.dp),
            text = "내 " + text + "\n확인하러 가기>",
            fontWeight = FontWeight.SemiBold
        )
    }

}

@Preview
@Composable
fun PreButton(){
    ProfileButton(text = "칭호")
}