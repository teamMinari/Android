package com.nohjason.minari.screens.profile

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.nohjason.minari.screens.quiz.data.QuestionResponse
import com.nohjason.myapplication.network.RetrofitInstance
import getProfileData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileViewModel : ViewModel() {
    private val _profileData = MutableStateFlow<ProfileResponse>()
    val profileData: StateFlow<ProfileResponse> get() = _profileData

    fun initializeProfileData(data: ProfileResponse) {
        _profileData.value = data
    }

}


