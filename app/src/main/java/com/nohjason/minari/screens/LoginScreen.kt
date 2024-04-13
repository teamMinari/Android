package com.nohjason.minari.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.nohjason.minari.firebase.signin.googleSignInClient
import com.nohjason.minari.firebase.rememberFirebaseAuthLauncher
import com.nohjason.minari.firebase.token.getToken


@Composable
fun LoginScreen(
    navController: NavHostController
) {
    val auth = Firebase.auth
    var user by remember { mutableStateOf(auth.currentUser) }
    val launcher = rememberFirebaseAuthLauncher(
        onAuthComplete = { result ->
            user = result.user
        },
        onAuthError = {
            user = null
        }
    )

    // log에 idtoken출력
    getToken()
    val googleSignInClient = googleSignInClient()

    Column {
        if (user == null) {
            Text("Not logged in")
            Button(onClick = {
                launcher.launch(googleSignInClient.signInIntent)
            }) {
                Text("Sign in via Google")
            }
        } else {
            Text("Welcome ${user!!.displayName}")
            Button(onClick = {
                googleSignInClient.signOut()
                user = null
            }) {
                Text("Sign out")
            }
        }
    }
}