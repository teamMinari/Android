package com.nohjason.minari.screens.profile.directory_screen.direc_cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.screens.login.Screens
import com.nohjason.minari.screens.profile.directory_screen.direc_data.DirecGpse
import com.nohjason.minari.screens.rout.GrapeViewModel

@Composable
fun DirecGpse(
    data: DirecGpse,
    token: String,
    grapeViewModel: GrapeViewModel,
    navController: NavController
){
    val isBookmarked = remember { mutableStateOf(false) }
    Row(
        modifier = Modifier
            .width(300.dp)
            .clickable {
                grapeViewModel.getGpse(token = token, gpseId = data.gpseId)
                navController.navigate(Screens.Grape.rout + "/${data.gpseId}/${data.gpseName}")
            }
    ){
        Text(
            text = data.gpseName,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Medium
        )
        Text(
            text = "(${data.gpseTime}분)",
            fontWeight = FontWeight.Medium,
            color = Color(0xFFB2B2B2)
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
                    grapeViewModel.likes(token = token, category = "GRAPESEED", id= data.gpseId)
                }
        )
    }
}