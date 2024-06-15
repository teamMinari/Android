package com.nohjason.minari.screens.profile

import android.net.Uri
import android.widget.ImageButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.my_words.MyWordCard
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.MinariWhite


@Composable
fun ProfileScreen(
    navController: NavHostController,
) {
    var imageUri by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(
        contract =
        ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri.toString()
    }

    Column {
        /* 프로필 박스 */
        Box(modifier = Modifier
            .width(360.dp)
            .height(170.dp)
            .background(
                brush = Brush.linearGradient(
                    colors = MinariGradation,
                    start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                    end = androidx.compose.ui.geometry.Offset(300f, 0f),
                ),
                shape = RoundedCornerShape(
                    topStart = CornerSize(0.dp), topEnd = CornerSize(0.dp),
                    bottomEnd = CornerSize(50.dp), bottomStart = CornerSize(50.dp)
                )
            )){
            Row {
                val textStyle = TextStyle(color = Color.White)
                Column {
                    Text(text = "소비대왕", style = textStyle, fontWeight = FontWeight.SemiBold)
                    Text(text = "박지민", style = textStyle, fontWeight = FontWeight.SemiBold)
                    Text(text = "rhdiddl6691@gmail.com", style = textStyle)
                    Divider(
                        color = Color.White,
                        thickness = 1.dp,
                        modifier = Modifier
                            .padding(vertical = 3.dp)
                            .width(150.dp)
                    )
                    Text(
                        text = "관심 주제: " + "선택한 카테고리",
                        style = textStyle,
                        fontWeight = FontWeight.Light
                    )

                }
                    Column {
                        Button(
                            onClick = { /*TODO*/ },
                            colors = ButtonDefaults.buttonColors(containerColor = Color.White),
                            modifier = Modifier.width(50.dp).height(15.dp)
                        ) {
                            Text(text = "로그아웃", color = Color(0xFF7C21E9),)
                        }
                        Image(
                            painter = rememberImagePainter(
                                data = Uri.parse(imageUri),
                                builder = { scale(Scale.FIT) }
                            ),
                            contentDescription = null,
                            modifier = Modifier
                                .size(100.dp)
                                .clip(CircleShape),
                            contentScale = ContentScale.FillWidth,
                        )

                        IconButton(onClick = { launcher.launch("image/*")},
                            ) {
                            Image(painter = painterResource(id = R.drawable.profile_fix),
                                contentDescription = "profile fix"
                            )
                        }
                    }

            }
        }

        Text(text = "포인트")
        Text(text = "14440")

        /* 단어장, 상점, 내역 */
        Box(modifier = Modifier
            .width(295.dp)
            .height(85.dp)){
            Row {

                Column {
                    IconButton(onClick = { navController.navigate("myDictionary") }) {
                        Image(painter = painterResource(id = R.drawable.my_words), contentDescription = "my words")
                    }
                    Text(text = "단어장", color = Color(0xFF7E7E7E))
                }

                Column {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.store), contentDescription = "store")
                    }
                    Text(text = "상점", color = Color(0xFF7E7E7E))
                }

                Column {
                    IconButton(onClick = { /*TODO*/ }) {
                        Image(painter = painterResource(id = R.drawable.point_history), contentDescription = "point history")
                    }
                    Text(text = "내역", color = Color(0xFF7E7E7E))
                }
            }
        }

        /* 포인트 상점 */
        Box(modifier = Modifier
            .width(295.dp)
            .height(132.dp)){
            Column {
                Text(text = "포인트 상점")
                Divider(
                    color = Color(0xFFEEF0F9),
                    thickness = 3.dp,
                    modifier = Modifier
                        .padding(vertical = 3.dp)
                        .width(150.dp)
                )
                Row {
                    Image(painter = painterResource(id = R.drawable.fix_icon), contentDescription = "fix icon")
                    Text(text = "아직 제작 중에 있는\n" + "기능이에요.")
                }
            }
        }
        MyWordCard()

    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    val navController = rememberNavController()
//    ProfileScreen(navController = navController)
//}