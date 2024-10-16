package com.nohjason.minari.screens.profile.profile_element

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
    id: String,
    email: String,
    totalExp: Int,
    level: Int,
    title: String?
) {
    // 이미지 Uri 상태값을 기억
    var imageUri: Uri? by remember {
        mutableStateOf<Uri?>(null)
    }
    // 이미지가 선택되었는지 여부를 기억
    var imageTy by remember {
        mutableStateOf(false)
    }

    // 현재 LocalContext 가져오기
    val context = LocalContext.current
    // 비트맵을 기억하는 상태 값
    val bitmap = remember {
        mutableStateOf<Bitmap?>(null)
    }
    // 이미지 선택 결과를 처리하는 런처 정의
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
                //exp구현 시 변경해야함
                val percentage = (50 / 100f) * 100
                val sweepAngle = 360 * (percentage / 100f)

                drawArc(
                    color = Color(0xFFE0E3ED),
                    startAngle = 50f,
                    sweepAngle = 360f,
                    useCenter = true,
                    size = size,
                    topLeft = Offset.Zero
                )
                drawArc(
                    color = Color(0xFF00D33B),
                    startAngle = 50f,
                    sweepAngle = sweepAngle,
                    useCenter = true,
                    size = size,
                    topLeft = Offset.Zero
                )
            }

            val sharedPreferences = context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

            // 이미지 URI가 제공되면 처리
            imageUri?.let {
                bitmap.value = if (Build.VERSION.SDK_INT < 28) {
                    MediaStore.Images.Media.getBitmap(context.contentResolver, it)
                } else {
                    val source = ImageDecoder.createSource(context.contentResolver, it)
                    ImageDecoder.decodeBitmap(source)
                }

                bitmap.value?.let { btm ->
                    val baos = ByteArrayOutputStream()
                    btm.compress(Bitmap.CompressFormat.PNG, 100, baos)
                    val b: ByteArray = baos.toByteArray()
                    val encoded: String = Base64.encodeToString(b, Base64.DEFAULT)

                    // SharedPreferences에 저장
                    sharedPreferences.edit()
                        .putString("profileImage", encoded)
                        .apply()

                    // 비트맵을 이미지로 보여줌
                    Image(
                        bitmap = btm.asImageBitmap(),
                        contentDescription = "profile",
                        contentScale = ContentScale.Crop,
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(5.dp, Color(0xFFE0E3ED), RoundedCornerShape(100.dp))
                            .clickable {
                                imageTy = true
                                launcher.launch("image/*")
                            }
                    )
                }
            } ?: run {
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
                                imageTy = true
                                launcher.launch("image/*")
                            }
                    )
                } else {
                    Image(
                        painter = painterResource(id = R.drawable.default_profile),
                        contentDescription = null,
                        modifier = Modifier
                            .width(140.dp)
                            .height(140.dp)
                            .clip(RoundedCornerShape(100.dp))
                            .border(5.dp, Color(0xFFE0E3ED), RoundedCornerShape(100.dp))
                            .clickable {
                                imageTy = true
                                launcher.launch("image/*")
                            }
                    )
                }
            }

            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .padding(start = 110.dp, top = 100.dp)
                    .size(23.dp)
                    .shadow(
                        elevation = 5.dp,
                        shape = CircleShape,
                        clip = false
                    )

            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    drawCircle(
                        color = Color(0xFF00D33B),
                    )
                }
                Text(
                    text = "${level}Lv",
                    fontSize = 10.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.align(Alignment.Center)
                )
            }
        }

        Spacer(modifier = Modifier.height(15.dp))
        Row (
            verticalAlignment = Alignment.CenterVertically
        ){
            Text(
                text = id,
                fontWeight = FontWeight.SemiBold,
                fontSize = 20.sp
            )
            if(title != null){
                Spacer(modifier = Modifier.width(5.dp))
                Divider(
                    color = Color(0xFFB7B7B7),
                    modifier = Modifier
                        .width(1.dp)
                        .height(18.dp),
                )
                Spacer(modifier = Modifier.width(5.dp))
                Text(
                    text = title,
                    fontWeight = FontWeight.Bold,
                    fontSize = 13.sp
                )
            }
        }
        Spacer(modifier = Modifier.height(3.dp))
        Text(
            text = email,
            fontWeight = FontWeight.Normal,
            fontSize = 12.sp
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

