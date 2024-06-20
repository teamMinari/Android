package com.nohjason.minari.screens.profile

import android.app.Activity
import android.net.Uri
import android.widget.ImageButton
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.magnifier
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme.colors
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.profile.my_words.MyWordCard
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGradation
import com.nohjason.minari.ui.theme.MinariWhite

sealed class Menu(
    val title: String,
    val image: @Composable () -> ImageVector
) {
    data object VocabularyBook : Menu(
        title = "단어장",
        image = { ImageVector.vectorResource(id = R.drawable.my_words) }
    )

    data object Stor : Menu(
        title = "상점",
        image = { ImageVector.vectorResource(id = R.drawable.store) }
    )

    data object StorHistory : Menu(
        title = "사용내역",
        image = { ImageVector.vectorResource(id = R.drawable.point_history) }
    )
}

@Composable
fun ProfileScreen(
    navController: NavHostController,
) {
    var selectedImageUri by remember {
        mutableStateOf<Uri?>(null)
    }
    val singlePhotoPickerLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia(),
        onResult = { uri ->
            selectedImageUri = uri
        }
    )

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MinariWhite)
    ) {
        /* 프로필 박스 */
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(0.dp, 0.dp, 50.dp, 50.dp))
                .height(170.dp)
                .align(Alignment.CenterHorizontally)
                .background(
                    brush = Brush.linearGradient(
                        colors = MinariGradation,
                        start = androidx.compose.ui.geometry.Offset(1300f, 800f),
                        end = androidx.compose.ui.geometry.Offset(300f, 0f),
                    ),
                )
        ) {
            Column(
                modifier = Modifier
                    .padding(5.dp)
            ) {
                Row {
                    Spacer(modifier = Modifier.weight(0.1f))
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .padding(horizontal = 10.dp, vertical = 1.dp)
                    ) {
                        MinariText(text = "로그아웃", size = 9, color = Color(0xFF7C21E9))
                    }
                }
                Row {
                    Box(modifier = Modifier.padding(20.dp)) {
                        val textStyle = TextStyle(color = Color.White)
                        Column {
                            Text(text = "소비대왕", style = textStyle, fontWeight = FontWeight.SemiBold)
                            MinariText(text = "박지민", size = 27, color = Color.White)
                            MinariText(
                                text = "rhdiddl6691@gmail.com",
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                size = 10
                            )
                            Divider(
                                color = Color.White,
                                thickness = 1.dp,
                                modifier = Modifier
                                    .padding(vertical = 3.dp)
                                    .width(150.dp)
                            )
                            MinariText(
                                text = "관심 주제: " + "선택한 카테고리",
                                color = Color.White,
                                fontWeight = FontWeight.Light,
                                size = 10
                            )
                        }
                    }
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp)
                    ) {
                        Box(modifier = Modifier.align(Alignment.Center)) {
                            AsyncImage(
                                model = selectedImageUri,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .clip(CircleShape),
                                contentScale = ContentScale.Crop
                            )
                            Image(
                                painter = painterResource(id = R.drawable.profile_fix),
                                contentDescription = "profile fix",
                                modifier = Modifier
                                    .align(Alignment.BottomEnd)
                                    .clickable {
                                        singlePhotoPickerLauncher.launch(
                                            PickVisualMediaRequest(
                                                ActivityResultContracts.PickVisualMedia.ImageOnly
                                            )
                                        )
                                    }
                            )
                        }
                    }
                }
            }
        }

        Box(modifier = Modifier.padding(10.dp)) {
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row {
                    MinariText(text = "My 포인트", size = 15)
                    Spacer(modifier = Modifier.weight(0.5f))
                }

                MinariText(text = "1,440P", size = 40, modifier = Modifier.padding(30.dp))
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 10.dp)
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                ) {
                    LazyRow(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        horizontalArrangement = Arrangement.SpaceAround,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        val menu = listOf(
                            Menu.VocabularyBook,
                            Menu.Stor,
                            Menu.StorHistory
                        )
                        itemsIndexed(menu) { index, items ->
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                Icon(
                                    imageVector = items.image(),
                                    contentDescription = null,
                                    tint = Color.Unspecified
                                )
                                Spacer(modifier = Modifier.height(5.dp))
                                MinariText(
                                    text = items.title,
                                    size = 12,
                                    color = Color.Gray
                                )
                            }
                        }
                    }
                }
                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .weight(0.5f)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        MinariText(
                            text = "포인트 상점",
                            size = 15,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .height(1.dp)
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.fixicon),
                                    contentDescription = "fix icon",
                                    modifier = Modifier.size(60.dp)
                                )
                                MinariText(text = "아직 제작 중에 있는\n" + "기능이에요.", size = 10, color = Color.Gray)
                            }
                        }
                    }
                }

                Box(
                    modifier = Modifier
                        .padding(10.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(15.dp))
                        .background(Color.White)
                        .weight(0.5f)
                ) {
                    Column(
                        modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                    ) {
                        MinariText(
                            text = "단어장",
                            size = 15,
                            modifier = Modifier.padding(vertical = 5.dp)
                        )
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Gray)
                                .height(1.dp)
                        )
                        Box(modifier = Modifier.fillMaxSize()) {
                            Row(
                                modifier = Modifier
                                    .align(Alignment.Center)
                                    .padding(20.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Image(
                                    painter = painterResource(id = R.drawable.fixicon),
                                    contentDescription = "fix icon",
                                    modifier = Modifier.size(60.dp)
                                )
                                MinariText(text = "아직 제작 중에 있는\n" + "기능이에요.", size = 10, color = Color.Gray)
                            }
                        }
                    }
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Composable
//fun ProfileScreenPreview() {
//    val navController = rememberNavController()
//    ProfileScreen(navController = navController)
//}