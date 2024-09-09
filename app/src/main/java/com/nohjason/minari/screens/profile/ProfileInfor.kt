package com.nohjason.minari.screens.profile

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.asImageBitmap
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.CoroutineStart
import java.io.ByteArrayOutputStream
import android.util.Base64
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.painterResource
import com.google.android.play.core.integrity.model.b
import com.nohjason.minari.R


@Composable
fun ProfileInfor(){
    // 이미지 Uri 상태값을 기억
    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }
// 이미지가 선택되었는지 여부를 기억 (타입 명시)
    var imageTy: Boolean by remember {
        mutableStateOf(false)
    }

    // 현재 LocalContext 가져오기
    val context = LocalContext.current
    // 비트맵을 기억하는 상태 값
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    // 이미지 선택 결과를 처리하는 런처 정의 (갤러리에서 이미지 선택)
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri: Uri? ->
        imageUri = uri
    }

    val sp = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

//    val pieData = PieChartData(
//        slices = listOf(
//            Slice(value = 40f, color = Color.Red),
//            Slice(value = 30f, color = Color.Green),
//            Slice(value = 20f, color = Color.Blue),
//            Slice(value = 10f, color = Color.Yellow)
//        )
//    )



    Column (
        horizontalAlignment = Alignment.CenterHorizontally
    ){
//        imageUri?.let {
//            // 안드로이드 28 이하 버전에서 비트맵으로 변환
//            if (Build.VERSION.SDK_INT < 28) {
//                bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
//            } else { // 안드로이드 28 이상 버전에서 ImageDecoder 사용
//                val source = ImageDecoder.createSource(context.contentResolver, it)
//                bitmap.value = ImageDecoder.decodeBitmap(source)
//            }
//
//            // 비트맵이 존재하면
//            bitmap.value?.let { btm ->
//                @OptIn(kotlin.io.encoding.ExperimentalEncodingApi::class)
//                // 비트맵을 PNG로 압축 후 Base64로 인코딩하여 저장
//                val baos = ByteArrayOutputStream()
//                btm.compress(Bitmap.CompressFormat.PNG, 100, baos)
//                val b: ByteArray = baos.toByteArray()
//                val encoded: String = Base64.encodeToString(b, Base64.DEFAULT) // Base64로 인코딩
//
//                // SharedPreferences에 저장
//                val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
//                val editor = sharedPreferences.edit()
//                editor.putString("profileImage", encoded) // 이미지 저장
//                editor.commit() // 저장 완료
//
//                // 비트맵을 이미지로 보여줌
//                Image(
//                    bitmap = btm.asImageBitmap(),
//                    contentDescription = "profile",
//                    contentScale = ContentScale.Crop, // 이미지 자르기 설정
//                    modifier = Modifier
//                        .clip(shape = RoundedCornerShape(16.dp)) // 둥근 모서리 설정
//                        .size(150.dp, 250.dp) // 이미지 크기 설정
//                )
//            }
//        }
//
//        // 저장된 프로필 이미지가 있으면 표시
//        if (!"".equals(sp.getString("profileImage", "")) && !imageTy) {
//            val encoded = sp.getString("profileImage", "")
//            val imageAsBytes: ByteArray = Base64.decode(encoded?.toByteArray(), Base64.DEFAULT)
//            val bitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
//            Image(
//                bitmap = bitMap.asImageBitmap(),
//                contentDescription = "profile",
//                contentScale = ContentScale.Crop,
//                modifier = Modifier
//                    .clip(shape = RoundedCornerShape(16.dp))
//                    .size(150.dp, 250.dp)
//                    .clickable{
//                        imageTy = true // 이미지 선택 여부 설정
//                        launcher.launch("image/*") // 갤러리에서 이미지 선택 시작
//                    }
//            )
//        }
        Image(
            painter = painterResource(id = R.drawable.default_profile),
            contentDescription = null,
            modifier = Modifier
                .width(140.dp)
                .height(140.dp)
                .clip(RoundedCornerShape(100.dp))
        )
//        PieChart(
//            pieChartData = pieData, // PieChartData 객체
//            modifier = Modifier.fillMaxSize(), // 전체 크기를 채움
//            animation = simpleChartAnimation(), // 애니메이션 설정
//            sliceDrawer = SimpleSliceDrawer() // 슬라이스 드로잉 설정
//        )
    }

        Row {
            Text(text = "wlals6691")
            Divider(
                color = Color(0xFFB7B7B7),
                modifier = Modifier
                    .width(1.dp)
                    .height(3.dp) ,
            )
            Text(text = "소비대왕")
        }
        Text(text = "rhdiddl6691@gmail.com")
        Text(text = "관심주제: 금융, 글로벌 경제, 채권")
        
//    }
}

@Preview
@Composable
fun PreProfil(){
    ProfileInfor()
}