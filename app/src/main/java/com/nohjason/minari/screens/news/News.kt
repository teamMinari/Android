package com.nohjason.minari.screens.news

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.ImageLoader
import coil.compose.AsyncImage
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.nohjason.minari.navigation.bottombar.Screen
import com.nohjason.minari.preferences.getFromPreferences
import com.nohjason.minari.preferences.getPreferences
import com.nohjason.minari.screens.home.SwipeNews

@Composable
fun News(
    navController: NavController,
    newsViewModel: NewsViewModel = viewModel()
) {
    val context = LocalContext.current
    val getallNews by newsViewModel.getAllNews.collectAsState()
    val preferences = getPreferences()
    val token = getFromPreferences(preferences, "token")
    LaunchedEffect(Unit) {
        newsViewModel.getAllNews(token, "main")
    }
    BackHandler(onBack = {
        navController.popBackStack(Screen.Home.rout, inclusive = false)
    })
//    val tag = listOf("금융", "증권", "산업/재계", "부동산", "글로벌 경제")
    LazyColumn(
        modifier = Modifier.padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        item {
            SwipeNews()
        }
        if (getallNews != null) {
            items(getallNews!!.data) { item ->
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.clickable {
                        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(item.url))
                        context.startActivity(intent)
                    }
                ) {
                    if (item.thumbnail != null) {
                        AsyncImage(
                            model = item.thumbnail,
                            contentDescription = null,
                            modifier = Modifier.size(110.dp)
                        )
                        Spacer(modifier = Modifier.width(10.dp))
                    }
                    Text(text = item.title, maxLines = 2, overflow = TextOverflow.Ellipsis)
                }
            }
        } else {
            item {
                Box(modifier = Modifier.fillMaxSize()) {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
            }
        }
    }
}



@Preview(showSystemUi = true)
@Composable
private fun Test() {
    News(navController = rememberNavController())
}