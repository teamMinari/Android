package com.nohjason.minari.screens

import android.app.Activity
import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Phone
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberImagePainter

@Composable
fun ProfileScreen() {
    var imageUri by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
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
    }
    Image(
        painter = rememberImagePainter(
            data = Uri.parse(imageUri)
        ),
        contentDescription = null,
    )
}