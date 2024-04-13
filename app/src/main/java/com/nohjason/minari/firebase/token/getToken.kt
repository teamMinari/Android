package com.nohjason.minari.firebase.token

import android.util.Log
import com.google.firebase.auth.FirebaseAuth

fun getToken() {
    val mUser = FirebaseAuth.getInstance().currentUser
    mUser!!.getIdToken(true)
        .addOnCompleteListener { task ->
            if (task.isSuccessful) {
                val idToken = task.result.token
                if (idToken != null) {
                    Log.d("TAG", "LoginScreen: $idToken")
                    // Send token to your backend via HTTPS
//                    sendTokenToBackend(idToken)
                }
            } else {
                // Handle error -> task.exception
            }
        }
}