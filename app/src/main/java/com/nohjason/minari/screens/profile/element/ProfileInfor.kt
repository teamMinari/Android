package com.nohjason.minari.screens.profile.element

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
import java.io.ByteArrayOutputStream
import android.util.Base64
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.ui.Alignment
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.nohjason.minari.R


@Composable
fun ProfileInfor(

) {
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



    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // 저장된 프로필 이미지가 있으면 표시
        if (!"".equals(sp.getString("profileImage", "")) && !imageTy) {
            val encoded = sp.getString("profileImage", "")
            val imageAsBytes: ByteArray = Base64.decode(encoded?.toByteArray(), Base64.DEFAULT)
            val bitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
            Image(
                bitmap = bitMap.asImageBitmap(),
                contentDescription = "profile",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .clip(shape = RoundedCornerShape(16.dp))
                    .size(150.dp, 250.dp)
                    .clickable {
                        imageTy = true // 이미지 선택 여부 설정
                        launcher.launch("image/*") // 갤러리에서 이미지 선택 시작
                    }
            )
        }
        Box(
            modifier = Modifier
                .width(155.dp)
                .height(155.dp),
            contentAlignment = Alignment.Center
        ) {

            Canvas(
                modifier = Modifier
                    .width(155.dp)
                    .height(155.dp)
            ) {
                val percentage = 75 // 원하는 퍼센트를 지정
                val sweepAngle = 360 * (percentage / 100f)

                drawArc(
                    color = Color(0xFFE0E3ED), // 원호의 색상
                    startAngle = 0f, // 원호의 시작 각도
                    sweepAngle = 360f, // 원호가 그려질 각도
                    useCenter = true, // 원호의 끝 부분을 원의 중심과 연결할지 여부
                    size = size, // 원호를 그릴 직사각형의 크기
                    topLeft = Offset.Zero // 직사각형의 시작 위치
                )
                drawArc(
                    color = Color(0xFF00D33B), // 실제 퍼센트 표시 원호의 색상
                    startAngle = 0f, // 원호의 시작 각도
                    sweepAngle = sweepAngle, // 퍼센트에 맞는 각도를 적용
                    useCenter = true, // 원호의 끝 부분을 원의 중심과 연결
                    size = size, // 원호를 그릴 직사각형의 크기
                    topLeft = Offset.Zero // 직사각형의 시작 위치
                )
            }

            val bitmap = remember { mutableStateOf<Bitmap?>(null) }
            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

            // 이미지 URI가 제공되면 처리
            imageUri?.let {
                // 안드로이드 28 이하 버전에서 비트맵으로 변환
                if (Build.VERSION.SDK_INT < 28) {
                    bitmap.value = MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else { // 안드로이드 28 이상 버전에서 ImageDecoder 사용
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    bitmap.value = ImageDecoder.decodeBitmap(source)
                }

                // 비트맵이 존재하면
                bitmap.value?.let { btm ->
                    @OptIn(kotlin.io.encoding.ExperimentalEncodingApi::class)
                    // 비트맵을 PNG로 압축 후 Base64로 인코딩하여 저장
                    val baos = ByteArrayOutputStream()
                    btm.compress(Bitmap.CompressFormat.PNG, 100, baos)
                    val b: ByteArray = baos.toByteArray()
                    val encoded: String = Base64.encodeToString(b, Base64.DEFAULT) // Base64로 인코딩

                    // SharedPreferences에 저장
                    val editor = sharedPreferences.edit()
                    editor.putString("profileImage", encoded) // 이미지 저장
                    editor.apply() // 저장 완료

                    // 비트맵을 이미지로 보여줌
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = "profile",
                        contentScale = ContentScale.Crop, // 이미지 자르기 설정
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(5.dp, Color(0xFFE0E3ED), RoundedCornerShape(100.dp))
                            .clickable {
                                imageTy = true // 이미지 선택 여부 설정
                                launcher.launch("image/*") // 갤러리에서 이미지 선택 시작
                            }
                    )
                }
            } ?: run {
                // 이미지 URI가 없을 때 기본 이미지 사용
                val encoded = sharedPreferences.getString("profileImage", "")
                if (!encoded.isNullOrEmpty() && !imageTy) {
                    val imageAsBytes: ByteArray = Base64.decode(encoded.toByteArray(), Base64.DEFAULT)
                    val bitMap = BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.size)
                    Image(
                        bitmap = bitMap.asImageBitmap(),
                        contentDescription = "profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(5.dp, Color(0xFFE0E3ED), RoundedCornerShape(100.dp))
                            .clickable {
                                imageTy = true // 이미지 선택 여부 설정
                                launcher.launch("image/*") // 갤러리에서 이미지 선택 시작
                            }
                    )
                } else {
                    // 기본 이미지가 필요할 경우
                    Image(
                        painter = painterResource(id = R.drawable.default_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(5.dp, Color(0xFFE0E3ED), RoundedCornerShape(100.dp))
                            .clickable {
                                imageTy = true // 이미지 선택 여부 설정
                                launcher.launch("image/*") // 갤러리에서 이미지 선택 시작
                            }
                    )
                }
            }



            Box(
                contentAlignment = Alignment.Center, // 동그라미 안에 숫자를 중앙에 위치
                modifier = Modifier
                    .padding(start = 110.dp, top = 100.dp)
                    .size(23.dp) // 동그라미의 크기
                    .shadow(
                        elevation = 5.dp, // 그림자 높이
                        shape = CircleShape, // 그림자 모양 (동그라미)
                        clip = false // 그림자 외부에 내용이 자르지 않도록 설정
                    )

            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    // 동그라미 그리기
                    drawCircle(
                        color = Color(0xFF00D33B), // 동그라미 색상
                    )
                }
                // 숫자 표시
                Text(
                    text = "12"+"Lv",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,// 숫자 색상
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }


        Spacer(modifier = Modifier.height(15.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(text = "wlals6691")
            Spacer(modifier = Modifier.width(5.dp))
            Divider(
                color = Color(0xFFB7B7B7),
                modifier = Modifier
                    .width(1.dp)
                    .height(18.dp),
            )
            Spacer(modifier = Modifier.width(5.dp))
            Text(text = "소비대왕")
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(text = "rhdiddl6691@gmail.com")
        Spacer(modifier = Modifier.height(8.dp))
        Row{
            Text(
                text = "관심주제:",
                color = Color(0xFF363CD5),
                fontWeight = FontWeight.SemiBold
            )
            Text(
                text = "금융, 글로벌 경제, 채권",
                fontWeight = FontWeight.Medium
            )
        }
    }

}

@Preview
@Composable
fun PreProfile(){
    ProfileInfor()
}