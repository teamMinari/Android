package com.nohjason.minari.preferences

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

@Composable
//코드 테스트중
fun getPreferences(): SharedPreferences {
    val context = LocalContext.current
    return context.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
}
fun saveToPreferences(preferences: SharedPreferences, key: String, value: String) {
    val editor = preferences.edit()
    editor.putString(key, value)
    editor.apply()
}
fun getFromPreferences(preferences: SharedPreferences, key: String, defaultValue: String = ""): String {
    return preferences.getString(key, defaultValue) ?: defaultValue
}
fun clearUserToken(preferences: SharedPreferences, key: String) {
    val editor = preferences.edit()
    editor.clear()
    editor.apply()
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