package com.nohjason.minari.screens.profile.directory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.Gp
import com.nohjason.minari.screens.profile.Gps
import com.nohjason.minari.screens.profile.Gpse

@Composable
fun DirecGpse(
    data: Gpse
){
    val isBookmarked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .width(260.dp) // Row가 부모의 가로 전체를 차지하도록 설정
    ){
        Text(
            text = data.gpseName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
        Text(
            text = "(${data.gpseTime}분)",
            modifier = Modifier
                .weight(1f)
        )
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








@Composable
fun DirecGps(
    data: Gps
){
    val isBookmarked = remember { mutableStateOf(false) }
    val TpList = data.gpsTpList
    Row (
        modifier = Modifier.width(260.dp),
        horizontalArrangement = Arrangement.Center
    ){
        AsyncImage(
            modifier = Modifier
                .wrapContentSize(),
            model = data.gpsImg,
            contentDescription = null
        )
        Column (
            modifier = Modifier.padding(start = 5.dp)
        ){
            Row(
                modifier = Modifier
                    .width(260.dp)
                    .padding(start = 5.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = data.gpsContent,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Spacer(modifier = Modifier.weight(1f))
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

            Row(
                modifier = Modifier
                    .width(260.dp)
                    .padding(start = 5.dp)
            ) {
                TpList.forEachIndexed { index, data ->
                    Tp(text = data)
                    if (index < TpList.size - 1) {
                        Spacer(modifier = Modifier.width(3.dp))
                    }
                }
            }
        }

    }


}

@Composable
fun Tp(text: String){
    Box(
        modifier = Modifier
            .wrapContentSize()
            .clip(shape = RoundedCornerShape(30.dp))
            .background(color = Color(0xFF363CD5))
    ){
        Text(
            text = text,
            color = Color.White,
            fontSize = 10.sp,
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 2.dp)
        )
    }
}










@Composable
fun DirecGp(
    data: Gp
){
    val isBookmarked = remember { mutableStateOf(false) }
    Row(modifier = Modifier
        .width(260.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            modifier = Modifier
                .wrapContentSize()
                .padding(end = 10.dp),
            model = data.gpImg,
            contentDescription = null
        )

        Text(
            text = data.gpName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontSize = 15.sp,
            fontWeight = FontWeight.SemiBold
        )
        Spacer(modifier = Modifier.weight(1f))
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