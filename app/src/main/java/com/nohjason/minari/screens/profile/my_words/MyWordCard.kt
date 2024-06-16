package com.nohjason.minari.screens.profile.my_words

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjason.minari.R

@Composable
fun MyWordCard(){
    Box(modifier = Modifier
        .width(295.dp)
        .height(132.dp)){
        Column {
            Text(text = "단어장")
            Divider(
                color = Color(0xFFEEF0F9),
                thickness = 3.dp,
                modifier = Modifier
                    .padding(vertical = 3.dp)
                    .width(150.dp)
            )
            Row {
                Image(painter = painterResource(id = R.drawable.fixicon), contentDescription = "fix icon")
                Text(text = "아직 제작 중에 있는\n" + "기능이에요.")
            }
        }
    }
}