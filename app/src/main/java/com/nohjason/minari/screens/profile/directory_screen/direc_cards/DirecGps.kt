package com.nohjason.minari.screens.profile.directory_screen.direc_cards

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
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
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGps
import com.nohjason.minari.screens.rout.GrapeViewModel

@Composable
fun DirecGps(
    data: DirecGps,
    token: String,
    grapeViewModel: GrapeViewModel,
    navController: NavController
){
    val isBookmarked = remember { mutableStateOf(false) }

    Row (
        modifier = Modifier.width(300.dp).clickable {
            grapeViewModel.getGps(token= token, gpsId = data.gpsId)
            navController.navigate(Screens.Grapes.rout + "/${data.gpsId}")
        },
        horizontalArrangement = Arrangement.Center
    ){
        AsyncImage(
            modifier = Modifier
                .width(45.dp)
                .height(45.dp),
            model = data.gpsImg,
            contentDescription = null
        )
        Column (
            modifier = Modifier.padding(start = 10.dp)
        ){
            Row(
                modifier = Modifier
                    .width(260.dp),
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
                            grapeViewModel.likes(token = token, category = "GRAPES", id= data.gpsId)
                        }
                )
            }

            Row(
                modifier = Modifier
                    .width(260.dp)
            ) {
                if(data.gpsWork != null){
                    Tp(text = data.gpsWork)
                }
                if( data.gpsAgeGroup != null){
                    Tp(text = data.gpsAgeGroup)
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