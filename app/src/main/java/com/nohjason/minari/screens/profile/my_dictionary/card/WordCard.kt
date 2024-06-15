package com.nohjason.minari.screens.profile.my_dictionary.card

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariText

@Composable
fun WordCard(
    title: String,
    isLike: Boolean,
    onLikeClick: (Boolean) -> Unit
) {

    val context = LocalContext.current
    var expanded by rememberSaveable { mutableStateOf(false) }

    Box(
        modifier = Modifier
            .clickable { expanded = !expanded }
            .clip(RoundedCornerShape(if (expanded) 3 else 10))
            .background(Color.LightGray)
            .padding(10.dp),
    ) {
        Column {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MinariText(text = title, size = 17)
                Spacer(modifier = Modifier.weight(0.5f))
                if (isLike) {
                    IconButton(onClick = {
                        onLikeClick(!isLike)
                        Toast.makeText(context, "좋아요 클릭", Toast.LENGTH_SHORT).show()
                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.minari_hart),
                            contentDescription = null,
                            tint = if (isLike) Color.Unspecified else Color.White,
                            modifier = if (!isLike) Modifier.border(1.dp, Color.Black) else Modifier.border(0.dp, Color.Unspecified)
                        )
                    }
                }


            }

            if (expanded) {
                MinariLine()
                Text(
                    text = """가구의 소득과 자산 상황을 고려해서 “ * * 가계부채 ” * * 의 위험을 평가하는 지표가 있어 .
                    | 이것은 원리금상환비율과 부채 / 자산비율을 합쳐서 계산돼 . 이걸 가계부실위험지수라고 해 .
                    |  만약 이 지수가 100을 넘으면 '위험가구'로 보여. 그런데 이런 위험가구 중에서는 소득이나 자산 측면에서 각각 취약한 '고위험가구', '고DTA가구', '고DSR가구'로 구분할 수 있어. 근데 이 위험가구들이 바로 “채무”를 갚지 못할 정도로 위험하다는 건 아니야.
                    |   그냥 채무 갚는 능력이 취약한 정도를 보여주는 지표야.""".trimMargin(),
                    fontSize = 13.sp,
                    color = Color.Black
                )
            }
        }
    }
}