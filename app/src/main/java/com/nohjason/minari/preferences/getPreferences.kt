package com.nohjason.minari.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.nohjason.minari.screens.login.PreferencesManager

@Composable
fun getPreferences(): SharedPreferences {
    val context = LocalContext.current
    return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
}
// SharedPreferences Helper Functions
fun saveToPreferences(preferences: SharedPreferences, key: String, value: String) {
    val editor = preferences.edit()
    editor.putString(key, value)
    editor.apply() // 데이터를 비동기적으로 저장
}
fun getFromPreferences(preferences: SharedPreferences, key: String, defaultValue: String = ""): String {
    return preferences.getString(key, defaultValue) ?: defaultValue
}
fun clearUserToken(preferencesManager: PreferencesManager, key: String) {
    preferencesManager.removeData(key)
    Log.d("PreferencesManager", "User token cleared")
}

@Composable
fun getRecentLearning(): SharedPreferences {
    val context = LocalContext.current
    return context.getSharedPreferences("my_prefs1", Context.MODE_PRIVATE)
}
// SharedPreferences Helper Functions
fun saveToResentLearning(preferences: SharedPreferences, key: String, value: String) {
    val editor = preferences.edit()
    editor.putString(key, value)
    editor.apply() // 데이터를 비동기적으로 저장
}
fun getFromResentLearning(preferences: SharedPreferences, key: String, defaultValue: String = ""): String {
    return preferences.getString(key, defaultValue) ?: defaultValue
}