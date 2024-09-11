package com.nohjason.minari.screens.profile.directory

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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

@Composable
fun DirecGpse(
    data: Gpse
){
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
            painter = painterResource(id = R.drawable.minari_book_mark),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickable {  }
        )
    }
}








@Composable
fun DirecGps(
    data: Gps
){
    val TpList = data.gpsTpList
    Column {
        Row(
            modifier = Modifier
                .width(260.dp)
                .padding(start = 5.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .wrapContentSize(),
                model = data.gpsImg,
                contentDescription = null
            )
            androidx.compose.material3.Text(
                text = data.gpsContent,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                fontSize = 15.sp,
                fontWeight = FontWeight.SemiBold
            )
            Spacer(modifier = Modifier.weight(1f))
            androidx.compose.material.Icon(
                painter = painterResource(id = R.drawable.minari_book_mark),
                contentDescription = null,
                tint = Color.Unspecified,
                modifier = Modifier.clickable { }
            )
        }

        Row(
            modifier = Modifier
                .width(260.dp)
                .padding(start = 5.dp)
        ) {
            TpList.forEachIndexed { index, data ->
                Tp(text = data)
                // Add Spacer only if it's not the last item
                if (index < TpList.size - 1) {
                    Spacer(modifier = Modifier.width(3.dp))
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
        androidx.compose.material3.Text(
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
    Row(modifier = Modifier
        .width(260.dp),
        verticalAlignment = Alignment.CenterVertically
    ){
        AsyncImage(
            modifier = Modifier
                .wrapContentSize(),
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
            painter = painterResource(id = R.drawable.minari_book_mark),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier.clickable {  }
        )
    }
}