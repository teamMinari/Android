package com.nohjason.cheongfordo.screens.profile.directory_screen.direc_cards

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.nohjason.cheongfordo.R
import com.nohjason.cheongfordo.screens.login.Screens
import com.nohjason.cheongfordo.screens.profile.directory_screen.direc_data.DirecTerm
import com.nohjason.cheongfordo.screens.profile.profile_data.TermDifficulty
import com.nohjason.cheongfordo.screens.rout.GrapeViewModel
import com.nohjason.cheongfordo.ui.theme.pretendard_medium

@Composable
fun DirecTerm(
    data: DirecTerm,
    token: String,
    grapeViewModel: GrapeViewModel,
    navController: NavController
) {
    val isBookmarked = remember { mutableStateOf(false) }

    Row(
        modifier = Modifier
            .width(300.dp)
            .clickable {
                grapeViewModel.getSearchTerm(token = token, termNm = data.termNm)
                navController.navigate(Screens.Term.rout + "/${data.termNm}")
            }
    ) {
        // 텍스트와 스타 아이콘
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
        ) {
            Row (
                verticalAlignment = Alignment.CenterVertically
            ){
                Text(
                    text = data.termNm,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    fontWeight = FontWeight.SemiBold
                )
                val numberOfStars = when (data.termDifficulty) {
                    TermDifficulty.LV_1 -> 1
                    TermDifficulty.LV_2 -> 2
                    TermDifficulty.LV_3 -> 3
                }

                // 별 아이콘 생성
                repeat(numberOfStars) {
                    Icon(
                        painter = painterResource(id = R.drawable.star),
                        contentDescription = null,
                        tint = Color.Unspecified,
                        modifier = Modifier
                            .padding(start = 3.dp)
                            .size(10.dp)
                    )
                }
            }
            Text(
                text = data.termExplain,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                color = Color(0xFF7A7A7A),
                fontSize = 10.sp,
                fontFamily = pretendard_medium
            )
        }
        

        Icon(
            painter = painterResource(
                id = if (isBookmarked.value) R.drawable.ic_book_mark_deactivate
                else R.drawable.minari_book_mark
            ),
            contentDescription = null,
            tint = Color.Unspecified,
            modifier = Modifier
                .clickable (
                    indication = null,
                    interactionSource = remember { MutableInteractionSource() }
                ) {
                    isBookmarked.value = !isBookmarked.value
                    grapeViewModel.likes(token = token, category = "TERM", id= data.termId)
                }
        )
        Spacer(modifier = Modifier.height(15.dp))
    }
}

//@Preview
//@Composable
//fun PreTerm(){
//    DirecTerm()
//}