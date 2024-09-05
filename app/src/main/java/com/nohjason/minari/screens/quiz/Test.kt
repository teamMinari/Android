package com.nohjason.minari.screens.quiz

import android.webkit.WebView
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.viewinterop.AndroidView

@Composable
fun Test() {
    Row(modifier = Modifier.fillMaxSize()) {
        AndroidView(factory = { context ->
            WebView(context).apply {
                loadUrl("https://ibb.co/5RNbsQb")
            }
        }, modifier = Modifier.fillMaxSize())
    }
}

@Preview
@Composable
fun PreviewImageFromUrl() {
    Test()
}
