package com.nohjason.minari.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController

@Composable
fun MainScreen(navController: NavHostController) {
    Column {
        Text(text = "This is Main Screen")
        Button(onClick = {
            navController.navigate("profile")
        }) {
            Text(text = "go to Profile")
        }

//        BottomNavigation {
//            BottomNavigationItem(
////                icon = ,
//                selected = ,
//                onClick = { /*TODO*/ },
//                icon = { /*TODO*/ }
//            )
//        }
    }
}