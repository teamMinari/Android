package com.nohjason.minari.screens.profile.directory.direc_cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.directory.DirecGp


@Composable
fun DirecGp(
    data: DirecGp
) {
    val isBookmarked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .wrapContentSize()
//            .clip(shape = RoundedCornerShape(30.dp))
//            .background(color = Color(0xFF363CD5))
    ){
        AsyncImage(
            modifier = Modifier
                .wrapContentSize(),
            model = data.gpImg,
            contentDescription = null
        )


        Column {
            Text(
                text = data.gpName,
                fontSize = 15.sp,
                modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp),
                fontWeight = FontWeight.SemiBold,
            )
            //        Row(
//            modifier = Modifier
//                .width(260.dp)
//                .padding(start = 5.dp)
//        ) {
//            .forEachIndexed { index, data ->
////                    DirecGps(text = data)
//                Text(text = data)
//                if (index < TpList.size - 1) {
//                    Spacer(modifier = Modifier.width(3.dp))
//                }
//            }
//        }
        }


        Icon(
            painter = painterResource(
                id = if (isBookmarked.value) R.drawable.ic_book_mark_deactivate
                else R.drawable.minari_book_mark
            ),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable {
                    isBookmarked.value = !isBookmarked.value
                    //서버에 아이디 전송
                }
        )
    }
}
