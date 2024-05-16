package com.nohjason.minari.screens.profile

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import coil.compose.rememberImagePainter
import coil.size.Scale

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
        Text(text = "프로필을 선택하세요")
        IconButton(onClick = {
            launcher.launch("image/*")
        }) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Search Profile",
            )
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
        Button(onClick = {
            navController.navigate("myDictionary")
        }) {
            Text(text = "나만의 단어장")
        }
    }
}