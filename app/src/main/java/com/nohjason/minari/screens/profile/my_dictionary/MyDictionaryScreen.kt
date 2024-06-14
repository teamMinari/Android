package com.nohjason.minari.screens.profile.my_dictionary

import android.app.Application
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.primarySurface
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.LocalViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.nohjason.minari.MainViewModelFactory
import com.nohjason.minari.ScreenSetup
import com.nohjason.minari.data.word.allWords
import com.nohjason.minari.screens.inproduct.InProduction
import com.nohjason.minari.screens.profile.my_dictionary.button.CatagoryButton
import com.nohjason.minari.screens.profile.my_dictionary.card.WordCard
import com.nohjason.minari.screens.profile.my_dictionary.db.MainViewModel
import com.nohjason.minari.screens.profile.my_dictionary.db.UserEntity
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.screens.ui.text.MinariTextField
import com.nohjason.minari.ui.theme.MinariLightGray
import com.nohjason.minari.ui.theme.MinariWhite

@Composable
fun MyDictionaryScreen(
    navController: NavHostController,
    mainViewModel: MainViewModel
) {
    var text by remember { mutableStateOf("") }
    Column {
        TopAppBar(
            title = {
                MinariTextField(
                    modifier = Modifier
                        .height(25.dp)
                        .padding(end = 30.dp)
                        .fillMaxWidth()
                        .background(MinariLightGray, shape = CircleShape),
                    value = text,
                    onValueChange = {text = it}
                ) {  }
            },
            backgroundColor =  Color.White,
            navigationIcon = {
                IconButton(onClick = { navController.popBackStack() }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        )
        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val test by mainViewModel.allProducts.observeAsState(emptyList())
            var count = 0;
            test.map { user ->
                if(user.check == false) {
                    count += 1
                }
            }

            Column {
                Spacer(modifier = Modifier.height(10.dp))

                if (test.size - count != 0) {
                    LazyColumn(modifier = Modifier.padding(10.dp)) {
                        items(test) { item ->
                            if (item.check != false) {
                                WordCard(
                                    title = item.id,
                                    isLike = item.check
                                ) {
                                    mainViewModel.upsertProduct(
                                        UserEntity(id = item.id, check = !item.check)
                                    )
                                }
                                Spacer(modifier = Modifier.padding(5.dp))
                            }
                        }
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                } else {
                    InProduction(
                        "아직 추가된 용어가 없습니다",
                        "용어를 추가해 보세요"
                    )
                }
            }
        }
    }
}

@Preview
@Composable
fun Test() {
    Wow()
}

@Composable
fun Wow() {
    var text by remember {
        mutableStateOf("")
    }
    Column {
        TopAppBar(
//            elevation = 4.dp,
            title = {
//                MinariTextField(
//                    value = text,
//                    onValueChange = {text = it}
//                ) {
//
//                }
            },
            backgroundColor =  Color.White,
            navigationIcon = {
                IconButton(onClick = { /* Do Something*/ }) {
                    Icon(Icons.Filled.ArrowBack, null)
                }
            }
        )

        Text("Hello World")
    }
}