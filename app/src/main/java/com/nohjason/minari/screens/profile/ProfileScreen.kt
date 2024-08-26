package com.nohjason.minari.screens.profile

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.ui.theme.MinariWhite
import com.nohjason.minari.network.MainViewModel
import com.nohjason.minari.screens.home.XpBar
import com.nohjason.minari.screens.login.Test
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.pretendard_medium
import com.nohjason.minari.ui.theme.pretendard_semibold

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
    viewModel: MainViewModel = androidx.lifecycle.viewmodel.compose.viewModel(),
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

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .background(MinariWhite)
            .padding(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        item {
            Row {
                Spacer(modifier = Modifier.weight(0.1f))
                Icon(
                    painter = painterResource(id = R.drawable.signout),
                    contentDescription = null
                )
            }
            Box(
                modifier = Modifier
                    .clip(CircleShape)
                    .clickable {
                        singlePhotoPickerLauncher.launch(
                            PickVisualMediaRequest(
                                ActivityResultContracts.PickVisualMedia.ImageOnly
                            )
                        )
                    }
            ) {
                AsyncImage(
                    model = if(selectedImageUri == null) R.drawable.default_profile else selectedImageUri,
                    contentDescription = null,
                    modifier = Modifier
                        .size(150.dp)
                        .clip(CircleShape),
                )
            }
            Column(
                modifier = Modifier.padding(10.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val test = listOf("금융", "글로벌 경제", "채권")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    val name = "박지민"
                    val userStyle = "소비대왕"
                    Text(
                        text = "$name 님",
                        fontFamily = pretendard_semibold,
                        fontSize = 20.sp
                    )
                    Box(
                        modifier = Modifier
                            .padding(horizontal = 5.dp)
                            .height(15.dp)
                            .width(1.dp)
                            .background(Color.Black)
                    )
                    Text(text = "$userStyle")
                }
                Text(text = "nohjason07@gmail.com")
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "관심주제 : ",
                        fontSize = 16.sp,
                        fontFamily = pretendard_semibold,
                        fontWeight = FontWeight.Bold,
                        color = MinariBlue
                    )
                    for (i in test) {
                        Text(text = "$i, ")
                    }
                }
            }
            Row(
                modifier = Modifier.padding(vertical = 10.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                CustomButton(
                    title = "칭호",
                    onclick = { navController.navigate(Test.Style.rout) }
                )
                CustomButton(
                    title = "관심사",
                    onclick = { navController.navigate(Test.Question.rout) }
                )
            }

            XpBar(
                progress = 123f,
                maxProgress = 200f,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(20.dp)
            )

            SaveList(
                title = "저장 목록",
                icon = painterResource(R.drawable.list)
            )
        }
    }
}

@Composable
fun CustomButton(
    title: String,
    onclick: () -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = 4.dp,
        modifier = Modifier.clickable { onclick() }
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .background(Color(0xFF3D5AFE), RoundedCornerShape(8.dp))
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = "Star",
                    tint = Color.White
                )
            }

            Spacer(modifier = Modifier.width(16.dp))

            Column {
                Text(
                    text = "내 $title",
                    style = MaterialTheme.typography.subtitle1,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "확인하러 가기>",
                    style = MaterialTheme.typography.body2,
                    color = Color(0xFF3D5AFE)
                )
            }
        }
    }
}

@Composable
private fun SaveList(
    title: String,
    icon: Painter
) {
    var array by remember {
        mutableStateOf(
            mutableListOf(
                "가산금리",
                "추가경정예산",
                "금리",
                "추정손실"
            )
        )
    }
    Box(
        modifier = Modifier
            .padding(vertical = 10.dp)
            .clip(RoundedCornerShape(20.dp))
            .fillMaxWidth()
            .background(Color.White)
    ) {
        Column(
            modifier = Modifier.padding(20.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = icon,
                    contentDescription = null,
                    tint = Color.Unspecified
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = title,
                    fontFamily = pretendard_semibold,
                    fontSize = 17.sp
                )
            }
            SaveCard(list = array)
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(CircleShape)
                    .background(MinariBlue)
                    .clickable {
                        array = array
                            .toMutableList()
                            .apply {
                                repeat(3) {
                                    add("test${size + 1}")
                                }
                            }
                    }
            ) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(5.dp)
                )
            }
        }
    }
}

@Composable
private fun SaveCard(list: MutableList<String>) {
    list.forEach {
        Box(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = it,
                    modifier = Modifier.padding(vertical = 10.dp),
                    fontFamily = pretendard_medium,
                    fontSize = 16.sp
                )
                Spacer(modifier = Modifier.weight(0.1f))
                Icon(
                    painter = painterResource(id = R.drawable.other),
                    contentDescription = null,
                    tint = Color.Unspecified
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    ProfileScreen(navController = rememberNavController())
}