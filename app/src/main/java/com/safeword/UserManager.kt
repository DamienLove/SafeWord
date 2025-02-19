package com.safeword

import android.content.Context
import android.content.SharedPreferences

class UserManager(context: Context) {
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences("SafeWordPrefs", Context.MODE_PRIVATE)

    fun saveUserSafeWord(safeWord: String) {
        sharedPreferences.edit().putString("SAFE_WORD", safeWord).apply()
    }

    fun getUserSafeWord(): String? {
        return sharedPreferences.getString("SAFE_WORD", null)
    }
}
