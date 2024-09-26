package com.nohjason.minari.screens.profile.likes

import androidx.compose.foundation.background
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
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.screens.profile.profile_data.LikeList

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LikeList(
    likeList: LikeList,
    navHostController: NavHostController
) {
    val nameList = likeList.name
    var showBottomSheet by remember { mutableStateOf(false) }
    var inputText by remember { mutableStateOf("") }

    Box(
        modifier = Modifier
            .width(340.dp)
            .wrapContentHeight()
            .padding(top = 10.dp)
            .clip(shape = RoundedCornerShape(20.dp)) // Box에 clip 적용
    ) {
        Column(
            modifier = Modifier
                .clip(shape = RoundedCornerShape(20.dp))
                .background(color = Color.White)
                .padding(horizontal = 24.dp),
//            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row (
                modifier = Modifier.padding(top = 14.dp)
            ){
                Icon(
                    painter = painterResource(id = R.drawable.ic_list),
                    contentDescription = null,
                    tint = Color.Unspecified,
                    modifier = Modifier
                )
                Text(
                    text = "저장 목록",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 15.sp,
                    modifier = Modifier.padding(start = 10.dp)
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                nameList.forEachIndexed { index, data ->
                    Like(onClick = {
                        navHostController.navigate("myDirectory")
                    }, library = data)

                    if (index < nameList.size - 1) {
                        Divider(
                            color = Color(0xFFECEFFB),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(1.dp)
                        )
                    }
                }
            }



            Spacer(modifier = Modifier.height(16.dp)) // Spacer 추가하여 버튼과 리스트 사이에 공간 추가

            Button(
                modifier = Modifier
                    .width(300.dp)
                    .height(30.dp)
                    .clip(shape = RoundedCornerShape(15.dp)),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF585EEA)),
                onClick = { showBottomSheet = true }
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.ic_plus),
                    contentDescription = null,
                    tint = Color.White,
                    modifier = Modifier.size(16.dp) // 아이콘 크기 조정
                )
            }
            Spacer(modifier = Modifier.height(14.dp))
        }
    }


    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false }, // 바텀 시트 외부 클릭 시 닫힘
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp) // 바텀 시트 내부 패딩 설정
        ) {
            // Box를 바텀 시트로 이동
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(top = 10.dp)
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "새로운 목록 추가",
                        fontWeight = FontWeight.SemiBold,
                        fontSize = 15.sp
                    )

                    LikeEditTextFild(
                        value = inputText, // 입력된 텍스트를 상태로 전달
                        onValueChange = { newText -> inputText = newText } // 값이 변경되면 상태 업데이트
                    )

                    Button(onClick = { showBottomSheet = false }) { // 확인 버튼 클릭 시 바텀 시트 닫힘
                        Text(
                            text = "확인",
                            color = Color(0xFF0C21C1),
                            fontSize = 15.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    }
                }
            }
        }
    }
}


//@Preview
//@Composable
//fun PreLikeList() {
//    LikeList(likeList = Dummy.dummyLikeList)
//}
