package com.nohjason.minari.screens

import android.net.Uri
import android.util.Log
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Phone
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import coil.size.Scale
import com.nohjason.minari.room.MainViewModel
import com.nohjason.minari.room.User

@Composable
fun ProfileSetup(viewModel: MainViewModel) {
    val allProducts by viewModel.allProducts.observeAsState(listOf())

    ProfileScreen(
        allProduct = allProducts,
        viewModel = viewModel
    )
}

@Composable
fun ProfileScreen(
    allProduct: List<User>,
    viewModel: MainViewModel
) {
    var imageUri by remember { mutableStateOf("") }
    val launcher = rememberLauncherForActivityResult(contract =
    ActivityResultContracts.GetContent()) { uri: Uri? ->
        imageUri = uri.toString()
    }

    Column {
        Text(text = "프로필을 선택하세요")
        IconButton(onClick = {
            launcher.launch("image/*")
//            viewModel.insertProduct(
//                User(0, imageUri)
//            )
        }) {
            Icon(
                imageVector = Icons.Default.Phone,
                contentDescription = "Search Profile",
            )
        }

        Image(
            painter = rememberImagePainter(
                data = Uri.parse(imageUri),
                builder = {scale(Scale.FIT)}
            ),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .border(1.dp, Color.Black),
            contentScale = ContentScale.FillWidth,
        )
        Button(onClick = {
            Log.d("TAG", "ProfileScreen: $imageUri")
            viewModel.insertProduct(
                User(0, imageUri)
            )
        }) {
            Text(text = "SaveButton")
        }

        LazyColumn {
            items(allProduct) { elements ->
                Text(text = "$elements")
            }
        }
    }
}