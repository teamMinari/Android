package com.nohjason.minari

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.nohjason.minari.screens.login.LoginViewModel
//import com.nohjason.minari.screens.profile.my_dictionary.db.MainViewModel
import com.nohjason.minari.ui.theme.MinariTheme
import com.nohjason.minari.network.MainViewModel

class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()
    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MinariTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    ScreenSetup(viewModel, loginViewModel)
                }
            }
        }
    }
}

@Composable
fun ScreenSetup(viewModel: MainViewModel, loginViewModel: LoginViewModel) {
    MainScreen(
        viewModel = viewModel,
        loginViewModel
    )
}