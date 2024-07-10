package com.nohjason.minari.screens.login

import android.content.Intent
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.result.ActivityResult
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.nohjason.minari.R
import com.nohjason.minari.navigation.bottombar.BottomBarScreen
import com.nohjason.minari.screens.ui.line.MinariLine
import com.nohjason.minari.screens.ui.text.MinariText
import com.nohjason.minari.ui.theme.MinariBlue
import com.nohjason.minari.ui.theme.MinariGray

@Composable
fun LoginScreen(
    navController: NavHostController,
    launcher: ManagedActivityResultLauncher<Intent, ActivityResult>,
    googleSignInClient: GoogleSignInClient
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {
        Icon(
            painter = painterResource(id = R.drawable.grape),
            contentDescription = null,
            tint = Color.Unspecified,
        )

        Spacer(modifier = Modifier.height(70.dp))

        Row {
            Column {
                MinariText(text = "choung", color = MinariBlue)
                MinariText(text = "For", color = MinariBlue)
                MinariText(text = "do", color = MinariBlue)
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                MinariText(text = "소년을",)
                MinariText(text = "경제",)
                MinariText(text = "도우미",)
            }
        }

        Spacer(modifier = Modifier.height(200.dp))

        //***test를 위해 잠시 버튼 넣겠습니다.***
        Button(onClick = { navController.navigate("Login") }) {
            Text(text = "로그인")
        }

        Text(
            text = "SNS 로그인",
            color = MinariGray,
            fontSize = 15.sp
        )

        MinariLine(horizontalPadding = 30.dp)

        IconButton(onClick = {
            navController.navigate(BottomBarScreen.Home.rout) {
                popUpTo(navController.graph.id) {
                    inclusive = true
                }
            }
        }) {
            Icon(
                painter = painterResource(id = R.drawable.baseline_radio_button_unchecked_24),
                contentDescription = null
            )
        }
    }
}