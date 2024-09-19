package com.nohjason.minari.screens.home.news.button

import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import com.nohjason.minari.R

sealed class CategoryNewsButton(
    val title: String,
    val image: @Composable () -> ImageVector
) {
    //    data object House : CategoryNewsButton(
//        title = "부동산",
//        image = { ImageVector.vectorResource(id = R.drawable.img_house) }
//    )
//    data object Chart : CategoryNewsButton(
//        title = "증권",
//        image = { ImageVector.vectorResource(id = R.drawable.img_candlesticks_chart) }
//    )
//    data object Coin : CategoryNewsButton(
//        title = "금융",
//        image = { ImageVector.vectorResource(id = R.drawable.img_coin) }
//    )
//    data object Folder : CategoryNewsButton(
//        title = "채권",
//        image = { ImageVector.vectorResource(id = R.drawable.img_paper_folder) }
//    )
}