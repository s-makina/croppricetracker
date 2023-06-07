package com.sps.compose.data

import android.content.Context
import androidx.compose.runtime.mutableStateOf
import com.sps.compose.data.model.User

object Prefs {
    const val authTokenKey = "authTokenKey"
    val authUser = mutableStateOf<User?>(null)

    fun Context.sharedPrefs() = getSharedPreferences("DAYLIGHT_PREFS", Context.MODE_PRIVATE)

    fun Context.putStr(key: String, value: String?) {
        val editor = sharedPrefs()?.edit()
        editor?.putString(key, value)
        editor?.apply()
    }

    fun Context.getStr(key: String): String {
        return sharedPrefs()?.getString(key, "default_value") ?: ""
    }
}