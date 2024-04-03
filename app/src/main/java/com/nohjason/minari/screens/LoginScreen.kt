package com.nohjason.minari.screens

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.nohjason.minari.R
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGray

@Composable
fun LoginScreen(
    navController: NavHostController
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(id = R.drawable.grape),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Spacer(modifier = Modifier.height(70.dp))

        Row {
            val size = 30
            Column {
                Text(
                    text = "choung",
                    fontSize = size.sp,
                    color = MinariBlue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "For",
                    fontSize = size.sp,
                    color = MinariBlue,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "do",
                    fontSize = size.sp,
                    color = MinariBlue,
                    fontWeight = FontWeight.Bold
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = "소년을",
                    fontSize = size.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "경제",
                    fontSize = size.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "도우미",
                    fontSize = size.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(200.dp))
        
        Text(
            text = "SNS 로그인",
            color = MinariGray,
            fontSize = 15.sp
        )

        Canvas(
            modifier = Modifier
                .width(346.dp)
                .padding(top = 15.dp, bottom = 10.dp)
        ) {
            val canvasWidth = size.width
            val canvasHeight = size.height
            drawLine(
                start = Offset(x = 0.dp.toPx(), y = canvasHeight / 2),
                end = Offset(x = canvasWidth, y = canvasHeight / 2),
                color = MinariGray,
                strokeWidth = 1.dp.toPx() // instead of 5.dp.toPx() , you can also pass 5f
            )
        }

        IconButton(onClick = {
            navController.navigate("profile")
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_radio_button_unchecked_24),
                contentDescription = null
            )
        }
    }
}