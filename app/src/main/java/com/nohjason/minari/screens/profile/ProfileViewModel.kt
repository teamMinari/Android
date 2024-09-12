package com.nohjason.minari.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.myapplication.network.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileViewModel : ViewModel() {
    //코드 로직 위주
    private val _profileData = MutableStateFlow<ProfileResponse?>(null)
    val profileData: StateFlow<ProfileResponse?> get() = _profileData

    fun fetchProfileData(userId: String) {
        viewModelScope.launch {
            try {
                val response = withContext(Dispatchers.IO) { RetrofitInstance.api.getProfile(userId) }
                Log.d("QuizViewModel", "fetchProfileData success: $response")
                _profileData.value = response
            } catch (e: Exception) {
                Log.e("QuizViewModel", "General exception occurred: ${e.message}", e)
            }
        }
    }
}


