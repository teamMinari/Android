package com.nohjason.cheongfordo.screens.login

import android.content.Context
import android.content.SharedPreferences
import android.util.Log

class PreferencesManager(context: Context) {
    private val sharedPreferences: SharedPreferences =
        context.getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)

    fun saveData(key: String, value: String) {
        val editor = sharedPreferences.edit()
        editor.putString(key, value)
        editor.apply()
    }

    fun getData(key: String, defaultValue: String): String {
        return sharedPreferences.getString(key, defaultValue) ?: defaultValue
    }

    fun removeData(key: String) {
        val editor = sharedPreferences.edit()
        editor.clear()
        editor.commit()
        Log.d("PreferencesManager", "Removed data for key: $key")
    }
}