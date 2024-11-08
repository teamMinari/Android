package com.nohjason.cheongfordo.screens.quiz


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.nohjason.cheongfordo.ui.theme.pretendard_bold
import com.nohjason.cheongfordo.ui.theme.pretendard_regular

@Composable
fun QuizButton(
    icResId: ImageVector,
    imgResId: Int,
    color1: Color,
    color2: Color,
    lavel: String,
    coment: String,
    onClick: () -> Unit,
) {
    Box(

        modifier = Modifier
            .padding(top = 25.dp, bottom = 20.dp)
            .shadow(
                elevation = 8.dp,
                shape = RoundedCornerShape(20.dp),
                clip = false
            )
    ) {

        //박스
        Box(
            modifier = Modifier
                .width(305.dp)
                .height(135.dp)
                .clip(RoundedCornerShape(20.dp))
                .background(
                    brush = Brush.linearGradient(
                        colors = listOf(color1, color2),
                        start = Offset(0f, 0f),
                        end = Offset(1000f, 1000f)
                    )
                )
                .clickOnce {
                    onClick()
                }
        )

        //이미지
        Box(modifier = Modifier){
            Image(
                painter = painterResource(id = imgResId),
                contentDescription = null,
                modifier = Modifier
                    .width(185.dp)
                    .height(135.dp)
                    .offset(y = -45.dp, x = 135.dp)
            )
        }

        //텍스트
        Column(
            modifier = Modifier
                .padding(top = 20.dp, start = 20.dp) // 필요에 따라 패딩 조정
        ) {
            Icon(
                modifier = Modifier.padding(bottom = 10.dp),
                imageVector = icResId,
                contentDescription = null,
                tint = Color.Unspecified
            )
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                fontFamily = pretendard_regular,
                text = lavel,
                color = Color.White
            )
            Text(
                modifier = Modifier.padding(bottom = 10.dp),
                text = coment,
                color = Color.White,
                fontFamily = pretendard_bold,
            )
        }
    }

}

//@Preview()
//@Composable
//fun PreButton() {
//    QuizButton(
//        icResId = ImageVector.vectorResource(id = R.drawable.ic_easy),
//        imgResId = R.drawable.img_easy,
//        color1 = Color(0xFF6889FF),
//        color2 = Color(0xFFFF64F5),
//        lavel = "Lavel 1",
//        coment = "제일 쉬운 난이도",
//        onClick = {}
//    )
//}

