package com.nohjason.minari.screens.term.card

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.nohjason.minari.R
import com.nohjason.minari.data.button.DummyTermSimilarButton
import com.nohjason.minari.screens.profile.my_dictionary.db.MainViewModel
import com.nohjason.minari.screens.profile.my_dictionary.db.UserEntity
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue

@Composable
fun TermCard(
    title: String,
    value: String,
    starCount: Int,
    dummyTermSimilarButton: List<DummyTermSimilarButton>,
    navController: NavController,
    viewModel: MainViewModel
) {
    Box(
        Modifier.clickable { navController.navigate("test/${title}") }
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 5.dp)
        ) {

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                MinariText(text = title, size = 20)

                Spacer(modifier = Modifier.width(5.dp))

                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(3.dp)
                ) {
                    items(starCount) {
                        Icon(
                            painter = painterResource(R.drawable.star),
                            contentDescription = "star",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(15.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.weight(0.1f))
                // --------------------------------------------------------------------------------
                var check by remember { mutableStateOf(false) }

                Box(
                    modifier = Modifier
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(1.dp, Color.Black, shape = CircleShape) // CircleShape을 명시적으로 설정
                        .padding(vertical = 3.dp, horizontal = 10.dp)
                        .clickable {
                            check = !check
                            viewModel.upsertProduct(
                                UserEntity(id = title, check)
                            )
                        }
                        .padding(3.dp)
                ) {
                    MinariText(text = "단어장 추가/삭제", size = 10)
                }
//                IconButton(onClick = {

//                }) {
//                    Icon(
//                        painter = painterResource(id = R.drawable.minari_hart),
//                        contentDescription = null,
//                        modifier = Modifier.size(10.dp)
//                    )
//                }
                // --------------------------------------------------------------------------------
            }


            Spacer(modifier = Modifier.height(10.dp))

            MinariText(
                text = value,
                size = 11,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis,
                color = Color.Gray,
                textAlign = TextAlign.Left
            )

            Spacer(modifier = Modifier.height(10.dp))

            LazyRow(horizontalArrangement = Arrangement.spacedBy(5.dp)) {
                items(dummyTermSimilarButton) { item ->
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(Color.White)
                            .border(1.dp, Color.Black, shape = CircleShape) // CircleShape을 명시적으로 설정
                            .padding(vertical = 3.dp, horizontal = 10.dp)
                            .clickable {
                                // 화면 이동
                                navController.navigate("test/${item.title}")
                            },
                    ) {
                        MinariText(text = item.title, size = 10)
                    }
                }
            }
        }
    }
}